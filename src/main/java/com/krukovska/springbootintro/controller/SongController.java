package com.krukovska.springbootintro.controller;

import com.krukovska.springbootintro.model.Song;
import com.krukovska.springbootintro.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/song")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        Song createdEvent = songService.create(song);
        log.info("Created new song {}", createdEvent);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping(value = "")
    public ResponseEntity<Song> updateSong(@RequestBody Song song) {
        Song updatedSong = songService.update(song);
        log.info("Updated song {}", updatedSong);
        return new ResponseEntity<>(updatedSong, HttpStatus.OK);
    }

    @GetMapping(value = "/{songId}")
    public ResponseEntity<Song> getEvent(@PathVariable("songId") Long songId) {
        Song song = songService.findById(songId);
        log.info("Found song {}", song);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{songId}")
    public ResponseEntity<Boolean> deleteSong(@PathVariable("songId") Long songId) {
        log.info("REST call to delete event with id {}", songId);
        songService.delete(songId);
        return new ResponseEntity<>(songService.delete(songId), HttpStatus.OK);
    }

}
