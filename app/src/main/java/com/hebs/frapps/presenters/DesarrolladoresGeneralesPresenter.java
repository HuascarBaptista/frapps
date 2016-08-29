package com.hebs.frapps.presenters;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.animation.OvershootInterpolator;

import com.hebs.frapps.R;
import com.hebs.frapps.adapters.CategoriasGeneralAdapter;
import com.hebs.frapps.models.AppModel;
import com.hebs.frapps.models.CreadorModel;
import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.models.modelsRealm.Creadores;
import com.hebs.frapps.views.Activity_Desarrolladores_Generales;
import com.novoda.merlin.MerlinsBeard;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.RealmResults;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:51 AM
 */
public class DesarrolladoresGeneralesPresenter {

    private Activity_Desarrolladores_Generales _view;
    private MerlinsBeard merlinsBeard;


    public DesarrolladoresGeneralesPresenter(Activity_Desarrolladores_Generales view) {
        this._view = view;

    }

    //Obtener solo los titulos de los desarrolladores
    public static void obtenerSoloTitulos(Context context, ArrayList<String> titulos) {
        RealmResults<Creadores> _categorias = CreadorModel.obtenerTodasXNombre(context, true);
        if (_categorias != null) {


            for (int i = 0; i < _categorias.size(); i++) {
                titulos.add(_categorias.get(i).get_nombre());

            }
        }
    }

    //Obtener solo las aplicaciones
    public static HashMap<String, ArrayList<Apps>> obtenerSoloApps(Context context, HashMap<String, ArrayList<Apps>> data) {

        RealmResults<Apps> _todas = AppModel.obtenerTodasXNombre(context, true);


        if (_todas != null) {
            ArrayList<Apps> temp;
            ArrayList<Apps> todas = new ArrayList<>();
            int limite = _todas.size();

            for (int i = 0; i < _todas.size(); i++) {

                temp = data.get(_todas.get(i).get_creador().get_nombre());

                if (temp == null) {

                    temp = new ArrayList<>();
                }
                if (temp.size() < limite) {
                    temp.add(_todas.get(i));
                    todas.add(_todas.get(i));
                    data.put(_todas.get(i).get_creador().get_nombre(), temp);

                }

            }
            data.put(context.getString(R.string.todas), todas);


        }
        return data;
    }

    public Activity_Desarrolladores_Generales get_view() {
        return _view;
    }

    public void set_view(Activity_Desarrolladores_Generales _view) {
        this._view = _view;
    }

    public void obtenerInformacion() {
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
        obtenerSoloTitulos(get_view(), _titulos);

        //Creo la animacion
        get_view().recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));


//Inicializo la vista de los desarrolladores, igual q la de categorias Todas
        get_view().recyclerAdapter = new CategoriasGeneralAdapter(get_view(), _titulos, _grid, _hashDeApps, true);
        get_view().recyclerView.setAdapter(get_view().recyclerAdapter);

        get_view().informacionActualizada();

    }
}
