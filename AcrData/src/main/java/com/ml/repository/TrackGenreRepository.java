package com.ml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.TrackGenreId;
import com.ml.model.Track_Genre;
@Repository
public interface TrackGenreRepository extends JpaRepository<Track_Genre, TrackGenreId> {

}
