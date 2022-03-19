package com.example.medicationreminder.ShowMedication.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

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
                presenter.onDeleteAllClick(anInterface.getMedInfo());
                adapter.notifyDataSetChanged();

                break;
            case R.id.tvDeleteDose:
                presenter.onDeleteDoseClick(anInterface.getTimeOfDose());
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
