package com.example.medicationreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class friendslist extends Fragment {

    private RecyclerView recyclerView;
    DatabaseReference mbase;
    friendslistadapter adapter;
    friendslistmodel friendslistmodel1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_friendslist, container, false);
        mbase=  FirebaseDatabase.getInstance().getReference("friends");



        // FirebaseDatabase.getInstance().getReference().child("request").child("reciever");
        recyclerView = view.findViewById(R.id.recyclerlist);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<friendslistmodel> options
                = new FirebaseRecyclerOptions.Builder<friendslistmodel>()
                .setQuery(mbase, friendslistmodel.class)
                .build();

        adapter = new friendslistadapter(options);

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