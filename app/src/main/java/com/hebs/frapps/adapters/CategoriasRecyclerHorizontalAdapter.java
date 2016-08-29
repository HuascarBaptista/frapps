package com.hebs.frapps.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.hebs.frapps.R;
import com.hebs.frapps.models.modelsRealm.Apps;
import com.hebs.frapps.presenters.BasePresenter;
import com.hebs.frapps.utils.RecyclerViewHolders;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

//Me carga la vista horizaontal de las categorias
public class CategoriasRecyclerHorizontalAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private final Animation animFadeIn;
    private final Animation shake;
    public View _vista;
    public Activity _context;
    private ArrayList<Apps> mDataList;
    private int mRowIndex = -1;

    public CategoriasRecyclerHorizontalAdapter(Activity context) {
        this._context = context;
        animFadeIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);

        //Animacion de shake cuando se le da click
        shake = AnimationUtils.loadAnimation(context, R.anim.shakeanim);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                //Functionality here
                BasePresenter.irDetalleApp(_context, (int) _vista.getTag());

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void setData(ArrayList<Apps> data) {
        if (mDataList != data) {
            mDataList = data;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }


    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_card_app_en_categorias, parent, false);
        RecyclerViewHolders holder = new RecyclerViewHolders(itemView, false);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders rawHolder, int position) {
        RecyclerViewHolders holder = (RecyclerViewHolders) rawHolder;
        holder.titulo.setText(mDataList.get(position).get_nombre());
        holder.informacionAdicional.setText(mDataList.get(position).get_creador().get_nombre());

        String avatar_ = mDataList.get(position).get_imagen_pequena();

        //.name("benotto.realm")
        if (!avatar_.equals(""))
            Ion.with(holder.icono)
                    .animateIn(animFadeIn)
                    .load(avatar_).setCallback(new FutureCallback<ImageView>() {
                @Override
                public void onCompleted(Exception e, ImageView result) {
                    (((View) result.getParent()).findViewById(R.id.progressbar)).setVisibility(View.GONE);
                }
            });

        holder.itemView.setTag(mDataList.get(position).get_id());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                _vista = view;
                view.startAnimation(shake);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDataList != null)
        return mDataList.size();
        return 0;
    }

}