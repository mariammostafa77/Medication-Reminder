package com.example.medicationreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.Model.MedInfo;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class ActiveMedicationsFragment extends Fragment {
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
    //FirebaseRecyclerAdapter<MedInfo, productviewholder> adapter;
    //FirestoreRecyclerAdapter adapter;
    DatabaseReference myRef;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

          View view=inflater.inflate(R.layout.fragment_active_medications, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        myRef = database.getReference().child("MedicationData");

        arrayList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = view.findViewById(R.id.activeRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.child("MedicationData").getChildren()) {
                    final String medname = postSnapshot.child("medName").getValue(String.class);
                    final int mun = postSnapshot.child("numOfTimes").getValue(Integer.class);
                    MedInfo Medinfot = new MedInfo(medname,mun);
                    arrayList.add(Medinfot);
                    String.valueOf(Medinfot.getNumOfTimes());
                }
                //recyclerView.setAdapter(adapter);
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

        /*FirebaseRecyclerOptions<MedInfo> options=new FirebaseRecyclerOptions.Builder<MedInfo>()
                .setQuery(myRef,MedInfo.class).build();
        adapter= new FirebaseRecyclerAdapter<MedInfo, productviewholder>(options) {
            private productviewholder holder;
            private int position;
            private MedInfo model;



            @NonNull
            @Override
            public productviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.active_or_inactive_med_row,parent,false);
                productviewholder findfriendviewholder1=new productviewholder(view);

                return findfriendviewholder1;
            }

            @Override
            public void onBindViewHolder(@NonNull productviewholder holder, int position, @NonNull MedInfo model) {
                holder.email.setText(model.getMedName());

                holder.medlistname.setText(String.valueOf(model.getNumOfTimes()));
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();*/
    }


    @Override
    public void onStop() {
        super.onStop();
//                        adapter.stopListening();
        //adapter.stopListening();
        // myadapter1.stopListening();
    }
    //********
    private class productviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView medlistname,email,weight,left,full,jk;
        public productviewholder(@NonNull View itemView) {
            super(itemView);
            medlistname=itemView.findViewById(R.id.txtInactiveMedName);
            email=itemView.findViewById(R.id.txtInactiveStrengthNumberOfTheMed);
            //   weight=itemView.findViewById(R.id.txtInactiveStrengthWeightOfTheMed);
            // left=itemView.findViewById(R.id.txt_med_numer_stringth_info);
            // full=itemView.findViewById(R.id.inactive_med_left);
            //jk=itemView.findViewById(R.id.txt_med_stringth_info);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }
    }
