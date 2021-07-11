/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.dal;

import hr.myproject.model.Movie;
import hr.myproject.model.Person;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author mgali
 */
public interface RepositoryMovie {
    
    int createMovie(Movie movie) throws Exception;
    void createMovies(List<Movie> movies) throws Exception;
    void updateMovie(int id, Movie data) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectMovies() throws Exception;
    void deleteMovies() throws Exception;
    
    
    int createPerson(Person person) throws Exception;
    void updatePerson(int id, Person data) throws Exception;
    void deletePerson(int id) throws Exception;
    Optional<Person> selectPerson(int id) throws Exception;
    List<Person> selectPersons() throws Exception;
    
    int createMovieActor(Person person, Movie movie) throws Exception;
    List<Person> selectMovieActors(int id) throws Exception;

    List<Person> selectFavoritePersons() throws Exception;
    void addFavoritePerson(int id) throws Exception;
}
