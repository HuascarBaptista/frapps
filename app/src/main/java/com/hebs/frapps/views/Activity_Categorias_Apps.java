package com.hebs.frapps.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hebs.frapps.R;
import com.hebs.frapps.models.modelsRealm.Categorias;
import com.hebs.frapps.presenters.CategoriasAppsPresenter;
import com.hebs.frapps.utils.MyShared_;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.annotations.sharedpreferences.Pref;

import io.realm.RealmResults;

@EActivity(R.layout.activity_categorias_apps)
public class Activity_Categorias_Apps extends BaseActivity {
    @StringRes(R.string.servicio_top_free_apps)
    public
    String servicio_top_free_apps;

    @ViewById(R.id.relative_superior)
    public View vista_superior;

    @Pref
    public MyShared_ myPrefs;

    private CategoriasAppsPresenter categoriasAppsPresenter;

    @Background
    public void cargarInformacion() {
        categoriasAppsPresenter = new CategoriasAppsPresenter(this);
        categoriasAppsPresenter.obtenerInformacion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargandoCustomDialog(true);
        cargarInformacion();
    }

    @UiThread
    public void actualizarInformacion(RealmResults<Categorias> categorias) {

        if (categorias != null)
            for (int i = 0; i < categorias.size(); i++) {
                Log.e("Titulo", categorias.get(i).get_nombre());
            }
        cargandoCustomDialog(false);
    }
}
