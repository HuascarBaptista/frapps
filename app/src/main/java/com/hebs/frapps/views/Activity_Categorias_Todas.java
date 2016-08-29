package com.hebs.frapps.views;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.hebs.frapps.R;
import com.hebs.frapps.presenters.CategoriasAppsPresenter;
import com.hebs.frapps.utils.MyShared_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.annotations.sharedpreferences.Pref;

@Fullscreen
@WindowFeature({Window.FEATURE_NO_TITLE})
@EActivity(R.layout.activity_categorias_apps)
public class Activity_Categorias_Todas extends BaseActivity {

    @ViewById(R.id.recyclerview)
    public static RecyclerView recyclerView;
    @StringRes(R.string.servicio_top_free_apps)
    public
    String servicio_top_free_apps;
    @ViewById(R.id.relative_superior)
    public View vista_superior;
    public RecyclerView.Adapter recyclerAdapter;
    @Pref
    public MyShared_ myPrefs;
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;
    private CategoriasAppsPresenter categoriasAppsPresenter;

    @Background
    public void cargarInformacion() {
        categoriasAppsPresenter = new CategoriasAppsPresenter(this);
        categoriasAppsPresenter.obtenerInformacion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setDrawer(drawer);
        super.cargarAppBar();
    }

    @UiThread
    public void informacionActualizada() {


        cargandoCustomDialog(false);
    }


    @AfterViews
    public void cargarAppBar() {
        cargandoCustomDialog(true);
        cargarInformacion();


    }



}
