package it.emanuelemelini.copypasta.model

import io.realm.RealmObject
import java.io.Serializable

open class Login(
    var iDuser: Int = 0,
    var username: String = "",
    var password: String = "",
    var IDgroup: Int = 0,
    var groupAdmin: Boolean = false,
    var admin: Boolean = false,
    var deleted: Boolean = false
): RealmObject(), Serializable {
    override fun toString(): String {
        return "Login(iDuser=$iDuser, username='$username', password='$password', IDgroup=$IDgroup, groupAdmin=$groupAdmin, admin=$admin, deleted=$deleted)"
    }
}