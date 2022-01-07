package it.emanuelemelini.copypasta.http.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponseObj {

    @SerializedName("code")
    public String code;

    @SerializedName("message")
    public String message;

    @SerializedName("response")
    public LoginResponseData response;

}
