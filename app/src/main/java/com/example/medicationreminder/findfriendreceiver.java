package com.example.medicationreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class findfriendreceiver extends Fragment {

    private RecyclerView recyclerView;
    DatabaseReference mbase,friend;
    findfriendadapter adapter;
    friendslistmodel friendslistmodel1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_findfriendreceiver, container, false);
        mbase=  FirebaseDatabase.getInstance().getReference("reciever");



        // FirebaseDatabase.getInstance().getReference().child("request").child("reciever");
        recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<midfriendmodel> options
                = new FirebaseRecyclerOptions.Builder<midfriendmodel>()
                .setQuery(mbase, midfriendmodel.class)
                .build();

        adapter = new findfriendadapter(options);

        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }


    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}