package com.example.medicationreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class AddMedFragment2 extends Fragment {

    FirebaseFirestore fireStore=FirebaseFirestore.getInstance();
    TextView tvNum;
    RecyclerView recycleOfDay;
    RecycleAdapterMedDays MyAdapter;
    MedData medData;
    String id;
    DatabaseReference mDatabase;
    FirebaseUser currentFirebaseUser ;


    public AddMedFragment2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_med2, container, false);
        tvNum=view.findViewById(R.id.tvNum);
        recycleOfDay=view.findViewById(R.id.recycleOfDay);
        mDatabase = FirebaseDatabase.getInstance().getReference("MedicationData");
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        tvNum.setText(getArguments().getInt(AddMedFragment1.MedNumTag)+" times per "+
                getArguments().getString(AddMedFragment1.numberTakenTag));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleOfDay.setLayoutManager(linearLayoutManager);
        MyAdapter =new RecycleAdapterMedDays(getContext());
        recycleOfDay.setAdapter(MyAdapter);
        id= currentFirebaseUser.getUid();
        Button btnNext2=view.findViewById(R.id.btnNext2);
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medData=new MedData(AddMedFragment1.medName.toString(),
                        AddMedFragment1.medUnit.toString(),
                        AddMedFragment1.startDate.toString(),
                        AddMedFragment1.endDate.toString(),id,
                        AddMedFragment1.MedNum);
                addMed(medData);
                NavController navController= Navigation.findNavController(v);
                NavDirections navDirections=AddMedFragment2Directions.next2();
                navController.navigate(navDirections);
            }
        });

        return view;
    }
    public String addMed(MedData medData){
        mDatabase.push().setValue(medData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(getContext(), "added successfully ", Toast.LENGTH_SHORT).show();
            }
        });


        return null;
    }
    public void addMedTimes(String id,MedDataDay medDataDay){


    }
}