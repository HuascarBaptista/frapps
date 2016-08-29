package com.hebs.frapps.models.modelsRealm;

import org.parceler.Parcel;

import java.util.Date;

import io.realm.AppsRealmProxy;
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
@Parcel(implementations = {AppsRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Apps.class})
public class Apps extends RealmObject {
    @PrimaryKey
    private int _id;

    @Required
    @Index
    private String _nombre;
    private String _nombreLargo;
    private Categorias _categoria;
    private String _descripcion;
    private String _link;
    private Date _fechaCreacion;
    private Creadores _creador;
    private String _icono;
    private String _imagen;
    private String _imagen_pequena;

    public Apps() {

    }

    public String get_nombreLargo() {
        return _nombreLargo;
    }

    public void set_nombreLargo(String _nombreLargo) {
        this._nombreLargo = _nombreLargo;
    }

    public String get_imagen_pequena() {
        return _imagen_pequena;
    }

    public void set_imagen_pequena(String _imagen_pequena) {
        this._imagen_pequena = _imagen_pequena;
    }

    public String get_icono() {
        return _icono;
    }

    public void set_icono(String _icono) {
        this._icono = _icono;
    }

    public String get_imagen() {
        return _imagen;
    }

    public void set_imagen(String _imagen) {
        this._imagen = _imagen;
    }

    public Categorias get_categoria() {
        return _categoria;
    }

    public void set_categoria(Categorias _categoria) {
        this._categoria = _categoria;
    }


    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String get_link() {
        return _link;
    }

    public void set_link(String _link) {
        this._link = _link;
    }

    public Date get_fechaCreacion() {
        return _fechaCreacion;
    }

    public void set_fechaCreacion(Date _fechaCreacion) {
        this._fechaCreacion = _fechaCreacion;
    }

    public Creadores get_creador() {
        return _creador;
    }

    public void set_creador(Creadores _creador) {
        this._creador = _creador;
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
