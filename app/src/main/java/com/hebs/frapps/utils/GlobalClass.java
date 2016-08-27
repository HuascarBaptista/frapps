package com.hebs.frapps.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 25/8/2016
 * Time: 4:01 AM
 */
public class GlobalClass extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                                .withMetaTables()
                                .withLimit(99999)
                                .withDescendingOrder()
                                .build())
                        .build());


    }

}
