package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {

    TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int trackId) {
        ResponseEntity responseEntity;
        try{
            trackService.deleteTrack(trackId);
            responseEntity=new ResponseEntity<String>("Successfully deleted", HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("track/{id}/{comments}")
    public ResponseEntity<?> updateComments(@PathVariable("id") int trackId,@PathVariable("comments") String newComments) {
        ResponseEntity responseEntity;
        try{
            trackService.updateComments(trackId,newComments);
            responseEntity=new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_MODIFIED);
        }
        return responseEntity;
    }

}
