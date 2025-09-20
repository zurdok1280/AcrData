
package com.ml.model.acr;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "album",
    "play_offset_ms",
    "sample_begin_time_offset_ms",
    "title",
    "result_from",
    "release_date",
    "sample_end_time_offset_ms",
    "label",
    "duration_ms",
    "score",
    "db_begin_time_offset_ms",
    "artists",
    "db_end_time_offset_ms",
    "external_ids",
    "acrid",
    "external_metadata"
})
@Generated("jsonschema2pojo")
public class Music {

    @JsonProperty("album")
    private Album album;
    @JsonProperty("play_offset_ms")
    private long playOffsetMs;
    @JsonProperty("sample_begin_time_offset_ms")
    private long sampleBeginTimeOffsetMs;
    @JsonProperty("title")
    private String title;
    @JsonProperty("result_from")
    private long resultFrom;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("sample_end_time_offset_ms")
    private long sampleEndTimeOffsetMs;
    @JsonProperty("genres")
    private List<Genre> genres = null;  
    @JsonProperty("label")
    private String label;
    @JsonProperty("duration_ms")
    private long durationMs;
    @JsonProperty("score")
    private long score;
    @JsonProperty("db_begin_time_offset_ms")
    private long dbBeginTimeOffsetMs;
    @JsonProperty("artists")
    private List<Artist> artists = null;
    @JsonProperty("db_end_time_offset_ms")
    private long dbEndTimeOffsetMs;
    @JsonProperty("external_ids")
    private ExternalIds externalIds;
    @JsonProperty("acrid")
    private String acrid;
    @JsonProperty("external_metadata")
    private ExternalMetadata externalMetadata;

    @JsonProperty("album")
    public Album getAlbum() {
        return album;
    }

    @JsonProperty("album")
    public void setAlbum(Album album) {
        this.album = album;
    }

    @JsonProperty("play_offset_ms")
    public long getPlayOffsetMs() {
        return playOffsetMs;
    }

    @JsonProperty("play_offset_ms")
    public void setPlayOffsetMs(long playOffsetMs) {
        this.playOffsetMs = playOffsetMs;
    }

    @JsonProperty("sample_begin_time_offset_ms")
    public long getSampleBeginTimeOffsetMs() {
        return sampleBeginTimeOffsetMs;
    }

    @JsonProperty("sample_begin_time_offset_ms")
    public void setSampleBeginTimeOffsetMs(long sampleBeginTimeOffsetMs) {
        this.sampleBeginTimeOffsetMs = sampleBeginTimeOffsetMs;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("result_from")
    public long getResultFrom() {
        return resultFrom;
    }

    @JsonProperty("result_from")
    public void setResultFrom(long resultFrom) {
        this.resultFrom = resultFrom;
    }

    @JsonProperty("release_date")
    public String getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("release_date")
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("sample_end_time_offset_ms")
    public long getSampleEndTimeOffsetMs() {
        return sampleEndTimeOffsetMs;
    }

    @JsonProperty("sample_end_time_offset_ms")
    public void setSampleEndTimeOffsetMs(long sampleEndTimeOffsetMs) {
        this.sampleEndTimeOffsetMs = sampleEndTimeOffsetMs;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("duration_ms")
    public long getDurationMs() {
        return durationMs;
    }

    @JsonProperty("duration_ms")
    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    @JsonProperty("score")
    public long getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(long score) {
        this.score = score;
    }

    @JsonProperty("db_begin_time_offset_ms")
    public long getDbBeginTimeOffsetMs() {
        return dbBeginTimeOffsetMs;
    }

    @JsonProperty("db_begin_time_offset_ms")
    public void setDbBeginTimeOffsetMs(long dbBeginTimeOffsetMs) {
        this.dbBeginTimeOffsetMs = dbBeginTimeOffsetMs;
    }

    @JsonProperty("artists")
    public List<Artist> getArtists() {
        return artists;
    }

    @JsonProperty("artists")
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @JsonProperty("db_end_time_offset_ms")
    public long getDbEndTimeOffsetMs() {
        return dbEndTimeOffsetMs;
    }

    @JsonProperty("db_end_time_offset_ms")
    public void setDbEndTimeOffsetMs(long dbEndTimeOffsetMs) {
        this.dbEndTimeOffsetMs = dbEndTimeOffsetMs;
    }

    @JsonProperty("external_ids")
    public ExternalIds getExternalIds() {
        return externalIds;
    }

    @JsonProperty("external_ids")
    public void setExternalIds(ExternalIds externalIds) {
        this.externalIds = externalIds;
    }

    @JsonProperty("acrid")
    public String getAcrid() {
        return acrid;
    }

    @JsonProperty("acrid")
    public void setAcrid(String acrid) {
        this.acrid = acrid;
    }

    @JsonProperty("external_metadata")
    public ExternalMetadata getExternalMetadata() {
        return externalMetadata;
    }

    @JsonProperty("external_metadata")
    public void setExternalMetadata(ExternalMetadata externalMetadata) {
        this.externalMetadata = externalMetadata;
    }
    @JsonProperty("genres")
	public List<Genre> getGenres() {
		return genres;
	}
    @JsonProperty("genres")
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
    
    

}
