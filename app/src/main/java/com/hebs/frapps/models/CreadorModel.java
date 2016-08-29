package com.hebs.frapps.models;

import android.content.Context;

import com.hebs.frapps.models.modelsRealm.Creadores;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:35 AM
 */
public class CreadorModel {
    public static Creadores crearActualizarCreador(Context context, final String nombre, final String link, final String firma) {
        Realm realm = UniversalModel.crearConexion(context);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Creadores result = realm.where(Creadores.class).equalTo("_nombre", nombre).findFirst();
                Creadores creador;
                if (result == null) {


                    creador = realm.createObject(Creadores.class);
                    creador.set_nombre(nombre);
                } else {
                    creador = result;
                }
                creador.set_link(link);
                creador.set_firma(firma);
            }
        });


        return obtenerCreadorPorNombre(context, nombre);
    }

    public static Creadores obtenerCreadorPorNombre(Context context, String nombre) {
        Realm realm = UniversalModel.crearConexion(context);

        Creadores _resultado = realm.where(Creadores.class).equalTo("_nombre", nombre).findFirst();

        return _resultado;
    }

    public static RealmResults<Creadores> obtenerTodas(Context context) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Creadores> _resultado = realm.where(Creadores.class).findAll();

        return _resultado;

    }

    public static RealmResults<Creadores> obtenerTodasXNombre(Context context, boolean asc) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Creadores> _resultado = realm.where(Creadores.class).findAllSorted("_nombre", (asc ? Sort.ASCENDING : Sort.DESCENDING));

        return _resultado;

    }


}
