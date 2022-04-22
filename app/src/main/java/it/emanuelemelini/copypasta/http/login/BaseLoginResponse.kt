package it.emanuelemelini.copypasta.http.login

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseLoginResponse(
    @SerializedName("error") val error: Int,
    @SerializedName("message") val message: String,
    @SerializedName("response") val response: User,
    @SerializedName("token") val token: String
): Serializable {
    override fun toString(): String {
        return "BaseLoginResponse(error=$error, message='$message', response=$response, token='$token')"
    }
}