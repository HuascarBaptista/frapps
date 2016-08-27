package com.hebs.frapps.utils;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 26/8/2016
 * Time: 1:18 AM
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface MyShared {

    // The field name will have default value "John"
    @DefaultString("SinInfo")
    String json_top_free_apps();

}