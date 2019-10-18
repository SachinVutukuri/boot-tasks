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
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException ex) {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }

    @GetMapping("track/{name}")
    public ResponseEntity<?> trackByName(@PathVariable("name") String trackName) {
        try{
            trackService.trackByName(trackName);
            responseEntity=new ResponseEntity<List<Track>>(trackService.trackByName(trackName),HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int trackId) {
        try{
            trackService.deleteTrack(trackId);
            responseEntity=new ResponseEntity<String>("Successfully deleted", HttpStatus.ACCEPTED);
        } catch (TrackNotFoundException ex) {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("track/{id}/{comments}")
    public ResponseEntity<?> updateComments(@PathVariable("id") int trackId,@PathVariable("comments") String newComments) {
        try{
            trackService.updateComments(trackId,newComments);
            responseEntity=new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
