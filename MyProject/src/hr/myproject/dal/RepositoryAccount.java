/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.dal;

import hr.myproject.model.Account;
import java.util.Optional;

/**
 *
 * @author mgali
 */
public interface RepositoryAccount {
    int createAccount(Account account) throws Exception;
    Optional<Account> selectPerson(int id) throws Exception;
}
