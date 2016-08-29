package com.hebs.frapps.models.modelsRealm;

import org.parceler.Parcel;

import io.realm.CategoriasRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:17 AM
 */
@Parcel(implementations = {CategoriasRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Categorias.class})
public class Categorias extends RealmObject {
    @PrimaryKey
    private int _id;

    @Required
    @Index
    private String _nombre;


    public Categorias() {
    }

    public int get_id() {

        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }
}
