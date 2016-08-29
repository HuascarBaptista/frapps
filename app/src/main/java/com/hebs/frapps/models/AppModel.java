package com.hebs.frapps.models;

import android.content.Context;

import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.models.modelsRealm.Categorias;
import com.hebs.frapps.models.modelsRealm.Creadores;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Case;
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
                                final String nombreLargo,
                                final String descripcion,
                                final String link,
                                final Date fechaCreacion,
                                final Creadores creador,
                                final String icono,
                                final String imagen_pequena,
                                final String imagen) {

        Realm realm = UniversalModel.crearConexion(context);

        //Guardo la informacion del servidor, o la actualizo dependeineod
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
                app.set_nombreLargo(nombreLargo);
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

    public static RealmResults<Apps> obtenerAppsPorNombre(Context context, String nombre) {
        Realm realm = UniversalModel.crearConexion(context);

        return realm.where(Apps.class).contains("_nombre", nombre, Case.INSENSITIVE).findAllSorted("_nombre");
    }


    public static RealmResults<Apps> obtenerTodas(Context context) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Apps> _resultado = realm.where(Apps.class).findAll();

        return _resultado;

    }

    public static RealmResults<Apps> obtenerTodasXNombreDeCategoria(Context context, String nombreCategoria) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Apps> _resultado = realm.where(Apps.class).equalTo("_categoria._nombre", nombreCategoria).findAllSorted("_nombre");

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

    public static ArrayList<Apps> buscarAppPorCreador(Context context, String creador) {
        Realm realm = UniversalModel.crearConexion(context);

        RealmResults<Apps> _resultado = realm.where(Apps.class).equalTo("_creador._nombre", creador).findAllSorted("_nombre");

        ArrayList<Apps> _apps = new ArrayList<>();
        for (int i = 0; i < _resultado.size(); i++) {
            _apps.add(_resultado.get(i));
        }
        return _apps;
    }
}
