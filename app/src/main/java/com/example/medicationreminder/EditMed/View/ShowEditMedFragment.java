package com.example.medicationreminder.EditMed.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medicationreminder.AddMed.Model.RefillMed;
import com.example.medicationreminder.EditMed.Database.RepoInterface;
import com.example.medicationreminder.EditMed.Database.Repository;
import com.example.medicationreminder.EditMed.Presenter.EditInterface;
import com.example.medicationreminder.EditMed.Presenter.EditPresenter;
import com.example.medicationreminder.HomeActivity;
import com.example.medicationreminder.HomeFragment;
import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.R;
import com.example.medicationreminder.ShowMedication.Database.RepositoryInterface;
import com.example.medicationreminder.ShowMedication.View.ClickListenerInterface;
import com.example.medicationreminder.ShowMedication.View.DeleteInterface;
import com.example.medicationreminder.ShowMedication.View.MedAdapter;
import com.example.medicationreminder.Model.TimeOfMed;
import com.example.medicationreminder.ShowMedication.presenter.ShowMedicationPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ShowEditMedFragment extends Fragment {

    ClickListenerInterface presenteer = new ShowMedicationPresenter();
    DeleteInterface anInterface = new MedAdapter();
    EditText edtName, edtNumLeft, edtNumRemind;
    TextView tvTimeUnit, tvRfillTime, tvStartDate, tvEndDate;
    Button btnSaveEdit;
    List<TimeOfMed> times = new ArrayList<>();
    MyAdapter adapter;
    RecyclerView recyclerTime;
    MedAdapter medAdapter=new MedAdapter();

    TextView tvRefillData,tvNumLeft,tvRemindNum,tvRemindTime;
    LinearLayout linearView;

    RepoInterface repo=new Repository();
    EditInterface presenter=new EditPresenter();
    String remindTime;
    //MedInfo medInfo=new MedInfo();
    //RefillMed refillMed=new RefillMed();
    static String userId,medId,startDate,endDate;


    public ShowEditMedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_edit_med, container, false);
        //presenteer.onEditClick(anInterface.getMedInfo());
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        edtName = view.findViewById(R.id.edtName);
        edtNumLeft = view.findViewById(R.id.edtNumLeft);
        edtNumRemind = view.findViewById(R.id.edtNumRemind);
        tvTimeUnit = view.findViewById(R.id.tvTimeUnit);
        tvRfillTime = view.findViewById(R.id.tvRfillTime);
        btnSaveEdit = view.findViewById(R.id.btnSaveEdit);
        tvStartDate = view.findViewById(R.id.tvEditStartDate);
        tvEndDate = view.findViewById(R.id.tvEditEndDate);
        recyclerTime = view.findViewById(R.id.recycleTimes);
        btnSaveEdit = view.findViewById(R.id.btnSaveEdit);
        tvRefillData=view.findViewById(R.id.tvRefillData);
        tvNumLeft=view.findViewById(R.id.tvNumLeft);
        tvRemindNum=view.findViewById(R.id.tvRemindNum);
        tvRemindTime=view.findViewById(R.id.tvRemindTime);
        linearView=view.findViewById(R.id.linearView);

        adapter = new MyAdapter(times, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTime.setLayoutManager(linearLayoutManager);
        recyclerTime.setAdapter(adapter);


        edtName.setText(anInterface.getMedInfo().getMedName());
        tvTimeUnit.setText(String.valueOf(anInterface.getMedInfo().getNumOfTimes())+" times Per "+anInterface.getMedInfo().getMedTakenUnit());
        tvStartDate.setText(anInterface.getMedInfo().getStartDate());
        tvEndDate.setText(anInterface.getMedInfo().getEndDate());
        userId=anInterface.getMedInfo().getUserId();
        medId=anInterface.getMedInfo().getMedId();
        Log.i("TAG","userid= "+userId);
        Log.i("TAG","medid= "+medId);
        startDate=tvStartDate.getText().toString();
        endDate=tvEndDate.getText().toString();
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate(tvStartDate);
                startDate=tvStartDate.getText().toString();
            }
        });
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate(tvEndDate);
                endDate=tvEndDate.getText().toString();

            }
        });

        for (int i = 0; i < anInterface.getMedInfo().getTimeList().size(); i++) {
            times.add(anInterface.getMedInfo().getTimeList().get(i));
        }
        if (anInterface.getMedInfo().getRefillMedData() != null) {
            edtNumLeft.setText(String.valueOf(anInterface.getMedInfo().getRefillMedData().getpillLeftNum()));
            edtNumRemind.setText(String.valueOf(anInterface.getMedInfo().getRefillMedData().getNumOfRemind()));
            tvRfillTime.setText(anInterface.getMedInfo().getRefillMedData().getRemindTime());

        }else {
            setDataUnVisible();
        }


        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(getActivity());
                deleteBuilder.setMessage("Are you sure you want to edit this med")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(anInterface.getMedInfo().getRefillMedData() != null) {

                                    presenter.getAllUpdatedDateWithRemind(edtName.getText().toString(),tvStartDate.getText().toString(),
                                            tvEndDate.getText().toString(),times,userId,
                                            anInterface.getMedInfo().getMedTakenUnit(),anInterface.getMedInfo().getMedUnit().toString(),
                                            String.valueOf(anInterface.getMedInfo().getNumOfTimes()),Integer.parseInt(edtNumLeft.getText().toString()),
                                            anInterface.getMedInfo().getRefillMedData().getNumOfRemind(),tvRemindTime.getText().toString(),medId);
                                    adapter.notifyDataSetChanged();

                                }else {

                                    presenter.getAllUpdatedDate(edtName.getText().toString(),tvStartDate.getText().toString(),
                                            tvEndDate.getText().toString(),times,anInterface.getMedInfo().getUserId(),
                                            anInterface.getMedInfo().getMedTakenUnit(), anInterface.getMedInfo().getMedUnit().toString(),
                                            String.valueOf(anInterface.getMedInfo().getNumOfTimes()),medId);

                                    adapter.notifyDataSetChanged();
                                }
                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                startActivity(intent);
                                ((Activity) getActivity()).overridePendingTransition(0, 0);                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });
            return view;
        }
    public void getTime(TextView textView){
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(calendar.HOUR_OF_DAY);
        final int minute = calendar.get(calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(textView.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String time = hour + ":" + minute;
                textView.setText(time);
            }

        }, hour, minute, false);
        timePickerDialog.show();
    }
    public void setDataUnVisible(){
        tvRefillData.setVisibility(View.INVISIBLE);
        tvNumLeft.setVisibility(View.INVISIBLE);
        tvRemindNum.setVisibility(View.INVISIBLE);
        tvRemindTime.setVisibility(View.INVISIBLE);
        edtNumLeft.setVisibility(View.INVISIBLE);
        edtNumRemind.setVisibility(View.INVISIBLE);
        linearView.setVisibility(View.INVISIBLE);
    }
    public void getDate(TextView textView){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                textView.setText(date);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}

