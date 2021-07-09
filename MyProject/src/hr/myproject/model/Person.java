/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.model;

/**
 *
 * @author mgali
 */
public class Person implements Comparable<Person> {
    
    private int id;
    private String firstName;
    private String lastName;

    public Person() {
    }

    public Person(String name, String lastName) {
        this.firstName = name;
        this.lastName = lastName;
    }

    public Person(int id, String name, String lastName) {
        this(name, lastName);
        this.id = id;
    }

    public Person(int id) {
        this.id = id;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return id + " - " + firstName + " - " + lastName;
    }       

    @Override
    public int compareTo(Person o) {
        return Integer.valueOf(id).compareTo(o.id);
    }
    
}
