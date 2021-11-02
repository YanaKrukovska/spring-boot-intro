package com.krukovska.springbootintro.service;

import com.krukovska.springbootintro.model.Song;

public interface SongService {

    Song findById(long id);

    Song create(Song song);

    Song update(Song song);

    void delete(long id);

}
