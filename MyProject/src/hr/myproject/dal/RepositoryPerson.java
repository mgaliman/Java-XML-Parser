/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.dal;

import hr.myproject.model.Person;

/**
 *
 * @author mgali
 */
public interface RepositoryPerson {
    boolean addPerson(Person person) throws Exception;
}
