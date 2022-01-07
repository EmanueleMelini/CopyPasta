package it.emanuelemelini.copypasta.realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class MyMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        /*
        if(oldVersion == 0) {
            RealmObjectSchema destSchema = schema.get("Destinazione");
            destSchema.addField("vehicle_name", String.class);
            destSchema.addField("vehicle_last", String.class);
            destSchema.addField("vehicle_distance", String.class);
            destSchema.addField("vehicle_stops", Integer.class);
            oldVersion++;
        }

       */
    }
}
