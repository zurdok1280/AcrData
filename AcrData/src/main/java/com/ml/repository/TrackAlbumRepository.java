package com.ml.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.TrackAlbumId;
import com.ml.model.Track_Album;

@Repository
public interface TrackAlbumRepository extends JpaRepository<Track_Album, TrackAlbumId>{
}
