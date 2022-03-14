package com.example.medicationreminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedAdapter extends RecyclerView.Adapter<MedAdapter.MyViewHolder> {
    Context context;
    ArrayList<MedInfo>medList;

    public MedAdapter(Context context, ArrayList<MedInfo> medList) {
        this.context = context;
        this.medList = medList;
    }

    @NonNull
    @Override
    public MedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.med_row,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedAdapter.MyViewHolder holder, int position) {
        MedInfo medInfo=medList.get(position);
        holder.txtMidName.setText(medInfo.getMedName());
        holder.txtDose.setText(medInfo.getStartDate());
        holder.txtTime.setText(medInfo.getTime());

      //  holder.myImage.setImageResource(medInfo.getImg());



    }

    @Override
    public int getItemCount() {
        return medList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtMidName,txtDose,txtTime,txtNotes,txtTaken;
        ImageView myImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMidName=itemView.findViewById(R.id.txtMidName);
            txtDose=itemView.findViewById(R.id.txtDose);
            txtTime=itemView.findViewById(R.id.txtMidTime);
            txtTaken=itemView.findViewById(R.id.txtTaken);
            txtNotes=itemView.findViewById(R.id.txtNotes);
            myImage=itemView.findViewById(R.id.imageView3);
        }
    }

}