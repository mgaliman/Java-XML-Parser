/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.dal;

import hr.myproject.model.Person;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author mgali
 */
public interface RepositoryPerson {
    
    int createPerson(Person person) throws Exception;
    void updatePerson(int id, Person data) throws Exception;
    void deletePerson(int id) throws Exception;
    Optional<Person> selectPerson(int id) throws Exception;
    List<Person> selectPersons() throws Exception;
}
