package it.emanuelemelini.copypasta.http;

import it.emanuelemelini.copypasta.http.login.LoginResponseObj;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseObj> login(@Field("username") String username,
                                    @Field("password") String password);

}
