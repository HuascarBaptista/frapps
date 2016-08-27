package com.hebs.frapps.models;

import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.models.modelsRealm.Artistas;
import com.hebs.frapps.models.modelsRealm.Categorias;

import io.realm.RealmResults;


/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 26/8/2016
 * Time: 3:18 AM
 */
public class InformacionAlmacenada {
    private RealmResults<Artistas> _artistas;
    private RealmResults<Categorias> _categorias;
    private RealmResults<Apps> _apps;

    public InformacionAlmacenada() {
    }

    public RealmResults<Apps> get_apps() {

        return _apps;
    }

    public void set_apps(RealmResults<Apps> _apps) {
        this._apps = _apps;
    }

    public RealmResults<Categorias> get_categorias() {
        return _categorias;
    }

    public void set_categorias(RealmResults<Categorias> _categorias) {
        this._categorias = _categorias;
    }

    public RealmResults<Artistas> get_artistas() {
        return _artistas;
    }

    public void set_artistas(RealmResults<Artistas> _artistas) {
        this._artistas = _artistas;
    }

}
