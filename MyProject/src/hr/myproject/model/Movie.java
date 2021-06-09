/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.model;

import hr.myproject.enumeration.GenreEnumeration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    private List<Person> persons;
    private int duration;
    private List<GenreEnumeration> genre;
    private String picturePath;
    private String link;
    private Date startDate;

    public Movie() {
    }    
    
    public Movie(String title, LocalDateTime publishedDate, String description, String originalTitle, List<Person> persons, int duration, List<GenreEnumeration> genre, String picturePath, String link, Date startDate) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.persons = persons;
        this.duration = duration;
        this.genre = genre;
        this.picturePath = picturePath;
        this.link = link;
        this.startDate = startDate;
    }

    public Movie(int id, String title, LocalDateTime publishedDate, String description, String originalTitle, List<Person> persons, int duration, List<GenreEnumeration> genre, String picturePath, String link, Date startDate) {
        this(title, publishedDate, description, originalTitle, persons, duration, genre, picturePath, link, startDate);
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> actors) {
        this.persons = actors;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<GenreEnumeration> getGenre() {
        return genre;
    }

    public void setGenre(List<GenreEnumeration> genre) {
        this.genre = genre;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String movieLink) {
        this.link = movieLink;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }
    
    
    
    
}
