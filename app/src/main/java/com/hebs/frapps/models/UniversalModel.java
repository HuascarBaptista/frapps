package com.hebs.frapps.models;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:46 AM
 */
public class UniversalModel {
    //Creo la conexion a la bd
    public static Realm crearConexion(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .name("grabilityhebs.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        // Use the config
        return Realm.getInstance(config);
    }


}
