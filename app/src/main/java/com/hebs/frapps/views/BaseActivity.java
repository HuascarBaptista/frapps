package com.hebs.frapps.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.hebs.frapps.R;
import com.hebs.frapps.presenters.BasePresenter;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 26/8/2016
 * Time: 3:34 AM
 */

public class BaseActivity extends AppCompatActivity {
    public ProgressDialog progressDialog;
    public Dialog dialog;
    protected BasePresenter basePresenter;
    DrawerLayout drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }
        basePresenter = new BasePresenter(this);
    }

    void cargandoDialog(boolean activo) {
        if (activo) {
            progressDialog = progressDialog.show(this, "Cargando",
                    "Espere un momento por favor...", true);
        } else {
            if (progressDialog != null)
                progressDialog.dismiss();
        }
    }

    public void cargandoCustomDialog(boolean activo) {
        if (activo) {
            dialog = new Dialog(this, R.style.myCoolDialog);
            // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_cargando_informacion);
            //  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setTitle("Cargando");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();


            AVLoadingIndicatorView avi = (AVLoadingIndicatorView) dialog.findViewById(R.id.avi);
            avi.smoothToShow();
        } else {
            if (dialog != null)
                dialog.dismiss();
        }

    }


    public void cargarAppBar() {
        //App bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }


    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_toolbar, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        basePresenter.cargarBuscador(searchView);


        return true;
    }


}
