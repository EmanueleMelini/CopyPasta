package it.emanuelemelini.copypasta.realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new MyMigration())
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        Realm.getInstance(realmConfiguration);
        /*Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> realm.deleteAll());*/
    }
}
