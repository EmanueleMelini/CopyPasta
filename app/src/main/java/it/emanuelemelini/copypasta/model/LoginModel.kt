package it.emanuelemelini.copypasta.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.io.Serializable

@RealmClass
open class LoginModel(
    var IDuser: Int = 0,
    var username: String = "",
    var password: String = "",
    var IDgroup: Int = 0,
    var groupAdmin: Boolean = false,
    var admin: Boolean = false,
    var deleted: Boolean = false
): RealmObject(), Serializable {
    override fun toString(): String {
        return "LoginModel(IDuser=$IDuser, username='$username', password='$password', IDgroup=$IDgroup, groupAdmin=$groupAdmin, admin=$admin, deleted=$deleted)"
    }
}