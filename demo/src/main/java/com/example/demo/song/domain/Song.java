package com.example.demo.song.domain;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    private String artist;

    private String album;

    private Date releaseDate;

    private String genre;

    private Integer duration;

    private String coverImage;

    private String spotifyUrl;

    public Song() {}

    public Song(Long id, String title, String artist, String album, Date releaseDate, String genre, Integer duration, String coverImage, String spotifyUrl) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.duration = duration;
        this.coverImage = coverImage;
        this.spotifyUrl = spotifyUrl;
    }


    // Getters y setters
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public String getGenre() {
        return this.genre;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public String getCoverImage() {
        return this.coverImage;
    }

    public String getSpotifyUrl() {
        return this.spotifyUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public void setArtist(String artist) {
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist cannot be null or empty");
        }
        this.artist = artist;
    }

    public void setAlbum(String album) {
        if (album == null || album.trim().isEmpty()) {
            throw new IllegalArgumentException("Album cannot be null or empty");
        }
        this.album = album;
    }

    public void setReleaseDate(Date releaseDate) {
        if (releaseDate == null) {
            throw new IllegalArgumentException("Release date cannot be null");
        }
        this.releaseDate = releaseDate;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        }
        this.genre = genre;
    }

    public void setDuration(Integer duration) {
        if (duration == null || duration < 0) {
            throw new IllegalArgumentException("Duration cannot be null or negative");
        }
        this.duration = duration;
    }

    public void setCoverImage(String coverImage) {
        if (coverImage == null || coverImage.trim().isEmpty()) {
            throw new IllegalArgumentException("Cover image cannot be null or empty");
        }
        this.coverImage = coverImage;
    }

    public void setSpotifyUrl(String spotifyUrl) {
        if (spotifyUrl == null || spotifyUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Spotify URL cannot be null or empty");
        }
        this.spotifyUrl = spotifyUrl;
    }

    // toString

    @Override
    public String toString() {
        return "Song{" + "id=" + this.id + ", title='" + this.title + '\'' + ", artist='" + this.artist + '\'' + ", album='" + this.album + '\'' + ", releaseDate=" + this.releaseDate + ", genre='" + this.genre + '\'' + ", duration=" + this.duration + ", coverImage='" + this.coverImage + '\'' + ", spotifyUrl='" + this.spotifyUrl + '\'' + '}';
    }

    // equals y hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Song))
            return false;
        Song song = (Song) o;
        return this.id.equals(song.id);
    }

}