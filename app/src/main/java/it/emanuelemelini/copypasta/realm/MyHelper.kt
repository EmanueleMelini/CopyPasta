package it.emanuelemelini.copypasta.realm

import io.realm.Realm
import io.realm.RealmResults
import it.emanuelemelini.copypasta.model.Login

class MyHelper(private var realm: Realm) {
    private lateinit var login: RealmResults<Login>

    fun saveLoginFromDB() {
        login = realm.where(Login::class.java).findAll()
    }

    fun getLogin(): Login? {
        return login.first()
    }

}