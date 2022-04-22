package it.emanuelemelini.copypasta.realm

import io.realm.Realm
import io.realm.RealmResults
import it.emanuelemelini.copypasta.model.LoginModel

class MyHelper(private var realm: Realm) {
    private lateinit var loginModel: RealmResults<LoginModel>

    fun saveLoginFromDB() {
        loginModel = realm.where(LoginModel::class.java).findAll()
    }

    fun getLogin(): LoginModel? {
        return loginModel.first()
    }

}