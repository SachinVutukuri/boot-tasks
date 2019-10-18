package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks();

    public void deleteTrack(int trackId) throws TrackNotFoundException;

    public Track updateComments(int trackId,String newComments) throws TrackNotFoundException;

    public List<Track> trackByName(String trackName) throws TrackNotFoundException;
}
