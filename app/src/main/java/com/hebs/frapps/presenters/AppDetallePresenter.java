package com.hebs.frapps.presenters;


import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hebs.frapps.R;
import com.hebs.frapps.models.AppModel;
import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.views.Activity_App_Detalle;
import com.koushikdutta.ion.Ion;
import com.novoda.merlin.MerlinsBeard;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 28/8/2016
 * Time: 11:00 PM
 */
public class AppDetallePresenter {
    private Activity_App_Detalle _view;
    private MerlinsBeard merlinsBeard;

    public AppDetallePresenter(Activity_App_Detalle view) {
        this._view = view;

    }

    public Activity_App_Detalle get_view() {
        return _view;
    }

    public void set_view(Activity_App_Detalle _view) {
        this._view = _view;
    }


    //Llenoi toda la infoirmacion y seteo los clicks
    public boolean llenarInformacion(int idApp, TextView titulo_app, ImageView icono_app, TextView desarrollador_app, TextView descripcion, FloatingActionButton compartir, TextView url_app) {
        final Apps _app = AppModel.obtenerAppPorId(get_view(), idApp);

        if (_app != null) {
            titulo_app.setText(_app.get_nombre());
            descripcion.setText(_app.get_descripcion());
            desarrollador_app.setText(_app.get_creador().get_firma());

            //Descaego la imagen
            if (!_app.get_imagen().equals("")) {

                merlinsBeard = MerlinsBeard.from(this._view.getBaseContext());
                if (merlinsBeard.isConnected()) {
                    Ion.with(icono_app)
                            .animateIn(AnimationUtils.loadAnimation(get_view(), android.R.anim.fade_in))
                            .load(_app.get_imagen());
                } else {
                    Ion.with(icono_app)
                            .animateIn(AnimationUtils.loadAnimation(get_view(), android.R.anim.fade_in))
                            .load(_app.get_icono());
                }
            }

            get_view().cambiarAppBarTitle(_app.get_nombre());


            //Es una url y la seteo para q la pueda abrir en un explorador
            url_app.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(_app.get_link()));
                    get_view().startActivity(i);
                }
            });

            //El action de compartir
            compartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = get_view().getString(R.string.compartirTexto, _app.get_nombre(), _app.get_creador().get_nombre(), _app.get_link());
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, _app.get_nombre() + " " + get_view().getString(R.string.app_name));
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    get_view().startActivity(Intent.createChooser(sharingIntent, get_view().getString(R.string.compartirPor)));
                }
            });

            return true;
        }
        return false;

    }
}