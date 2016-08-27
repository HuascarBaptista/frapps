package com.hebs.frapps.presenters;

import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hebs.frapps.models.AppModel;
import com.hebs.frapps.models.ArtistaModel;
import com.hebs.frapps.models.CategoriaModel;
import com.hebs.frapps.models.modelsRealm.Artistas;
import com.hebs.frapps.models.modelsRealm.Categorias;
import com.hebs.frapps.views.Activity_Categorias_Apps;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.novoda.merlin.MerlinsBeard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:51 AM
 */
public class CategoriasAppsPresenter {

    private Activity_Categorias_Apps _view;
    private MerlinsBeard merlinsBeard;


    public CategoriasAppsPresenter(Activity_Categorias_Apps view) {
        this._view = view;

    }

    public Activity_Categorias_Apps get_view() {
        return _view;
    }

    public void set_view(Activity_Categorias_Apps _view) {
        this._view = _view;
    }

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
                                    Toast.makeText(get_view(), "El servicio no emitió respuesta alguna.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(get_view(), "Lo sentimos hubo un error en el servicio. " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            get_view().actualizarInformacion(CategoriaModel.obtenerTodasXNombre(get_view(), true));


                        }
                    });
        } else {
            Snackbar.make(get_view().vista_superior, "No posee conexión a internet.", Snackbar.LENGTH_LONG)
                    .show();
            //Llamar a la vista para que actualice
            get_view().actualizarInformacion(CategoriaModel.obtenerTodasXNombre(get_view(), true));
        }
    }


    private void guardarInformacion(JsonObject s) {
        if (s.has("feed"))
            if (s.getAsJsonObject("feed").has("entry")) {
                JsonArray _apps = s.getAsJsonObject("feed").getAsJsonArray("entry");
                JsonObject _app;
                Categorias _categoria;
                int id = -1;
                String nombre = "";
                String descripcion = "";
                String link = "";
                String fechaString = "";
                Date fechaCreacion;
                String nombreArtista = "";
                String linkArtista = "";
                String firmaArtista = "";
                Artistas artista;
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
                    nombreArtista = "";
                    linkArtista = "";
                    firmaArtista = "";
                    artista = null;
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
                    if (_app.has("title")) {

                        if (_app.getAsJsonObject("title").has("label")) {
                            nombre = _app.getAsJsonObject("title").get("label").getAsString();
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
                            nombreArtista = _app.getAsJsonObject("im:artist").get("label").getAsString();
                            if (_app.getAsJsonObject("im:artist").has("attributes")) {
                                if (_app.getAsJsonObject("im:artist").getAsJsonObject("attributes").has("href")) {
                                    linkArtista = _app.getAsJsonObject("im:artist").getAsJsonObject("attributes").get("href").getAsString();
                                }
                            }

                            if (_app.has("rights")) {

                                if (_app.getAsJsonObject("rights").has("label")) {
                                    firmaArtista = _app.getAsJsonObject("rights").get("label").getAsString();
                                }
                            }
                            artista = ArtistaModel.crearActualizarArtista(get_view(), nombreArtista, linkArtista, firmaArtista);
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

                    AppModel.crearApp(get_view(), _categoria, id, nombre, descripcion, link, fechaCreacion, artista, icono, imagen_pequena,imagen);

                }
                get_view().myPrefs.json_top_free_apps().put(s.toString());
            }

    }

    /*public void pruebaAgregarCategoria(){
        CategoriaModel.crearActualizarCategorica(get_view(),1,"Probando ");
        CategoriaModel.crearActualizarCategoria(get_view(),2,"Lol ");
        CategoriaModel.crearActualizarCategoria(get_view(),3,"Tercero");
        CategoriaModel.crearActualizarCategoria(get_view(),2,"Pap ");
    }*/
}
