package com.hebs.frapps.adapters;

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

import io.realm.RealmResults;

public class CategoriasInsideAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private final Animation animFadeIn;
    public Context _context;
    private RealmResults<Apps> mDataList;
    private int mRowIndex = -1;

    public CategoriasInsideAdapter(Context context) {
        this._context = context;
        animFadeIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
    }

    public void setData(RealmResults<Apps> data) {
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
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_card_app_in_category, parent, false);
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
            public void onClick(View view) {
                BasePresenter.irDetalleApp(_context, (int) view.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}