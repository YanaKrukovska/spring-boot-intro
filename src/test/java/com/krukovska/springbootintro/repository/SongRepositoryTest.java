package com.krukovska.springbootintro.repository;

import com.krukovska.springbootintro.model.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
class SongRepositoryTest {

    @Autowired
    private SongRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByIdSuccess() {
        Song savedSong = entityManager.persistFlushFind(new Song("Lotta Lovin", "Gene Vincent", 1957));
        Song song = repository.findById(1);
        Assertions.assertEquals(song.getTitle(), savedSong.getTitle());
        Assertions.assertEquals(song.getArtist(), savedSong.getArtist());
        Assertions.assertEquals(song.getYear(), savedSong.getYear());
    }

}