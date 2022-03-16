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
import java.util.List;

public class MedAdapter extends RecyclerView.Adapter<MedAdapter.MyViewHolder> {
    Context context;
    ArrayList<MedInfo>medList;
    int i=0;


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
        int counter=medInfo.getNumOfTimes();
        List<TimeOfMed> times=new ArrayList<>();
        times=medInfo.getTimeList();

        holder.txtMidName.setText(medInfo.getMedName());
        holder.txtDose.setText(times.get(i).getDose()+medInfo.getMedUnit());
        holder.txtTime.setText(times.get(i).getTime());
        if(i<counter-1){

            i++;
        }
        else{
            i=0;
        }


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