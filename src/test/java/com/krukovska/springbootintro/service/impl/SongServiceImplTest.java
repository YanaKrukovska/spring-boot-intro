package com.krukovska.springbootintro.service.impl;

import com.krukovska.springbootintro.model.Song;
import com.krukovska.springbootintro.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SongServiceImplTest {

    @Mock
    private SongRepository repository;

    @InjectMocks
    private SongServiceImpl service;

    @Test
    void findByIdExists() {
        Song expected = new Song(1, "Champagne Problems", "Taylor Swift", 2020);
        when(repository.findById(1)).thenReturn(expected);
        assertEquals(expected, service.findById(1));
    }

    @Test
    void findByIdNotExists() {
        when(repository.findById(1)).thenReturn(null);
        assertNull(service.findById(1));
    }

    @Test
    void createNewSong() {
        Song newSong = new Song("If", "Bread", 1972);
        Song expected = new Song(1, "If", "Bread", 1972);
        when(repository.save(newSong)).thenReturn(expected);
        assertEquals(expected, service.create(newSong));
    }

    @Test
    void updateExistingSong() {
        Song existingSong = new Song(1, "Honeymoon", "Lana Del Ray", 2013);
        Song expected = new Song(1, "Honeymoon", "Lana Del Rey", 2015);
        when(repository.findById(1)).thenReturn(existingSong);
        when(repository.save(existingSong)).thenReturn(expected);
        assertEquals(expected, service.update(existingSong));
    }

    @Test
    void updateNotExistingSong() {
        when(repository.findById(1)).thenReturn(null);
        assertNull(service.update(new Song(1, "Pretty Venom", "All Time Low", 2020)));
    }

    @Test
    void deleteExistingSong() {
        Song song = new Song(1, "Take Me Home, Country Roads", "John Denver", 1971);
        when(repository.findById(1)).thenReturn(song).thenReturn(null);
        assertTrue(service.delete(1));
    }

    @Test
    void deleteNotExistingSong() {
        when(repository.findById(1)).thenReturn(null);
        assertFalse(service.delete(1));
    }
}