package cz.educanet.UserApi.Managers;


import cz.educanet.UserApi.Wrappers.Classes.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
public class UserManager {

    @Inject
    private DatManager database;

    private ArrayList<User> users = new ArrayList<>();

    public UserManager() throws SQLException {
       // this.updateUsers();
    }

    public boolean createUser(User user) {
        if (user.getUsername() == null || !user.getEmail().contains(".") || !user.getEmail().contains("@") || user.getPassword() == null)
            return false;
        return database.addUser(user);
    }

    public boolean deleteUser(int id) {
        Optional<User> temp = users.stream().filter(u -> u.getId() == id).findFirst();
        return users.remove(temp.get());
    }

    public ArrayList<User> getUsers() throws SQLException {
        return database.getUsers();
    }

    public void updateUsers() throws SQLException {
        this.users = database.getUsers();
    }

    public Optional<User> specUser(int id) throws SQLException {
        return database.getUser(id);
    }

    public Optional<User> specUser(final String username) {
        return this.users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    private User sanitize(User u) {
        return new User(
                u.getUsername().replaceAll("[^a-zA-Z0-9-_]", ""),
                u.getEmail().replaceAll("[^a-zA-Z0-9-_@.]", ""),
                u.getPassword().replaceAll("[^a-zA-Z0-9-_@.!#$%&*()]", ""));
    }
}
