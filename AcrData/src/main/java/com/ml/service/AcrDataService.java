package com.ml.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.ml.model.AcrData;
import com.ml.model.AcrDataLog;
import com.ml.model.Album;
import com.ml.model.Artist;
import com.ml.model.Channel;
import com.ml.model.ExternalMetadata;
import com.ml.model.Genre;
import com.ml.model.Isrc;
import com.ml.model.Label;
import com.ml.model.Track;
import com.ml.model.TrackAlbumId;
import com.ml.model.TrackArtistId;
import com.ml.model.TrackGenreId;
import com.ml.model.Track_Album;
import com.ml.model.Track_Artist;
import com.ml.model.Track_Genre;
import com.ml.model.Upc;
import com.ml.model.acr.Acr;
import com.ml.model.acr.Datum;
import com.ml.model.acr.Deezer;
import com.ml.model.acr.Music;
import com.ml.model.acr.Spotify;
import com.ml.model.acr.Youtube;
import com.ml.repository.AcrDataRepository;
import com.ml.repository.TrackAlbumRepository;
import com.ml.repository.TrackArtistRepository;
import com.ml.repository.TrackGenreRepository;

@Service
public class AcrDataService {
	
	Logger logger = LoggerFactory.getLogger(AcrDataService.class);
	
	@Autowired 
	private AcrDataRepository repo;
	@Autowired
	private ChannelService schannel;
	@Autowired
	private TrackService strack;
	@Autowired
	private LabelService slabel;
	@Autowired
	private AlbumService salbum;
	@Autowired
	private AcrDataLogService sacrlog;
	@Autowired
	private ArtistService sartist;
	@Autowired
	private TrackArtistRepository rtrackartist;
	@Autowired
	private IsrcService sisrc;
	@Autowired
	private UpcService supc;
	@Autowired
	private ExternalMetadataService sem;
	@Autowired
	private GenreService sgenre;
	@Autowired
	private TrackGenreRepository gtrackartist;
	@Autowired
	private TrackAlbumRepository rtrackalbum;
	
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public AcrData getLastRow(Long channel) {
		Channel c  = schannel.getById(channel).get();
		return repo.findFirstByChannelOrderByTimestamputcDesc(c);
	}
	 
	
	public List<AcrData> getByChannel(Channel channel){
		return repo.findByChannel(channel);
	}
	
	public AcrData save(AcrData a) {
		Optional<Channel> ch = schannel.getById(a.getChannel().getId());
		Optional<Track> tr = strack.findByIsrcGroup(a.getTrack().getIsrc().stream().map(code -> code.getCode()).collect(Collectors.joining(",")),a.getAcrid() );
		Optional<Label> lb = slabel.getByName(a.getTrack().getLabel().getName());
		if (!ch.isPresent()) {
			schannel.save(a.getChannel());
			ch = Optional.of(a.getChannel());
		}
		if (!lb.isPresent()) {
			slabel.save(a.getTrack().getLabel());
			lb = Optional.of(a.getTrack().getLabel());
			
		}
		a.getTrack().setLabel(lb.get());
		if (!tr.isPresent()) {
			strack.save(a.getTrack());
			tr = Optional.of(a.getTrack());
		}
		
		a.setChannel(ch.get());
		a.setTrack(tr.get());
		
		return repo.saveAndFlush(a);
		
	}
	
	@SuppressWarnings("deprecation")
	public AcrData saveAcr(Acr data,long channel) {
		AcrData acr = null;
		
		Optional<Channel> ch = schannel.getById(channel);
		if (!ch.isPresent()) {
			Channel c = new Channel();
			c.setId(channel);
			ch = Optional.of(schannel.save(c));
		}
		for(Datum d: data.getData()) {
			Music m = d.getMetadata().getMusic().get(0);
			Optional<Label> lb = slabel.getByName(m.getLabel());
			if (!lb.isPresent()) {
				Label lb1 = new Label();
				lb1.setName(m.getLabel());
				lb = Optional.of(slabel.save(lb1));
			}
			
			//Optional<Track> tr = strack.findByIsrcGroup(m.getExternalIds().getIsrc().stream().collect(Collectors.joining(",")),m.getAcrid());
			Optional<Track> tr = strack.FindByAcrid(m.getAcrid());
			if (!tr.isPresent()) {
				Track tr1 = new Track();
				tr1.setLabel(lb.get());
				tr1.setDuration_ms((int)m.getDurationMs());
				String reldate=null;
				if (m.getReleaseDate()!=null && !m.getReleaseDate().equals("") && !m.getReleaseDate().equals("None")) {
					int index = m.getReleaseDate().indexOf("T");
					reldate = m.getReleaseDate();
					if (index>0) {
						reldate = m.getReleaseDate().substring(0, index);
					}else if (reldate.length()<10){
						if (reldate.length()==4)
						reldate = reldate + "-01-01";
						else {
							LocalDate lt = LocalDate.now();
							reldate = lt.toString();
						}
					}
					tr1.setRelease_date(LocalDate.parse(reldate));
				}
				
				tr1.setAcrid(m.getAcrid());
				tr1.setTitle(m.getTitle());
				tr = strack.save(tr1);
				Optional<Artist> ar = null;
				int count = 1;
				for(com.ml.model.acr.Artist a: m.getArtists()) {
					ar = sartist.getByName(a.getName());
					
					if (!ar.isPresent()) {
						Artist ar1 = new Artist();
						ar1.setName(a.getName());
						ar = Optional.of(sartist.save(ar1));
					}
					TrackArtistId tai = new TrackArtistId(tr.get().getId(),ar.get().getId());
					Track_Artist ta = new Track_Artist();
					ta.setId(tai);
					ta.setTrack(tr.get());
					ta.setArtist(ar.get());
					ta.setNo(count);
					rtrackartist.saveAndFlush(ta);
					
					if (count==1) {
						Optional<Album> al = salbum.getByNameAndArtist(m.getAlbum().getName(),ar.get());
						if (!al.isPresent()) {
							Album al1 = new Album();
							al1.setName(m.getAlbum().getName());
							al1.setArtist(ar.get());
							
							al = Optional.of(salbum.save(al1));
							TrackAlbumId trali = new TrackAlbumId(tr.get().getId(),al.get().getId());
							Track_Album tral = new Track_Album();
							tral.setAlbum(al.get());
							tral.setTrack(tr.get());
							tral.setId(trali);
							tral = rtrackalbum.save(tral);
						}
						
					}
					count++;
				}
				
				
				if (m.getGenres()!=null) {
					for(com.ml.model.acr.Genre g: m.getGenres()) {
						Optional<Genre> ge = sgenre.getByName(g.getName());
						if (!ge.isPresent()) {
							Genre g1 = new Genre();
							g1.setName(g.getName());
							ge = Optional.of(sgenre.save(g1));
						}
						TrackGenreId gei = new TrackGenreId(tr.get().getId(),ge.get().getId());
						Track_Genre tg = new Track_Genre();
						tg.setId(gei);
						tg.setTrack(tr.get());
						tg.setGenre(ge.get());
						gtrackartist.saveAndFlush(tg);
					}
				}
				
				if (m.getExternalIds()!=null){
					if (m.getExternalIds().getIsrc()!=null) {
						for(String code: m.getExternalIds().getIsrc()){
							Isrc isrc = new Isrc(code,tr.get());
							sisrc.save(isrc);
						}
					}
					if (m.getExternalIds().getUpc()!=null) {
						for(String code: m.getExternalIds().getUpc()){
							Upc upc = new Upc(code,tr.get());
							supc.save(upc);
						}
					}
				}
				if (m.getExternalMetadata()!=null){
					if (m.getExternalMetadata().getSpotify()!=null) {
						for(Spotify spotify: m.getExternalMetadata().getSpotify()) {
							ExternalMetadata em = new ExternalMetadata(1,spotify.getTrack().getId(),tr.get());
							sem.save(em);
						}
					}
					if (m.getExternalMetadata().getYoutube()!=null) {
						for(Youtube youtube: m.getExternalMetadata().getYoutube()) {
							ExternalMetadata em = new ExternalMetadata(2,youtube.getVid(),tr.get());
							sem.save(em);
						}
					}
					if (m.getExternalMetadata().getDeezer()!=null) {
						for(Deezer deezer: m.getExternalMetadata().getDeezer()) {
							ExternalMetadata em = new ExternalMetadata(3,deezer.getTrack().getId(),tr.get());
							sem.save(em);
						}
					}
				}
				
			}
			acr = new AcrData();
			acr.setAcrid(m.getAcrid());
			acr.setChannel(ch.get());
			acr.setDuration_ms((int) m.getDurationMs());
			acr.setPlayed_duration((int) d.getMetadata().getPlayedDuration());
			acr.setScore((int) m.getScore());
			acr.setTrack(tr.get());
			acr.setPlay_offset_ms((int) m.getPlayOffsetMs());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			LocalDateTime dateTime = LocalDateTime.parse(d.getMetadata().getRecordTimestamp(), formatter);
			acr.setRecord_timestamp(dateTime);
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			dateTime = LocalDateTime.parse(d.getMetadata().getTimestampUtc(), formatter);
			acr.setTimestamputc(dateTime);
			int utc = ch.get().getUtc();
			acr.setLocaltimestamp(dateTime.plusHours(utc));
			acr = repo.saveAndFlush(acr);
			
			
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JSR310Module());
			String jsonmeta="";
			try {
				jsonmeta = obj.writeValueAsString(d.getMetadata());
				AcrDataLog acl = new AcrDataLog(jsonmeta,dateTime,acr);
				sacrlog.save(acl);
			} catch (JsonProcessingException e) {
				logger.error("Error to cast object client, error: " + e.getMessage());
			}
			
			
			
		}
		
		
		return acr;
	}
	
	

}
