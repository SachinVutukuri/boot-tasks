package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.muzix.repository.TrackRepository;

import java.util.List;
@Service
public class TrackServiceImpl implements TrackService {

    private  TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {

        this.trackRepository = trackRepository;
    }
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId()))
            throw new TrackAlreadyExistsException("Track already exists");
        Track savedTrack = trackRepository.save(track);
        if(savedTrack==null)
            throw new TrackAlreadyExistsException("Track already exists");
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public void deleteTrack(int trackId) throws TrackNotFoundException {
        if(!trackRepository.existsById(trackId))
            throw new TrackNotFoundException("Track doesn't exist");
        trackRepository.deleteById(trackId);
    }

    @Override
    public Track updateComments(int trackId,String newComments) throws TrackNotFoundException {
        if(!trackRepository.existsById(trackId))
            throw new TrackNotFoundException("Track doesn't exist");
        Track track=trackRepository.getOne(trackId);

        track.setComments(newComments);
        trackRepository.save(track);
        return track;
    }

    @Override
    public List<Track> trackByName(String trackName) throws TrackNotFoundException {
        if(trackRepository.getByName(trackName).size()==0)
            throw new TrackNotFoundException("Track doesn't exist");
        return trackRepository.getByName(trackName);
    }
}
