package cz.educanet.UserApi.Wrappers.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserEntity {
    private int id;
    private String username;
    private String email;
    private String password;

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public UserEntity(){

    }

    public UserEntity(String username, String password){
        this.password = password;
        this.username = username;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }


}
