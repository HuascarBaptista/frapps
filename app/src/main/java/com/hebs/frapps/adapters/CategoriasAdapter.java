package com.hebs.frapps.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.hebs.frapps.R;
import com.hebs.frapps.models.AppModel;
import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.presenters.BasePresenter;
import com.hebs.frapps.presenters.DesarrolladoresGeneralesPresenter;
import com.hebs.frapps.utils.GridAutofitLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;

import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 28/8/2016
 * Time: 7:02 PM
 */

//Me carga la vista vertical de las categorias
public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.RecyclerViewHoldersOutside> {
    private final Activity _context;
    ArrayList<String> data;
    HashMap<String, ArrayList<Apps>> dataApps;
    boolean _grid = false;
    boolean desarrolladores = false;
    private int lastPosition = -1;

    public CategoriasAdapter(Activity context, ArrayList<String> data, boolean grid, HashMap<String, ArrayList<Apps>> dataApps, boolean desarrolladores) {
        this.dataApps = dataApps;
        this.data = data;
        this._grid = grid;
        this.desarrolladores = desarrolladores;
        this._context = context;

        if (desarrolladores) {
            this.dataApps = DesarrolladoresGeneralesPresenter.obtenerSoloApps(context, this.dataApps);
        }
    }

    @Override
    public RecyclerViewHoldersOutside onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recycler_categorias, null);
        RecyclerViewHoldersOutside rcv = new RecyclerViewHoldersOutside(layoutView, _grid);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHoldersOutside holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(_context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_up);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

        holder.tituloCategoria.setText(data.get(position));

        holder._adapter.setRowIndex(position);
        if (desarrolladores) {
            holder._adapter.setData(AppModel.buscarAppPorCreador(_context, data.get(position)));

        } else {

            holder._adapter.setData(dataApps.get(data.get(position)));
        }

        holder.itemView.setTag(data.get(position));

        if (!desarrolladores) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    BasePresenter.irCategorias(_context, (String) view.getTag());

                }
            });
        } else {

            holder.itemView.findViewById(R.id.verMas).setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    //Para crear animacion del scroll up
    @Override
    public void onViewDetachedFromWindow(RecyclerViewHoldersOutside holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
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

            //Tengo dos posibles layouts y dos animaciones difernetes
            if (grid) {
                recyclerViewInside.setLayoutManager(new GridAutofitLayoutManager(context, 0));
                recyclerViewInside.setItemAnimator(new LandingAnimator());

            } else {
                recyclerViewInside.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerViewInside.setItemAnimator(new SlideInLeftAnimator());

            }

            _adapter = new CategoriasInsideAdapter(_context);
            recyclerViewInside.setItemAnimator(new SlideInLeftAnimator());

            recyclerViewInside.setAdapter(_adapter);

            tituloCategoria = (TextView) itemView.findViewById(R.id.tituloCategoria);
            recyclerViewInside = (RecyclerView) itemView.findViewById(R.id.recyclerviewInside);

        }

    }
}
