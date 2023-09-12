package com.example.demo.song.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.song.domain.SongRepository;
import com.example.demo.song.domain.Song;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {
    //update
    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public ResponseEntity<List<Song>> songs() {
        List<Song> songs = songRepository.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            return new ResponseEntity<>(song, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping
    public ResponseEntity<String> song(@RequestBody Song song) {
        songRepository.save(song);

        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSong(@PathVariable Long id, @RequestBody Song song){
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()){
            Song existingSong = optionalSong.get();
            existingSong.setTitle(song.getTitle());
            existingSong.setArtist(song.getArtist());
            existingSong.setAlbum(song.getAlbum());
            existingSong.setReleaseDate(song.getReleaseDate());
            existingSong.setGenre(song.getGenre());
            existingSong.setDuration(song.getDuration());
            existingSong.setCoverImage(song.getCoverImage());
            existingSong.setSpotifyUrl(song.getSpotifyUrl());
            songRepository.save(existingSong);
            return ResponseEntity.status(HttpStatus.OK).body("Updated");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateSong(@PathVariable Long id, @RequestBody Song song){
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()){
            Song existingSong = optionalSong.get();
            if(song.getTitle() != null) {
                existingSong.setTitle(song.getTitle());
            }
            songRepository.save(existingSong);
            return ResponseEntity.status(HttpStatus.OK).body("Parcialmente actualizado");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id){
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()){
            songRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
}
