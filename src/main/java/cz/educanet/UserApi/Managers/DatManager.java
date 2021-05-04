package cz.educanet.UserApi.Managers;

import cz.educanet.UserApi.Wrappers.Classes.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

@RequestScoped
public class DatManager {

    private Connection con;

    public boolean addUser(User u) {
        try {
            createStatement().executeQuery("" +
                    "INSERT INTO `test`.`users` (`username`, `password`, `email`)" +
                    " VALUES (" + u.getUsername() + "," + u.getPassword() + " , " + u.getEmail() + " ); "
            );
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public ArrayList<User> getUsers() throws SQLException {
        ResultSet rawUsers = createStatement().executeQuery("select * from users");
        ArrayList<User> users = new ArrayList<>(10);

        while (rawUsers.next()) {
            System.out.println("dalskdjflaksjdflk");
            System.out.println(rawUsers.getString("username"));
            users.add(new User(rawUsers.getString("username"), rawUsers.getString("password")));
        }
        return users;
    }

    public Optional<User> getUser(int id) throws SQLException {
        ResultSet rawUsers = createStatement().executeQuery("select * from users where users.id = " + id);
        if (rawUsers.next()) {
            return Optional.of(new User(rawUsers.getString("username"), rawUsers.getString("password")));
        }
        return Optional.empty();
    }

    public Optional<User> getUser(String username) throws SQLException {
        ResultSet rawUsers = createStatement().executeQuery("select * from users where users.username = " + username);
        if (rawUsers.next()) {
            return Optional.of(new User(rawUsers.getString("username"), rawUsers.getString("password")));
        }
        return Optional.empty();
    }

    private Statement createStatement() throws SQLException {
        return con.createStatement();
    }

    @PreDestroy
    private void destroy() throws SQLException {
        con.close();
    }

    @PostConstruct
    private void init() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
    }


}
