package com.ml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.TrackArtistId;
import com.ml.model.Track_Artist;

@Repository
public interface TrackArtistRepository extends JpaRepository<Track_Artist, TrackArtistId> {

}
