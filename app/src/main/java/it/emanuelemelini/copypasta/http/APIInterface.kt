package it.emanuelemelini.copypasta.http

import it.emanuelemelini.copypasta.http.login.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("IDuser") IDuser: Int = 0,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("IDgroup") IDgroup: Int = 0,
        @Field("groupAdmin") groupAdmin: Boolean = false,
        @Field("admin") admin: Boolean = false,
        @Field("deleted") deleted: Boolean = false
    ): Call<BaseResponse<User>>

}