package com.example.demo.playlist.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.playlist.domain.PlaylistRepository;
import com.example.demo.playlist.domain.Playlist;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    //update
    @Autowired
    private PlaylistRepository playlistRepository;
    //Para todas las playlist
    @GetMapping
    public ResponseEntity<List<Playlist>> playlist() {
        List<Playlist> playlists = playlistRepository.findAll();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }
    //Para una playlist
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            Playlist playlist = optionalPlaylist.get();
            return new ResponseEntity<>(playlist, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Crear una playlist
    @PostMapping
    public ResponseEntity<String> playlist(@RequestBody Playlist playlist) {
        playlistRepository.save(playlist);

        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlaylist(@PathVariable Long id, @RequestBody Playlist newPlaylist){
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if(optionalPlaylist.isPresent()){
            Playlist existingPlaylist = optionalPlaylist.get();
            existingPlaylist.setSongs(newPlaylist.getSongs());
            existingPlaylist.setTitle(newPlaylist.getTitle());
            existingPlaylist.setCoverImage(newPlaylist.getCoverImage());
            playlistRepository.save(existingPlaylist);
            return ResponseEntity.status(HttpStatus.OK).body("Updated Playlist");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdatePlaylist(@PathVariable Long id, @RequestBody Playlist newPlaylist){
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if(optionalPlaylist.isPresent()){
            Playlist existingPlaylist = optionalPlaylist.get();
            if(newPlaylist.getTitle() != null) {
                existingPlaylist.setTitle(newPlaylist.getTitle());
            }
            playlistRepository.save(existingPlaylist);
            return ResponseEntity.status(HttpStatus.OK).body("Parcialmente actualizado");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long id){
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if(optionalPlaylist.isPresent()){
            playlistRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Playlist");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }  
    

}
