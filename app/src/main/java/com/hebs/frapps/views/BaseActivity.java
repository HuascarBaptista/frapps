package com.hebs.frapps.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hebs.frapps.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Android Studio.
 * Huascar.baptista@gmail.com / @HuascarBaptista
 * Desarrollador en curso...
 * Date: 26/8/2016
 * Time: 3:34 AM
 */
public class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Dialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    void cargandoDialog(boolean progress) {
        if (progress) {
            progressDialog = ProgressDialog.show(this, "Cargando",
                    "Espere un momento por favor...", true);
        } else {
            if (progressDialog != null)
                progressDialog.dismiss();
        }
    }

    void cargandoCustomDialog(boolean activo) {
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
}
