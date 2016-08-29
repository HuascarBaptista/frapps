package com.hebs.frapps.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hebs.frapps.R;
import com.hebs.frapps.presenters.AppDetallePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

@Fullscreen
@WindowFeature({Window.FEATURE_NO_TITLE})
@EActivity(R.layout.activity_detalle_app)
public class Activity_App_Detalle extends BaseActivity {

    @ViewById
    ImageView icono_app;
    @ViewById
    TextView titulo_app;
    @ViewById
    TextView desarrollador_app;
    @ViewById
    TextView descripcion;
    @ViewById
    FloatingActionButton compartir;

    @Extra
    int idApp;

    AppDetallePresenter appDetallePresenter;

    @AfterViews
    public void cargarInformacion() {
        cargandoDialog(true);
        appDetallePresenter = new AppDetallePresenter(this);
        boolean _cargo = appDetallePresenter.llenarInformacion(idApp, titulo_app, icono_app, desarrollador_app, descripcion, compartir);
        cargandoDialog(false);

        if (!_cargo) {
            Toast.makeText(this, "Esta aplicación es invalida", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setDrawer(drawer);
        super.cargarAppBar();
    }
}



