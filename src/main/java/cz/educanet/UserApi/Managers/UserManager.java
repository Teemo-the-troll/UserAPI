package cz.educanet.UserApi.Managers;


import cz.educanet.UserApi.ObjectClasses.User;

import javax.enterprise.context.ApplicationScoped;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
public class UserManager {

    ArrayList<User> users = new ArrayList<>();

    public boolean createUser(User user){
        if (user.getUsername() == null || (!user.getEmail().contains(".") || !user.getEmail().contains("@"))  || user.getPassword() == null)
            return false;
        user.setId(users.size());
        users.add(user);
        return true;
    }

    public boolean deleteUser(int id){
        Optional<User> temp = users.stream().filter(u -> u.getId() == id).findFirst();
        return users.remove(temp.get());
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }

    public Optional<User> specUser(int id){
        if (id + 1 > users.size())
            return Optional.empty();
        return Optional.of(users.get(id));
    }

    public Optional<User> specUser(final String username){
        return this.users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }
}
