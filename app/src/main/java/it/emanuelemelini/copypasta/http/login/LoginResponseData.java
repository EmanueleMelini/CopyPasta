package it.emanuelemelini.copypasta.http.login;

import com.google.gson.annotations.SerializedName;

import it.emanuelemelini.copypasta.model.Login;

public class LoginResponseData {

    @SerializedName("IDuser")
    public Long IDuser;

    @SerializedName("Username")
    public String username;

    @SerializedName("password")
    public String password;

    @SerializedName("Type")
    public String type;

    public Login getLogin() {
        return new Login(IDuser, username, password, type);
    }

    @Override
    public String toString() {
        return "LoginResponseData{" +
                "IDuser=" + IDuser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
