package com.krukovska.springbootintro.service.impl;

import com.krukovska.springbootintro.model.Song;
import com.krukovska.springbootintro.repository.SongRepository;
import com.krukovska.springbootintro.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository repository;

    @Autowired
    public SongServiceImpl(SongRepository repository) {
        this.repository = repository;
    }

    @Override
    public Song findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Song create(Song song) {
        return repository.save(song);
    }

    @Override
    public Song update(Song song) {
        if (findById(song.getId()) == null) {
            return null;
        }
        return repository.save(song);
    }

    @Override
    public boolean delete(long id) {
        if (findById(id) == null) {
            return false;
        }

        repository.deleteById(id);
        return findById(id) == null;
    }
}
