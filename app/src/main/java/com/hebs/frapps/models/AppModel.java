package com.hebs.frapps.models;

import android.content.Context;

import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.models.modelsRealm.Artistas;
import com.hebs.frapps.models.modelsRealm.Categorias;

import java.util.Date;

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
public class AppModel {
    public static Apps crearApp(Context context, final Categorias categoria,
                                final int id,
                                final String nombre,
                                final String descripcion,
                                final String link,
                                final Date fechaCreacion,
                                final Artistas creador,
                                final String icono,
                                final String imagen_pequena,
                                final String imagen) {

        Realm realm = UniversalModel.crearConexion(context);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Apps result = realm.where(Apps.class).equalTo("_id", id).findFirst();
                Apps app;
                if (result == null) {
                    app = realm.createObject(Apps.class);
                    app.set_id(id);
                } else {
                    app = result;
                }
                app.set_nombre(nombre);
                app.set_descripcion(descripcion);
                app.set_link(link);
                app.set_fechaCreacion(fechaCreacion);
                app.set_creador(creador);
                app.set_icono(icono);
                app.set_imagen(imagen);
                app.set_imagen_pequena(imagen_pequena);
                app.set_categoria(categoria);
            }
        });


        return obtenerAppPorId(context, id);
    }

    public static Apps obtenerAppPorId(Context context, int id) {
        Realm realm = UniversalModel.crearConexion(context);

        Apps _resultado = realm.where(Apps.class).equalTo("_id", id).findFirst();

        return _resultado;
    }

    public static Apps obtenerCategoriaPorNombre(Context context, String nombre) {
        Realm realm = UniversalModel.crearConexion(context);

        Apps _resultado = realm.where(Apps.class).equalTo("_nombre", nombre).findFirst();

        return _resultado;
    }


    public static RealmResults<Apps> obtenerTodas(Context context) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Apps> _resultado = realm.where(Apps.class).findAll();

        return _resultado;

    }

    public static RealmResults<Apps> obtenerTodasXNombre(Context context, boolean asc) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Apps> _resultado = realm.where(Apps.class).findAllSorted("_nombre", (asc ? Sort.ASCENDING : Sort.DESCENDING));

        return _resultado;

    }

    public static RealmResults<Apps> obtenerTodasXFecha(Context context, boolean asc) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Apps> _resultado = realm.where(Apps.class).findAllSorted("_fechaCreacion", (asc ? Sort.ASCENDING : Sort.DESCENDING));

        return _resultado;

    }
}
