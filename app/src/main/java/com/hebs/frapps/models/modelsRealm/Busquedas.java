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
public class Busquedas extends RealmObject {
    private long _time;

    @PrimaryKey
    private String _nombre;

    public long get_time() {
        return _time;
    }

    public void set_time(long _time) {
        this._time = _time;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }
}
