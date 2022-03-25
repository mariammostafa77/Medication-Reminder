package com.example.medicationreminder;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class findfriendadapter extends FirebaseRecyclerAdapter<
        midfriendmodel, findfriendadapter.personsViewholder> {
    ArrayList<midfriendmodel> arrayList;
    friendslistmodel friendslistmodel1;
    midfriendmodel model;
    findfriendadapter findfriendadapter2;
    public static String channel_id="exampleservicechannel";
    public findfriendadapter(
            @NonNull FirebaseRecyclerOptions<midfriendmodel> options)
    {
        super(options);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull midfriendmodel model)
    {
        holder.name.setText(model.getEmail());
        holder.email.setText(model.getName());
        String b=model.getName();

        friendslistmodel friendslist11=new friendslistmodel(b);
       // int size=findfriendadapter2.getItemCount();
        holder.refuse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                holder.accept.setVisibility(View.GONE);
                holder.refuse.setVisibility(View.GONE);
                holder.statustext.setVisibility(View.VISIBLE);
                holder.statustext.setText("Refused");
            }
        });
        holder.accept.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                //addDatatoFirebase("model.getName()");
                holder.statustext.setVisibility(View.VISIBLE);
                holder.statustext.setText("Accepted");
                holder.accept.setVisibility(View.GONE);
                holder.refuse.setVisibility(View.GONE);
                DatabaseReference databaseReference;

                databaseReference = FirebaseDatabase.getInstance().getReference("friends").push();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.setValue(friendslist11);
                        System.out.println("accept");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new HomeFragment()).commit();

            }
        });

    }
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.findfrienditemfinal, parent, false);

        return new findfriendadapter.personsViewholder(view);
    }

    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView name, email,statustext;
        Button accept,refuse;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);
           statustext = itemView.findViewById(R.id.status);
            name = itemView.findViewById(R.id.firstname);
            email = itemView.findViewById(R.id.lastname);
            accept=itemView.findViewById(R.id.acceptbtn);
            refuse=itemView.findViewById(R.id.refusebtn);


        }
    }
    private void addDatatoFirebase(String name) {

       // friendslistmodel1.setName(name);
   DatabaseReference databaseReference;

        databaseReference = FirebaseDatabase.getInstance().getReference("friends").push();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.setValue(friendslistmodel1);
                  System.out.println("accept");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
