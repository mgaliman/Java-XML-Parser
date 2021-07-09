/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.dal;

import hr.myproject.dal.sql.SqlRepositoryAccount;
import hr.myproject.dal.sql.SqlRepositoryMovie;

/**
 *
 * @author mgali
 */
public class RepositoryFactory {
     public RepositoryFactory() {
    }   
    
    public static RepositoryMovie GetMovieRepository() throws Exception {
        return new SqlRepositoryMovie();
    }
    
    public static RepositoryAccount GetUserRepository() throws Exception {
        return new SqlRepositoryAccount();
    }
}
