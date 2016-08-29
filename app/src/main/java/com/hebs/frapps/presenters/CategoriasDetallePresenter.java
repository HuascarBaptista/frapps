package com.hebs.frapps.presenters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.hebs.frapps.R;
import com.hebs.frapps.adapters.CategoriaAdapter;
import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.utils.GridAutofitLayoutManager;
import com.hebs.frapps.views.FragmentCategoria;
import com.novoda.merlin.MerlinsBeard;

import java.util.ArrayList;
import java.util.HashMap;

import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:51 AM
 */
//Este es el presentador de las categorias q se muestran en general, con las pestañas
public class CategoriasDetallePresenter {

    private Activity _view;
    private MerlinsBeard merlinsBeard;


    public CategoriasDetallePresenter(Activity view) {
        this._view = view;

    }


    public Activity get_view() {
        return _view;
    }

    public void set_view(Activity _view) {
        this._view = _view;
    }


    public void cargarDataFragment(Activity view, ArrayList<Apps> data, String categoria, FragmentCategoria _este) {
        _este.recyclerView.setHasFixedSize(true);


        boolean _grid = false;
        //Si es tablet entonces grid si no entonces linear

        if (_este.getResources().getBoolean(R.bool.portrait_only)) {
            _grid = false;
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(_este.getContext(), LinearLayoutManager.VERTICAL, false);
            _este.recyclerView.setLayoutManager(layoutManager);
            _este.recyclerView.setItemAnimator(new SlideInLeftAnimator());

        } else {
            _grid = true;
            GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(_este.getContext(), 0);
            _este.recyclerView.setLayoutManager(layoutManager);
            _este.recyclerView.setItemAnimator(new LandingAnimator());


        }


        _este.recyclerAdapter = new CategoriaAdapter(get_view(), data);
        _este.recyclerView.setAdapter(_este.recyclerAdapter);

    }

    public void manejarInformacion(Context baseContext, ArrayList<String> _titulos, HashMap<String, ArrayList<Apps>> _data, boolean guardarTodas, boolean limit) {
        BasePresenter.obtenerInformacionEnHash(baseContext, _titulos, _data, guardarTodas, limit, false);
    }

}
