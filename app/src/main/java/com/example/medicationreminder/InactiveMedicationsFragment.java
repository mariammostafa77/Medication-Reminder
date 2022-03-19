package com.example.medicationreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.AddMed.Model.MedData;
import com.example.medicationreminder.Model.MedInfo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class InactiveMedicationsFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<MedInfo> arrayList;
    DatabaseReference databaseReference;
    //InactiveMedicationsAdapter inactiveMedicationsAdapter;
    // personAdapter myadapter1;
    LinearLayoutManager linearLayoutManager;
    // InactiveMedicationsAdapter inactiveMedicationsAdapter1;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    boolean sf=true;
    FirebaseRecyclerAdapter<MedInfo, productviewholder> adapter;
    //FirestoreRecyclerAdapter adapter;
    DatabaseReference myRef;
    Date today;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inactive_medications, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        myRef = database.getReference().child("MedicationData");

        arrayList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = view.findViewById(R.id.inActiveRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.
                today= Calendar.getInstance().getTime();
        MedInfo medlist1=new MedInfo();
        String enddate=medlist1.getEndDate();
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
        /*try {
          //  Date sdate=sdf.parse(enddate);
            //if(today.after(sdate))
            //{

          //  }
        } catch (ParseException e) {
            e.printStackTrace();
        }*/


        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.child("MedicationData").getChildren()) {
                    final String medname = postSnapshot.child("medName").getValue(String.class);
                    final String enddat= postSnapshot.child("endDate").getValue(String.class);
//                    final int sta= postSnapshot.child("status").getValue(Integer.class);
                     String startdate= postSnapshot.child("startDate").getValue(String.class);
                     String enddate= postSnapshot.child("endDate").getValue(String.class);
                    final int mun = postSnapshot.child("numOfTimes").getValue(Integer.class);
                    MedInfo medlistt = new MedInfo(medname,mun);
                 System.out.println("strtdate:"+startdate);
                    today = Calendar.getInstance().getTime();
                    MedInfo medlist1=new MedInfo();
                    try {
                        Date date1 = sdf.parse(startdate);
                        Date date2 = sdf.parse(enddate);

                     // DateRangeValidator checker = new DateRangeValidator(startDate, endDate);
                        if(date1.after(today)||date2.before(today))
                        {
                            arrayList.add(medlistt);
                        }
                        else
                        {
                            Toast.makeText(getContext(),"err",Toast.LENGTH_LONG).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //String enddate=medlist1.getEndDate();
                   // SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
                 //   sf=stat.equals("inactive");
             //int staf=0;
           // Date sdate=sdf.parse(enddat);


                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // myadapter1.startListening();

        FirebaseRecyclerOptions<MedInfo> options=new FirebaseRecyclerOptions.Builder<MedInfo>()
                .setQuery(myRef,MedInfo.class).build();
        adapter= new FirebaseRecyclerAdapter<MedInfo, productviewholder>(options) {
            @NonNull
            @Override
            public productviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.active_or_inactive_med_row,parent,false);
                productviewholder findfriendviewholder1=new productviewholder(view);

                return findfriendviewholder1;
            }

            @Override
            protected void onBindViewHolder(@NonNull productviewholder holder, int position, @NonNull MedInfo model) {
                holder.email.setText(model.getMedUnit());

                holder.medlistname.setText(String.valueOf(model.getNumOfTimes()+"IU"));
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
//                        adapter.stopListening();
        adapter.stopListening();
        // myadapter1.stopListening();
    }
    //********
    class productviewholder extends RecyclerView.ViewHolder {
        TextView medlistname,email,weight,left,full,jk;
        public productviewholder(@NonNull View itemView) {
            super(itemView);
            medlistname=itemView.findViewById(R.id.txtInactiveMedName);
            email=itemView.findViewById(R.id.txtInactiveStrengthNumberOfTheMed);
             //   weight=itemView.findViewById(R.id.txtInactiveStrengthWeightOfTheMed);
            // left=itemView.findViewById(R.id.txt_med_numer_stringth_info);
            // full=itemView.findViewById(R.id.inactive_med_left);
            //jk=itemView.findViewById(R.id.txt_med_stringth_info);

        }
    }
}