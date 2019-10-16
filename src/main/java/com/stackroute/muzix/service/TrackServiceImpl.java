package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.muzix.repository.TrackRepository;

import java.util.List;
@Service
public class TrackServiceImpl implements TrackService {

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }
    @Override
    public Track saveTrack(Track track) {
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public void deleteTrack(int trackId) {
        trackRepository.deleteById(trackId);
    }

    @Override
    public Track updateComments(int trackId,String newComments) {
        Track track=trackRepository.getOne(trackId);

        track.setComments(newComments);
        trackRepository.save(track);
        return track;
    }
}
