package it.emanuelemelini.copypasta.http

import it.emanuelemelini.copypasta.http.login.BaseLoginResponse
import it.emanuelemelini.copypasta.http.login.User
import it.emanuelemelini.copypasta.model.LoginModel
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    fun loginOld(
        @Header("Content-Type") content: String = "application/json",
        @Field("IDuser") IDuser: Int = 0,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("IDgroup") IDgroup: Int = 0,
        @Field("groupAdmin") groupAdmin: Boolean = false,
        @Field("admin") admin: Boolean = false,
        @Field("deleted") deleted: Boolean = false
    ): Call<BaseResponse<User>>

    @POST("login")
    fun login(
        @Header("Content-Type") content: String = "application/json",
        @Body loginModel: LoginModel
    ): Call<BaseLoginResponse>

}