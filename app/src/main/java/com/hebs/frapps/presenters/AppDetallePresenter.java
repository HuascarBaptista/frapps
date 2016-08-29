package com.hebs.frapps.presenters;


import android.support.design.widget.FloatingActionButton;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hebs.frapps.models.AppModel;
import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.views.Activity_App_Detalle;
import com.koushikdutta.ion.Ion;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 28/8/2016
 * Time: 11:00 PM
 */
public class AppDetallePresenter {
    private Activity_App_Detalle _view;

    public AppDetallePresenter(Activity_App_Detalle view) {
        this._view = view;

    }

    public Activity_App_Detalle get_view() {
        return _view;
    }

    public void set_view(Activity_App_Detalle _view) {
        this._view = _view;
    }


    public boolean llenarInformacion(int idApp, TextView titulo_app, ImageView icono_app, TextView desarrollador_app, TextView descripcion, FloatingActionButton compartir) {
        Apps _app = AppModel.obtenerAppPorId(get_view(), idApp);

        if (_app != null) {
            titulo_app.setText(_app.get_nombre());
            descripcion.setText(_app.get_descripcion());
            desarrollador_app.setText(_app.get_creador().get_firma());
            //.name("benotto.realm")
            if (!_app.get_imagen().equals(""))
                Ion.with(icono_app)
                        .animateIn(AnimationUtils.loadAnimation(get_view(), android.R.anim.fade_in))
                        .load(_app.get_imagen());

            return true;
        }
        return false;

    }
}