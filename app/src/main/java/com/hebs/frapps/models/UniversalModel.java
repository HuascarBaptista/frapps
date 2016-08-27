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
    public static Realm crearConexion(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .name("grabilityhebs.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        // Use the config
        return Realm.getInstance(config);
    }


    public static void todaInformacion(Context context) {
        InformacionAlmacenada _nueva = new InformacionAlmacenada();
        _nueva.set_apps(AppModel.obtenerTodas(context));
        _nueva.set_categorias(CategoriaModel.obtenerTodas(context));
        _nueva.set_artistas(ArtistaModel.obtenerTodas(context));
    }
}
