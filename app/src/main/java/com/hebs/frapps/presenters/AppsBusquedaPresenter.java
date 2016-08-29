package com.hebs.frapps.presenters;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.hebs.frapps.R;
import com.hebs.frapps.adapters.CategoriaDetalleAdapter;
import com.hebs.frapps.models.AppModel;
import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.utils.GridAutofitLayoutManager;
import com.hebs.frapps.views.Activity_Apps_Busqueda;
import com.hebs.frapps.views.FragmentCategoria;
import com.novoda.merlin.MerlinsBeard;

import java.util.ArrayList;

import io.realm.RealmResults;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:51 AM
 */
public class AppsBusquedaPresenter {

    private Activity_Apps_Busqueda _view;
    private MerlinsBeard merlinsBeard;


    public AppsBusquedaPresenter(Activity_Apps_Busqueda view) {
        this._view = view;

    }

    public Activity_Apps_Busqueda get_view() {
        return _view;
    }

    public void set_view(Activity_Apps_Busqueda _view) {
        this._view = _view;
    }


    public void cargarDataFragment(View view, ArrayList<Apps> data, String categoria, FragmentCategoria _este) {
        _este.recyclerView.setHasFixedSize(true);

        //Si es tablet entonces grid si no entonces linear


        boolean _grid = false;
        if (_este.getResources().getBoolean(R.bool.portrait_only)) {
            _grid = false;
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(_este.getContext(), LinearLayoutManager.VERTICAL, false);
            _este.recyclerView.setLayoutManager(layoutManager);
        } else {
            _grid = true;
            GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(_este.getContext(), 0);
            _este.recyclerView.setLayoutManager(layoutManager);

        }

        _este.recyclerView.setItemAnimator(new DefaultItemAnimator());

        _este.recyclerAdapter = new CategoriaDetalleAdapter(_view, data);
        _este.recyclerView.setItemAnimator(new SlideInLeftAnimator());

        _este.recyclerView.setAdapter(_este.recyclerAdapter);

    }

    public void cargarDataFragment(ArrayList<Apps> data, String categoria) {
        get_view().recyclerView.setHasFixedSize(true);

        //Si es tablet entonces grid si no entonces linear


        boolean _grid = false;
        //Observo si es celular o tablet y muestro los lementos depende de eso
        if (get_view().getResources().getBoolean(R.bool.portrait_only)) {
            _grid = false;
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(get_view(), LinearLayoutManager.VERTICAL, false);
            get_view().recyclerView.setLayoutManager(layoutManager);
        } else {
            _grid = true;
            GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(get_view(), 0);
            get_view().recyclerView.setLayoutManager(layoutManager);

        }

        get_view().recyclerView.setItemAnimator(new DefaultItemAnimator());

        get_view().recyclerAdapter = new CategoriaDetalleAdapter(get_view(), data);
        //Seteo la animacion
        get_view().recyclerView.setItemAnimator(new SlideInLeftAnimator());

        get_view().recyclerView.setAdapter(get_view().recyclerAdapter);

    }

    //Busco la informacion y la mando a actualizar
    public void manejarInformacion(Context baseContext, ArrayList<Apps> _data, String buscarPor) {
        obtenerInformacionEnArrayList(baseContext, _data, buscarPor);
        cargarDataFragment(_data, "");
        get_view().informacionActualizada();

    }

    public ArrayList<Apps> obtenerInformacionEnArrayList(Context context, ArrayList<Apps> data, String buscarPor) {

        RealmResults<Apps> _todas = AppModel.obtenerAppsPorNombre(context, buscarPor);


        if (_todas != null) {
            if (data == null)
                data = new ArrayList<>();

            for (int i = 0; i < _todas.size(); i++) {

                data.add(_todas.get(i));

            }
            return data;


        }
        return null;
    }
}
