/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author mgali
 */
public class Movie {    
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    private int id;
    private String title;
    private LocalDateTime publishedDate;
    private String description;
    private String originalTitle;
    private Person director;
    private List<Person> actors;
    private String duration;
    private String genre;
    private String picturePath;

    public Movie() {
    }    
    
    public Movie(String title, LocalDateTime publishedDate, String description, String originalTitle, Person person, List<Person> persons, String duration, String genre, String picturePath) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.director = person;
        this.actors = persons;
        this.duration = duration;
        this.genre = genre;
        this.picturePath = picturePath;
    }

    public Movie(int id, String title, LocalDateTime publishedDate, String description, String originalTitle, Person director, List<Person> actors, String duration, String genre, String picturePath) {
        this(title, publishedDate, description, originalTitle, director, actors, duration, genre, picturePath);
        this.id = id;        
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }       

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }    
}
