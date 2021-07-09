/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.dal.sql;

import hr.myproject.dal.RepositoryAccount;
import hr.myproject.model.Account;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author mgali
 */
public class SqlRepositoryAccount implements RepositoryAccount{

    private static final String ID_ACCOUNT = "IDAccount";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String IS_ADMIN = "IsAdmin";

    private static final String CREATE_ACCOUNT = "{ CALL createPerson (?,?,?,?) }";
    private static final String SELECT_ACCOUNT = "{ CALL selectPerson (?) }";

    @Override
    public int createAccount(Account account) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACCOUNT)) {

            stmt.setString(1, account.getUserName());
            stmt.setString(2, account.getPassword());
            stmt.setBoolean(3, account.isAdmin());
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(4);
        }
    }

    @Override
    public Optional<Account> selectPerson(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACCOUNT)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Account(
                            rs.getInt(id)));
                }
            }
        }
        return Optional.empty();
    }
}
