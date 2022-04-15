package it.emanuelemelini.copypasta.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .schemaVersion(1)
            .migration(MyMigration())
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        Realm.getInstance(realmConfiguration)
    }

}