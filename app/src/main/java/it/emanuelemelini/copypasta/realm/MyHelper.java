package it.emanuelemelini.copypasta.realm;

import io.realm.Realm;
import io.realm.RealmResults;
import it.emanuelemelini.copypasta.model.Login;

public class MyHelper {

    Realm realm;
    RealmResults<Login> login;

    public MyHelper(Realm realm) {
        this.realm = realm;
    }

    public void saveLoginFromDB() {
        login = realm.where(Login.class).findAll();
    }

    public Login getLogin() {
        return login.first();
    }

}
