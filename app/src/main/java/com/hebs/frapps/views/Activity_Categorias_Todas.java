package com.hebs.frapps.views;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.hebs.frapps.R;
import com.hebs.frapps.presenters.CategoriasTodasPresenter;
import com.hebs.frapps.utils.MyShared_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.annotations.sharedpreferences.Pref;

@Fullscreen
@WindowFeature({Window.FEATURE_NO_TITLE})
@EActivity(R.layout.activity_categorias_todas)
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
    @ViewById
    LinearLayout linear_actualizar;
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById
    AppBarLayout appbarLayout;

    private CategoriasTodasPresenter categoriasTodasPresenter;

    //Usarlop para cuano no haya internet y no tenia data
    @Click(R.id.linear_actualizar)
    public void actualizarNuevamente() {
        cargarData();
    }

    public void cargarInformacion() {
        if (categoriasTodasPresenter == null)
            categoriasTodasPresenter = new CategoriasTodasPresenter(this);
        categoriasTodasPresenter.obtenerInformacion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowInAnimations();

    }

    //Ve si pudo cargar la data o si tiene q mostrar el boton de actualizar
    @UiThread
    public void informacionActualizada(boolean data) {

        if (data) {
            linear_actualizar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            appbarLayout.setVisibility(View.VISIBLE);

            super.setDrawer(drawer);
            super.cargarAppBar(true, getString(R.string.categorias));
        } else {
            linear_actualizar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            appbarLayout.setVisibility(View.GONE);

        }

        cargandoCustomDialog(false);
    }


    @AfterViews
    public void cargarData() {


        cargandoCustomDialog(true);
        cargarInformacion();


    }



}
