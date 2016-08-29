package com.hebs.frapps.presenters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hebs.frapps.R;
import com.hebs.frapps.adapters.CategoriasGeneralAdapter;
import com.hebs.frapps.models.AppModel;
import com.hebs.frapps.models.CategoriaModel;
import com.hebs.frapps.models.CreadorModel;
import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.models.modelsRealm.Categorias;
import com.hebs.frapps.models.modelsRealm.Creadores;
import com.hebs.frapps.views.Activity_Categorias_Todas;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.novoda.merlin.MerlinsBeard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:51 AM
 */
public class CategoriasTodasPresenter {

    private Activity_Categorias_Todas _view;
    private MerlinsBeard merlinsBeard;


    public CategoriasTodasPresenter(Activity_Categorias_Todas view) {
        this._view = view;

    }

    public Activity_Categorias_Todas get_view() {
        return _view;
    }

    public void set_view(Activity_Categorias_Todas _view) {
        this._view = _view;
    }

    //Pregunto si tengo internet y me descargo la data del server
    public void obtenerInformacion() {

        merlinsBeard = MerlinsBeard.from(this._view.getBaseContext());
        if (merlinsBeard.isConnected()) {
            //Descargar información del servidor
            Ion.with(get_view().getBaseContext())
                    .load(get_view().servicio_top_free_apps)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {

                            if (e == null) {
                                if (result != null) {
                                    // Si no hubo error entonces compruebo si la info la habia almacenado o si el json es diferente
                                    if (!get_view().myPrefs.json_top_free_apps().get().equals(result.toString())) {
                                        //No tengo esta info, procedo a guardarla
                                        guardarInformacion(result);

                                    }
                                } else {
                                    Toast.makeText(get_view(), get_view().getString(R.string.servicio_no_emitio), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(get_view(), get_view().getString(R.string.servicio_error) + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            manejarInformacion();


                        }
                    });
        } else {
            Snackbar.make(get_view().vista_superior, get_view().getString(R.string.sin_conexion), Snackbar.LENGTH_LONG)
                    .show();
            //Llamar a la vista para que actualice
            manejarInformacion();
        }
    }

    public void manejarInformacion() {
        get_view().recyclerView.setHasFixedSize(true);

        //Si es tablet entonces grid si no entonces linear
      /*
        GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(get_view(), 0);
        get_view().recyclerView.setLayoutManager(layoutManager);
         */

        boolean _grid = false;
        if (get_view().getResources().getBoolean(R.bool.portrait_only)) {
            _grid = false;
        } else {
            _grid = true;

        }
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(get_view(), LinearLayoutManager.VERTICAL, false);
        get_view().recyclerView.setLayoutManager(layoutManager);


        get_view().recyclerView.setItemAnimator(new DefaultItemAnimator());


        HashMap<String, ArrayList<Apps>> _hashDeApps = new HashMap<>();
        ArrayList<String> _titulos = new ArrayList<>();
        //Creo mi vista de dos recyclers, uno vertical y otro horizontal
        BasePresenter.obtenerInformacionEnHash(get_view(), _titulos, _hashDeApps, false, true, false);

        get_view().recyclerAdapter = new CategoriasGeneralAdapter(get_view(), _titulos, _grid, _hashDeApps, false);
        get_view().recyclerView.setItemAnimator(new SlideInUpAnimator());

        get_view().recyclerView.setAdapter(get_view().recyclerAdapter);

        //Depenjdeiendo de si tiene data o no
        if (_titulos.size() <= 0)
            get_view().informacionActualizada(false);
        else
            get_view().informacionActualizada(true);



    }

    //Guardo toda la info de las apps en una bd local
    private void guardarInformacion(JsonObject s) {
        if (s.has("feed"))
            if (s.getAsJsonObject("feed").has("entry")) {
                JsonArray _apps = s.getAsJsonObject("feed").getAsJsonArray("entry");
                JsonObject _app;
                Categorias _categoria;
                int id = -1;
                String nombre = "";
                String nombreLargo = "";
                String descripcion = "";
                String link = "";
                String fechaString = "";
                Date fechaCreacion;
                String nombreCreador = "";
                String linkCreador = "";
                String firmaCreador = "";
                Creadores creador;
                String icono = "";
                String imagen = "";
                String imagen_pequena="";

                for (int i = 0; i < _apps.size(); i++) {
                    _app = _apps.get(i).getAsJsonObject();
                    _categoria = null;
                    id = -1;
                    nombre = "";
                    descripcion = "";
                    link = "";
                    fechaString = "";
                    fechaCreacion = null;
                    nombreCreador = "";
                    linkCreador = "";
                    firmaCreador = "";
                    creador = null;
                    icono = "";
                    imagen = "";

                    //Guardo primero la categoría
                    if (_app.has("category")) {

                        if (_app.getAsJsonObject("category").has("attributes")) {
                            if (_app.getAsJsonObject("category").getAsJsonObject("attributes").has("im:id") && _app.getAsJsonObject("category").getAsJsonObject("attributes").has("label")) {
                                _categoria = CategoriaModel.crearActualizarCategoria(get_view(),
                                        _app.getAsJsonObject("category").getAsJsonObject("attributes").get("im:id").getAsInt(),
                                        _app.getAsJsonObject("category").getAsJsonObject("attributes").get("label").getAsString());
                            }
                        }
                    }
                    if (_app.has("id")) {

                        if (_app.getAsJsonObject("id").has("attributes")) {
                            if (_app.getAsJsonObject("id").getAsJsonObject("attributes").has("im:id")) {
                                id = _app.getAsJsonObject("id").getAsJsonObject("attributes").get("im:id").getAsInt();
                            }
                        }
                    }
                    if (_app.has("im:name")) {

                        if (_app.getAsJsonObject("im:name").has("label")) {
                            nombre = _app.getAsJsonObject("im:name").get("label").getAsString();
                        }
                    }
                    if (_app.has("title")) {

                        if (_app.getAsJsonObject("title").has("label")) {
                            nombreLargo = _app.getAsJsonObject("title").get("label").getAsString();
                        }
                    }
                    if (_app.has("summary")) {

                        if (_app.getAsJsonObject("summary").has("label")) {
                            descripcion = _app.getAsJsonObject("summary").get("label").getAsString();
                        }
                    }
                    if (_app.has("link")) {

                        if (_app.getAsJsonObject("link").has("attributes")) {
                            if (_app.getAsJsonObject("link").getAsJsonObject("attributes").has("href")) {
                                link = _app.getAsJsonObject("link").getAsJsonObject("attributes").get("href").getAsString();
                            }
                        }
                    }
                    if (_app.has("im:releaseDate")) {

                        if (_app.getAsJsonObject("im:releaseDate").has("label")) {
                            fechaString = _app.getAsJsonObject("im:releaseDate").get("label").getAsString();
                            String pattern = "yyyy-MM-dd'T'HH:mm:ss";
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            try {
                                fechaCreacion = sdf.parse(fechaString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (_app.has("im:artist")) {

                        if (_app.getAsJsonObject("im:artist").has("label")) {
                            nombreCreador = _app.getAsJsonObject("im:artist").get("label").getAsString();
                            if (_app.getAsJsonObject("im:artist").has("attributes")) {
                                if (_app.getAsJsonObject("im:artist").getAsJsonObject("attributes").has("href")) {
                                    linkCreador = _app.getAsJsonObject("im:artist").getAsJsonObject("attributes").get("href").getAsString();
                                }
                            }

                            if (_app.has("rights")) {

                                if (_app.getAsJsonObject("rights").has("label")) {
                                    firmaCreador = _app.getAsJsonObject("rights").get("label").getAsString();
                                }
                            }
                            creador = CreadorModel.crearActualizarCreador(get_view(), nombreCreador, linkCreador, firmaCreador);
                        }
                    }
                    if (_app.has("im:image")) {
                        if (_app.getAsJsonArray("im:image").size() >= 1) {
                            if (_app.getAsJsonArray("im:image").get(0).getAsJsonObject().has("label")) {
                                icono = _app.getAsJsonArray("im:image").get(0).getAsJsonObject().get("label").getAsString();
                            }
                        }
                        if (_app.getAsJsonArray("im:image").size() >= 2) {
                            if (_app.getAsJsonArray("im:image").get(1).getAsJsonObject().has("label")) {
                                imagen_pequena = _app.getAsJsonArray("im:image").get(1).getAsJsonObject().get("label").getAsString();
                            }
                        }
                        if (_app.getAsJsonArray("im:image").size() >= 3) {
                            if (_app.getAsJsonArray("im:image").get(2).getAsJsonObject().has("label")) {
                                imagen = _app.getAsJsonArray("im:image").get(2).getAsJsonObject().get("label").getAsString();
                            }
                        }

                    }

                    AppModel.crearApp(get_view(), _categoria, id, nombre, nombreLargo, descripcion, link, fechaCreacion, creador, icono, imagen_pequena, imagen);

                }
                get_view().myPrefs.json_top_free_apps().put(s.toString());
            }

    }


}
