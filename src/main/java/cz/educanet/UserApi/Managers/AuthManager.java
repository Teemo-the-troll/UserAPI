package cz.educanet.UserApi.Managers;

import cz.educanet.UserApi.Wrappers.Classes.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AuthManager {

    @Inject
    private UserManager manager;

    private final ArrayList<String> tokens = new ArrayList<String>();

    /**
     * @return all tokens
     * purely for testing, should be deleted later
     */
    public ArrayList<String> getTokens() {
        return tokens;
    }

    public Optional<String> loginUser(String username, String password) {
        Optional<User> tempuser = manager.specUser(username);
        if (tempuser.isPresent() && tempuser.get().getPassword().equals(password)) {
            String token = UUID.randomUUID().toString();
            tokens.add(token);
            return Optional.of(token);
        }
        return Optional.empty();
    }

    public boolean logoutUser(String token){
        return tokens.removeIf(t -> t.equals(token));
    }

    public boolean validateToken(String token){
        return tokens.stream().anyMatch(t -> t.equals(token));
    }


}
