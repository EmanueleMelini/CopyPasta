package it.emanuelemelini.copypasta.http

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("error") var error: Int,
    @SerializedName("message") var message: String,
    @SerializedName("response") var response: T
) {
    override fun toString(): String {
        return "BaseResponse(error=$error, message='$message', response=$response)"
    }
}