package com.example.medicationreminder.EditMed.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medicationreminder.R;
import com.example.medicationreminder.ShowMedication.View.ClickListenerInterface;
import com.example.medicationreminder.ShowMedication.View.DeleteInterface;
import com.example.medicationreminder.ShowMedication.View.MedAdapter;
import com.example.medicationreminder.Model.TimeOfMed;
import com.example.medicationreminder.ShowMedication.presenter.ShowMedicationPresenter;

import java.util.ArrayList;
import java.util.List;

public class ShowEditMedFragment extends Fragment {

    ClickListenerInterface presenteer=new ShowMedicationPresenter();
    DeleteInterface anInterface=new MedAdapter();
    EditText edtName,edtTimeNum,edtNumLeft,edtNumRemind;
    TextView tvTimeUnit,tvRfillTime,tvStartDate,tvEndDate;
    Button btnSaveEdit;
    static List<TimeOfMed> times=new ArrayList<>();
    MyAdapter adapter;
    RecyclerView recyclerTime;


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
        View view =inflater.inflate(R.layout.fragment_show_edit_med, container, false);
        //presenteer.onEditClick(anInterface.getMedInfo());
        edtName=view.findViewById(R.id.edtName);
        edtTimeNum =view.findViewById(R.id.edtTimeNum);
        edtNumLeft=view.findViewById(R.id.edtNumLeft);
        edtNumRemind=view.findViewById(R.id.edtNumRemind);
        tvTimeUnit =view.findViewById(R.id.tvTimeUnit);
        tvRfillTime=view.findViewById(R.id.tvRfillTime);
        btnSaveEdit=view.findViewById(R.id.btnSaveEdit);
        tvStartDate=view.findViewById(R.id.tvEditStartDate);
        tvEndDate=view.findViewById(R.id.tvEditEndDate);
        recyclerTime=view.findViewById(R.id.recycleTimes);
        btnSaveEdit=view.findViewById(R.id.btnSaveEdit);

        adapter=new MyAdapter(times,getContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerTime.setLayoutManager(linearLayoutManager);
        recyclerTime.setAdapter(adapter);


        edtName.setText(anInterface.getMedInfo().getMedName());
        edtTimeNum.setText(String.valueOf(anInterface.getMedInfo().getNumOfTimes()));
        tvTimeUnit.setText(anInterface.getMedInfo().getMedTakenUnit());
        tvStartDate.setText(anInterface.getMedInfo().getStartDate());
        tvEndDate.setText(anInterface.getMedInfo().getEndDate());

        for(int i=0;i<anInterface.getMedInfo().getTimeList().size();i++) {
            times.add(anInterface.getMedInfo().getTimeList().get(i));
        }
        if(anInterface.getMedInfo().getRefillMeds() !=null) {
            edtNumLeft.setText(String.valueOf(anInterface.getMedInfo().getRefillMeds().getpillLeftNum()));
            edtNumRemind.setText(String.valueOf(anInterface.getMedInfo().getRefillMeds().getNumOfRemind()));
            tvRfillTime.setText(anInterface.getMedInfo().getRefillMeds().getRemindTime());
        }

        return view;
    }
}