package com.ml.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ml.model.Track;
@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
	
	Track findByIsrc(String code);
	@Query(nativeQuery =true,value = "select top 1 t.* from acr_track t inner join acr_isrc i on i.fk_track=t.id where i.code in(:par_code) and acrid=:par_acrid")  
	Optional<Track> findByIsrcGroup(@Param("par_code") String par_code,@Param("par_acrid") String par_acrid);
	Optional<Track> findByAcrid(String acrid);
}
