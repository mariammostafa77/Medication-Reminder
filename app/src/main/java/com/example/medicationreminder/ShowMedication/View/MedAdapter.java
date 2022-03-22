package com.example.medicationreminder.ShowMedication.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.EditMed.View.ShowEditMedFragment;
import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.R;
import com.example.medicationreminder.Model.TimeOfMed;

import java.util.ArrayList;
import java.util.List;

public class MedAdapter extends RecyclerView.Adapter<MedAdapter.MyViewHolder> implements DeleteInterface {
    Context context;
    ArrayList<MedInfo>medList;
    static int i=0;
    ClickListenerInterface clickListener;
    static MedInfo myMedInfo=new MedInfo();
    String choice;
    static List<TimeOfMed> times;
    static String doseId;
    int counter;
    public MedAdapter() {
    }
    public MedAdapter(Context context, ArrayList<MedInfo> medList) {
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
         counter=medInfo.getTimeList().size();
        times=new ArrayList<>();
        times=medInfo.getTimeList();
       if(i<counter-1){

           i++;

       }
       else{
           i=0;
      }
        int myPosition=position;
        holder.txtMidName.setText(medInfo.getMedName());
        if(times.get(i)==null){
            i++;
            counter--;
        }
        holder.txtDose.setText(times.get(i).getDose()+medInfo.getMedUnit());
        holder.txtTime.setText(times.get(i).getTime());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDeleteDialog deleteDialog=new CustomDeleteDialog(v.getContext());
                deleteDialog.show();
                myMedInfo=medList.get(myPosition);
                while (myMedInfo.getTimeList().get(i)==null){
                    i++;
                }
                doseId=myMedInfo.getTimeList().get(i).getDoseId();

            }

        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMedInfo=medList.get(myPosition);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new ShowEditMedFragment()).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return medList.size();
    }

    @Override
    public MedInfo getMedInfo() {
        return myMedInfo;
    }

    @Override
    public String getTimeOfDose() {
        return doseId;
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