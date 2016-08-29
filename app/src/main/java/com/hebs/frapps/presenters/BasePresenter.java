package com.hebs.frapps.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView;

import com.hebs.frapps.R;
import com.hebs.frapps.models.BusquedaModel;
import com.hebs.frapps.models.modelsRealm.ParBusqueda;
import com.hebs.frapps.views.Activity_App_Detalle_;
import com.hebs.frapps.views.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 28/8/2016
 * Time: 4:20 PM
 */
public class BasePresenter {
    ArrayList<ParBusqueda> _sugerencias;
    private BaseActivity _view;

    public BasePresenter(BaseActivity view) {
        this._view = view;

    }

    public static void irDetalleApp(Context _context, int tag) {
        Activity_App_Detalle_.intent(_context).flags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK).start();
        ((Activity) _context).overridePendingTransition(0, 0); //0 for no animation
    }

    public BaseActivity get_view() {
        return _view;
    }

    public void set_view(BaseActivity _view) {
        this._view = _view;
    }

    public void cargarBuscador(final SearchView searchView) {


        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {

            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {


                if (_sugerencias.get(position).get_id() == -1) {
                    //Le dio click a una sugerencia
                    BusquedaModel.crearActualizarBusqueda(get_view(), _sugerencias.get(position).getNombre());

                    //Ir a la pagina de busqueda de apps
                } else {
                    //Le dio click a una app


                    //Ir a la pagina de la app
                }

                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                BusquedaModel.crearActualizarBusqueda(get_view(), query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                String[] columnNames = {"_id", "nombre"};
                MatrixCursor cursor = new MatrixCursor(columnNames);

                _sugerencias = BusquedaModel.obtenerTodasNombre(get_view(), query, cursor);


                String[] from = {"text"};
                SimpleCursorAdapter busStopCursorAdapter = new SimpleCursorAdapter(get_view(),
                        android.R.layout.simple_list_item_1,
                        cursor,
                        new String[]{"nombre"},
                        new int[]{android.R.id.text1}, 1);

                SearchView.SearchAutoComplete autoCompleteTextView = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
                if (autoCompleteTextView != null) {

                    autoCompleteTextView.setDropDownBackgroundDrawable(ContextCompat.getDrawable(get_view(), R.drawable.abc_popup_background_mtrl_mult));
                }

                searchView.setSuggestionsAdapter(busStopCursorAdapter);

                return false;
            }


        });
    }
}
