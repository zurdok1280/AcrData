package com.ml.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ml.model.Album;
import com.ml.model.Artist;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
	
	Optional<Album> findByName(String name);
	Optional<Album> findByNameAndArtist(String name, Artist a);
	@Query(nativeQuery =true,value = "select top 1 a.* from acr_album a inner join acr_artist aa on aa.id = a.fk_artist inner join acr_track_artist t on t.fk_artist = a.id and t.no=1 where a.name=:par_album and aa.id=:par_artist")  
	Optional<Album> findByAlbumAndArtistQ(@Param("par_album") String par_album,@Param("par_artist") Long par_artist);

}
