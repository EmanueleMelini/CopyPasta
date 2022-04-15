package it.emanuelemelini.copypasta.realm

import io.realm.RealmMigration
import io.realm.DynamicRealm
import io.realm.RealmSchema

class MyMigration : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema
        /*if(oldVersion == 0) {
            RealmObjectSchema destSchema = schema.get("Destinazione");
            destSchema.addField("vehicle_name", String.class);
            destSchema.addField("vehicle_last", String.class);
            destSchema.addField("vehicle_distance", String.class);
            destSchema.addField("vehicle_stops", Integer.class);
            oldVersion++;
        }*/
    }

}