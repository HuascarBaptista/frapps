package com.hebs.frapps.models.modelsRealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:31 AM
 */
public class Artistas extends RealmObject {
    @PrimaryKey
    private String _nombre;

    private String _link;

    private String _firma;

    public Artistas() {
    }

    public String get_nombre() {

        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_link() {
        return _link;
    }

    public void set_link(String _link) {
        this._link = _link;
    }

    public String get_firma() {
        return _firma;
    }

    public void set_firma(String _firma) {
        this._firma = _firma;
    }
}
