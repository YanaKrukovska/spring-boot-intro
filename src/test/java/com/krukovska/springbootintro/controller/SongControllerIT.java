package com.krukovska.springbootintro.controller;

import com.krukovska.springbootintro.model.Song;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SongControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    void getSongSuccess() {
        HttpEntity<Song> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Song> response = restTemplate.exchange(createURLWithPort("/api/song/1"), GET, entity, Song.class);
        Song song = new Song(1, "Lotta Lovin", "Gene Vincent", 1957);
        assertEquals(song, response.getBody());
    }

    @Test
    void createSongSuccess() {
        Song song = new Song("Miracle Aligner", "The Last Shadow Puppets", 2016);
        HttpEntity<Song> entity = new HttpEntity<>(song, headers);
        Song createdSong = restTemplate.exchange(createURLWithPort("/api/song"), POST, entity, Song.class).getBody();
        assertEquals(song.getTitle(), createdSong.getTitle());
        assertEquals(song.getArtist(), createdSong.getArtist());
        assertEquals(song.getYear(), createdSong.getYear());
    }

    @Test
    void updateSongSuccess() {
        Song song = new Song("To Know Him To Love Him", "The Teddy Bears", 1956);
        Song createdSong = restTemplate.exchange(createURLWithPort("/api/song"), POST,
                new HttpEntity<>(song, headers), Song.class).getBody();
        assertEquals(song.getTitle(), createdSong.getTitle());
        assertEquals(song.getArtist(), createdSong.getArtist());
        assertEquals(song.getYear(), createdSong.getYear());

        createdSong.setTitle("To Know Him Is To Love Him");
        createdSong.setYear(1957);

        Song updatedSong = restTemplate.exchange(createURLWithPort("/api/song"), PUT,
                new HttpEntity<>(createdSong, headers), Song.class).getBody();
        assertEquals(createdSong.getTitle(), updatedSong.getTitle());
        assertEquals(createdSong.getArtist(), updatedSong.getArtist());
        assertEquals(createdSong.getYear(), updatedSong.getYear());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
