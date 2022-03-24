package com.example.medicationreminder;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class friendslistadapter extends FirebaseRecyclerAdapter<
        friendslistmodel, friendslistadapter.personsViewholder> {
    ArrayList<friendslistmodel> arrayList;
    friendslistmodel friendslistmodel1;
    Context context;
    public static String channel_id="exampleservicechannel";
    public friendslistadapter(
            @NonNull FirebaseRecyclerOptions<friendslistmodel> options)
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
                     int position, @NonNull friendslistmodel model)
    {

        holder.name.setText(model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),HomeActivity.class);
                intent.putExtra("usernamee",model.getName());
                view.getContext().startActivity(intent);
            }
        });

    }
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friendslistitem, parent, false);
        return new friendslistadapter.personsViewholder(view);
    }

    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView name;

        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.friendsname);
        }
    }

}
