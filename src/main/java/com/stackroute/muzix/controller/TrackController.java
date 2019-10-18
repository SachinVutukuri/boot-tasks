package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {

    private TrackService trackService;
    private ResponseEntity responseEntity;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)throws TrackAlreadyExistsException{
        return new ResponseEntity<String>("Successfully created \n"+trackService.saveTrack(track), HttpStatus.CREATED);
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }

    @GetMapping("track/{name}")
    public ResponseEntity<?> trackByName(@PathVariable("name") String trackName)throws TrackNotFoundException {
        return new ResponseEntity<List<Track>>(trackService.trackByName(trackName),HttpStatus.OK);
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int trackId)throws TrackNotFoundException {
        trackService.deleteTrack(trackId);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.ACCEPTED);
    }

    @PutMapping("track/{id}/{comments}")
    public ResponseEntity<?> updateComments(@PathVariable("id") int trackId,@PathVariable("comments") String newComments)throws TrackNotFoundException {
        trackService.updateComments(trackId,newComments);
        return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
    }

}
