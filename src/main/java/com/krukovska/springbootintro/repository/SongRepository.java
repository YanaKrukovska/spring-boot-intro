package com.krukovska.springbootintro.repository;

import com.krukovska.springbootintro.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {

    Song findById(long id);
}
