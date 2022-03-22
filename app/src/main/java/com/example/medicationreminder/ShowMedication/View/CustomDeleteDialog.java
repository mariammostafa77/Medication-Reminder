package com.example.medicationreminder.ShowMedication.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.medicationreminder.HomeActivity;
import com.example.medicationreminder.R;
import com.example.medicationreminder.ShowMedication.model.IShowMedicationView;
import com.example.medicationreminder.ShowMedication.presenter.ShowMedicationPresenter;

public class CustomDeleteDialog extends Dialog implements
        View.OnClickListener{

        public static String choice;
        Button btnCancel;
        TextView tvDeleteDose,tvDeleteAll;
        MedAdapter adapter;
        ClickListenerInterface presenter=new ShowMedicationPresenter();
        DeleteInterface anInterface=new MedAdapter();
        public static String deleteTag;

    public CustomDeleteDialog(@NonNull Context context) {
        super(context);
    }


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.delete_custom_dialog);
            tvDeleteAll = findViewById(R.id.tvDeleteAll);
            tvDeleteDose = findViewById(R.id.tvDeleteDose);
            btnCancel=findViewById(R.id.btnCancel);
            tvDeleteAll.setOnClickListener(this);
            tvDeleteDose.setOnClickListener(this);
            btnCancel.setOnClickListener(this);
            adapter=new MedAdapter();

        }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvDeleteAll:
                dismiss();
                AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                deleteBuilder.setMessage("Are you sure you want to edit this med")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                presenter.onDeleteAllClick(anInterface.getMedInfo());
                                adapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dismiss();
                            }
                        }).show();

                break;
            case R.id.tvDeleteDose:
                dismiss();
                AlertDialog.Builder deleteBuilder2 = new AlertDialog.Builder(v.getRootView().getContext());
                deleteBuilder2.setMessage("Are you sure you want to edit this med")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                presenter.onDeleteDoseClick(anInterface.getMedInfo(),anInterface.getTimeOfDose());


                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dismiss();
                            }
                        }).show();

                break;
            case R.id.btnCancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
