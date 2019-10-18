package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    @Query("from Track where trackName=?1")
    List<Track> getByName(String trackName);

}
