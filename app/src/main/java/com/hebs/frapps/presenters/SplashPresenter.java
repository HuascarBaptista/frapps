package com.hebs.frapps.presenters;

import com.hebs.frapps.models.CategoriaModel;
import com.hebs.frapps.views.Activity_Splash;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 3:51 AM
 */
public class SplashPresenter {
    private Activity_Splash _view;

    public SplashPresenter(Activity_Splash view) {
        this._view = view;

    }

    public Activity_Splash get_view() {
        return _view;
    }

    public void set_view(Activity_Splash _view) {
        this._view = _view;
    }


}
