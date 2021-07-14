/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author mgali
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"publishedDate", "title", "originalTitle", "duration", "genre", "director", "actors", "description", "picturePath"})
public class Movie {    
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    @XmlAttribute
    private int id;
    private String title;
    @XmlElement(name = "publisheddate")
    @XmlJavaTypeAdapter(PubDateAdapter.class)
    private LocalDateTime publishedDate;
    private String description;
    @XmlElement(name = "originaltitle")
    private String originalTitle;
    private Person director;
    @XmlElement(name = "actor")
    private List<Person> actors;
    private String duration;
    private String genre;
    @XmlElement(name = "picturepath")
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

    public void setId(int id) {
        this.id = id;
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
