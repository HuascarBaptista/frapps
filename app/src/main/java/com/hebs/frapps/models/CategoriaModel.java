package com.hebs.frapps.models;

import android.content.Context;

import com.hebs.frapps.models.modelsRealm.Categorias;

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
public class CategoriaModel {
    public static Categorias crearActualizarCategoria(Context context, final int id, final String nombre) {
        Realm realm = UniversalModel.crearConexion(context);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Categorias result = realm.where(Categorias.class).equalTo("_id", id).findFirst();
                Categorias categoria;
                if (result == null) {


                    categoria = realm.createObject(Categorias.class);
                    categoria.set_id(id);
                } else {
                    categoria = result;
                }
                categoria.set_nombre(nombre);
                //  realm.copyToRealmOrUpdate(categoria);
            }
        });


        return obtenerCategoriaPorId(context, id);
    }

    public static Categorias obtenerCategoriaPorId(Context context, int id) {
        Realm realm = UniversalModel.crearConexion(context);

        Categorias _resultado = realm.where(Categorias.class).equalTo("_id", id).findFirst();

        return _resultado;
    }

    public static Categorias obtenerCategoriaPorNombre(Context context, String nombre) {
        Realm realm = UniversalModel.crearConexion(context);

        Categorias _resultado = realm.where(Categorias.class).equalTo("_nombre", nombre).findFirst();

        return _resultado;
    }

    public static RealmResults<Categorias> obtenerTodas(Context context) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Categorias> _resultado = realm.where(Categorias.class).findAll();

        return _resultado;

    }

    public static RealmResults<Categorias> obtenerTodasXNombre(Context context, boolean asc) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Categorias> _resultado = realm.where(Categorias.class).findAllSorted("_nombre", (asc ? Sort.ASCENDING : Sort.DESCENDING));

        return _resultado;

    }

}
