package com.hebs.frapps.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hebs.frapps.R;
import com.hebs.frapps.models.AppModel;
import com.hebs.frapps.models.modelsRealm.Categorias;
import com.hebs.frapps.utils.GridAutofitLayoutManager;

import io.realm.RealmResults;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 28/8/2016
 * Time: 7:02 PM
 */
public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.RecyclerViewHoldersOutside> {
    private final Context _context;
    RealmResults<Categorias> data;
    boolean _grid = false;

    public CategoriasAdapter(Context context, RealmResults<Categorias> data, boolean grid) {
        this.data = data;
        this._grid = grid;
        this._context = context;
    }

    @Override
    public RecyclerViewHoldersOutside onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recycler_categorias, null);
        RecyclerViewHoldersOutside rcv = new RecyclerViewHoldersOutside(layoutView, _grid);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHoldersOutside holder, int position) {


        holder.tituloCategoria.setText(data.get(position).get_nombre());

        holder._adapter.setData(AppModel.obtenerTodasXNombreDeCategoria(_context, data.get(position).get_nombre())); // List of Strings
        holder._adapter.setRowIndex(position);

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }


    public class RecyclerViewHoldersOutside extends RecyclerView.ViewHolder {

        public TextView tituloCategoria;
        public RecyclerView recyclerViewInside;
        public boolean _grid = false;
        public CategoriasInsideAdapter _adapter;

        public RecyclerViewHoldersOutside(View itemView, boolean grid) {
            super(itemView);

            Context context = itemView.getContext();
            recyclerViewInside = (RecyclerView) itemView.findViewById(R.id.recyclerviewInside);

            if (grid) {
                recyclerViewInside.setLayoutManager(new GridAutofitLayoutManager(context, 0));
            } else {
                recyclerViewInside.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            }
            _adapter = new CategoriasInsideAdapter(context);

            recyclerViewInside.setAdapter(_adapter);

            tituloCategoria = (TextView) itemView.findViewById(R.id.tituloCategoria);
            recyclerViewInside = (RecyclerView) itemView.findViewById(R.id.recyclerviewInside);

        }

    }
}
