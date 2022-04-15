package it.emanuelemelini.copypasta.model;

import java.io.Serializable;

import io.realm.RealmObject;

public class Login extends RealmObject implements Serializable {

    private Long IDuser;
    private String username;
    private String password;
    private String type;

    public Login() {}

    public Login(Long IDuser, String username, String password, String type) {
        this.IDuser = IDuser;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public Long getIDuser() {
        return IDuser;
    }

    public void setIDuser(Long IDuser) {
        this.IDuser = IDuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Login{" +
                "IDuser=" + IDuser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
