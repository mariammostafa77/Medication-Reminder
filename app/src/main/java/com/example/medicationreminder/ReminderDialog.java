package com.example.medicationreminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medicationreminder.ShowMedication.View.ClickListenerInterface;
import com.example.medicationreminder.ShowMedication.View.DeleteInterface;
import com.example.medicationreminder.ShowMedication.View.MedAdapter;
import com.example.medicationreminder.ShowMedication.presenter.ShowMedicationPresenter;

public class ReminderDialog extends Dialog implements
        View.OnClickListener{

    TextView tvTimeReminder,tvMedNameReminder,tvDoseReminder;
    ImageView btnSnoozReminder,btnCancelReminder,btnTakeReminder;

    public ReminderDialog(@NonNull Context context) {
        super(context);
    }

    public ReminderDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ReminderDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reminder_dialog);
        tvDoseReminder = findViewById(R.id.tvDoseReminder);
        tvMedNameReminder = findViewById(R.id.tvMedNameReminder);
        tvTimeReminder = findViewById(R.id.tvTimeReminder);
        btnCancelReminder = findViewById(R.id.btnCancelReminder);
        btnSnoozReminder = findViewById(R.id.btnSnoozeReminder);
        btnTakeReminder = findViewById(R.id.btnTakeReminder);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancelReminder:
                dismiss();

                break;
            case R.id.btnTakeReminder:
                dismiss();

                break;
            case R.id.btnSnoozeReminder:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
