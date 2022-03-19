
package com.example.medicationreminder;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.ShowMedication.View.ClickListenerInterface;
import com.example.medicationreminder.ShowMedication.View.MedAdapter;
import com.example.medicationreminder.ShowMedication.model.IShowMedicationView;
import com.example.medicationreminder.ShowMedication.model.MedInfo;
import com.example.medicationreminder.ShowMedication.presenter.ShowMedicationPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;



public class HomeFragment extends Fragment implements IShowMedicationView {

    RecyclerView medRecyclerView;
    MedAdapter medAdapter;
    ArrayList<MedInfo> medList;
    ShowMedicationPresenter presenter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public int[] splitMedDate(String data) {
        String[] splitDate = data.split("/");
        int day = Integer.parseInt(splitDate[0]);

        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        int[] allDate = {day, month, year};
        return allDate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        CalendarView calendarView = v.findViewById(R.id.custom_calendar);

        Calendar c = Calendar.getInstance();
        int thisday = c.get(Calendar.DAY_OF_MONTH);
        int thismonth = c.get(Calendar.MONTH) + 1;
        int thisyear = c.get(Calendar.YEAR);
        presenter=new ShowMedicationPresenter(this);


        medRecyclerView = v.findViewById(R.id.medRecyclerview);

        medRecyclerView.setHasFixedSize(true);
        medRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        medList = new ArrayList<>();
        medAdapter = new MedAdapter(getContext(), medList);

        medList.clear();

        presenter.onCalenderItemSlected(medList,medAdapter,thisday, thismonth, thisyear);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int actualMonth = month + 1;
                Log.i("Data", "" + dayOfMonth);
                medList.clear();
                presenter.onCalenderItemSlected(medList,medAdapter,dayOfMonth, actualMonth, year);

            }
        });

        return v;

    }

    @Override
    public void showMed() {
        medAdapter.notifyDataSetChanged();
        medRecyclerView.setAdapter(medAdapter);

    }


}