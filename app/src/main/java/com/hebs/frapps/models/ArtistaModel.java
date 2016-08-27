package com.hebs.frapps.models;

import android.content.Context;

import com.hebs.frapps.models.modelsRealm.Artistas;

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
public class ArtistaModel {
    public static Artistas crearActualizarArtista(Context context, final String nombre, final String link, final String firma) {
        Realm realm = UniversalModel.crearConexion(context);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Artistas result = realm.where(Artistas.class).equalTo("_nombre", nombre).findFirst();
                Artistas artista;
                if (result == null) {


                    artista = realm.createObject(Artistas.class);
                    artista.set_nombre(nombre);
                } else {
                    artista = result;
                }
                artista.set_link(link);
                artista.set_firma(firma);
            }
        });


        return obtenerArtistaPorNombre(context, nombre);
    }

    public static Artistas obtenerArtistaPorNombre(Context context, String nombre) {
        Realm realm = UniversalModel.crearConexion(context);

        Artistas _resultado = realm.where(Artistas.class).equalTo("_nombre", nombre).findFirst();

        return _resultado;
    }

    public static RealmResults<Artistas> obtenerTodas(Context context) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Artistas> _resultado = realm.where(Artistas.class).findAll();

        return _resultado;

    }

    public static RealmResults<Artistas> obtenerTodasXNombre(Context context, boolean asc) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Artistas> _resultado = realm.where(Artistas.class).findAllSorted("_nombre", (asc ? Sort.ASCENDING : Sort.DESCENDING));

        return _resultado;

    }


}
