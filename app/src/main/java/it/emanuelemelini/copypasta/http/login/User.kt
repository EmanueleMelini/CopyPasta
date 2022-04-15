package it.emanuelemelini.copypasta.http.login

import com.google.gson.annotations.SerializedName
import it.emanuelemelini.copypasta.model.Login

data class User(
    @SerializedName("IDuser") var IDuser: Int,
    @SerializedName("Username") var username: String,
    @SerializedName("password") var password: String,
    @SerializedName("IDgroup") var IDgroup: Int,
    @SerializedName("groupAdmin") var groupAdmin: Boolean,
    @SerializedName("admin") var admin: Boolean,
    @SerializedName("deleted") var deleted: Boolean
) {
    override fun toString(): String {
        return "User(IDuser=$IDuser, username='$username', password='$password', IDgroup=$IDgroup, groupAdmin=$groupAdmin, admin=$admin, deleted=$deleted)"
    }
}