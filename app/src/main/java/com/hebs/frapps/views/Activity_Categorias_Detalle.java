package com.hebs.frapps.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.hebs.frapps.R;
import com.hebs.frapps.adapters.CategoriasFragmentPagerAdapter;
import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.presenters.CategoriasDetallePresenter;
import com.hebs.frapps.utils.MyShared_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;
import java.util.HashMap;

@Fullscreen
@WindowFeature({Window.FEATURE_NO_TITLE})
@EActivity(R.layout.activity_categorias_detalle)
public class Activity_Categorias_Detalle extends BaseActivity {

    @ViewById(R.id.relative_superior)
    public View vista_superior;
    public RecyclerView.Adapter recyclerAdapter;
    @Pref
    public MyShared_ myPrefs;
    @ViewById
    TabLayout appbartabs;

    @ViewById(R.id.viewpager)
    ViewPager viewPager;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    ArrayList<String> _titulos;
    HashMap<String, ArrayList<Apps>> _data;

    @Extra
    String nombreCategoria;

    private CategoriasDetallePresenter categoriasDetallePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowInAnimations();


    }

    @UiThread
    public void informacionActualizada() {


        cargandoCustomDialog(false);
    }


    @AfterViews
    public void cargarInformacionAppBar() {
        categoriasDetallePresenter = new CategoriasDetallePresenter(this);

        super.setDrawer(drawer);
        super.cargarAppBar(true, getString(R.string.categorias));

        //  cargandoCustomDialog(true);
        _titulos = new ArrayList<>();
        _data = new HashMap<>();

        categoriasDetallePresenter.manejarInformacion(getBaseContext(), _titulos, _data, true, false);


        //Sete el viewpager del los fragmnets
        viewPager.setAdapter(new CategoriasFragmentPagerAdapter(
                getSupportFragmentManager(), _titulos, _data));

        //La animacion del viewpager
        viewPager.setPageTransformer(true, new CubeOutTransformer());


        //Cargar una categoria inicial
        if (!nombreCategoria.equals("")) {
            for (int j = 0; j < _titulos.size(); j++) {
                if (nombreCategoria.equals(_titulos.get(j))) {
                    viewPager.setCurrentItem(j);
                }
            }
        }
        //Asigno q sea scroll horizontal y q agarre los titulos del viewpagar
        appbartabs.setupWithViewPager(viewPager);
        appbartabs.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

}
