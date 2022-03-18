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
    ClickListenerInterface clickListener;


    public MedAdapter(Context context, ArrayList<MedInfo> medList,ClickListenerInterface clickListener) {
        this.context = context;
        this.medList = medList;
        this.clickListener=clickListener;

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
       if(i<counter-1){

           i++;

       }
       else{
           i=0;
      }
        int myPosition=position;
        holder.txtMidName.setText(medInfo.getMedName());
        holder.txtDose.setText(times.get(i).getDose()+medInfo.getMedUnit());
        holder.txtTime.setText(times.get(i).getTime());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onDeleteClick(medList.get(myPosition));
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onEditClick(medList.get(myPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return medList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtMidName,txtDose,txtTime,txtNotes,txtTaken;
        ImageView myImage,imgEdit,imgDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMidName=itemView.findViewById(R.id.txtMidName);
            txtDose=itemView.findViewById(R.id.txtDose);
            txtTime=itemView.findViewById(R.id.txtMidTime);
            txtTaken=itemView.findViewById(R.id.txtTaken);
            txtNotes=itemView.findViewById(R.id.txtNotes);
            myImage=itemView.findViewById(R.id.imageView3);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            imgEdit=itemView.findViewById(R.id.imgEdit);
        }
    }

}