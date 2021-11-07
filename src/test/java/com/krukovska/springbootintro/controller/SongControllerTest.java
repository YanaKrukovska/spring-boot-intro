package com.krukovska.springbootintro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krukovska.springbootintro.model.Song;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SongControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getSongByIdSuccess() throws Exception {
        mvc.perform(get("/api/song/{songId}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void createNewSongSuccess() throws Exception {
        mvc.perform(post("/api/song")
                .content(asJsonString(new Song("Shivers", "Ed Sheeran", 2021)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void updateSongSuccess() throws Exception {
        mvc.perform(put("/api/song")
                .content(asJsonString(new Song(2, "Lotta Lovin'", "Don McLean", 1977)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Lotta Lovin'"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Don McLean"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(1977));
    }

    @Test
    void deleteSongSuccess() throws Exception {
        mvc.perform(delete("/api/song/{songId}", 2))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}