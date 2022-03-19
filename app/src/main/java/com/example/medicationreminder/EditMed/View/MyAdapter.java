package com.example.medicationreminder.EditMed.View;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;

import com.example.medicationreminder.AddMed.View.AddMedFragment2;
import com.example.medicationreminder.HomeActivity;
import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.Model.TimeOfMed;
import com.example.medicationreminder.R;
import com.example.medicationreminder.WorkerHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<TimeOfMed> medInfoList;
    Context context;

    public MyAdapter(List<TimeOfMed> medInfoList, Context context) {
        this.medInfoList = medInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.edit_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        Log.i("TAG","onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTvTime().setText(medInfoList.get(position).getTime());
        holder.getTvDose().setText("Dose: "+medInfoList.get(position).getDose());
        holder.getTvDay().setText("Day of Month:"+medInfoList.get(position).getDayOfMonth());
        holder.getTvDay().setText(medInfoList.get(position).getDayOfWeek());
        holder.getImgGetTime().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(holder.getTvTime());
            }
        });
        holder.getTvTime().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(holder.getTvTime());
            }
        });
    }

    @Override
    public int getItemCount() {
        return medInfoList.size();
    }
    public void getTime(TextView textView){
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(calendar.HOUR_OF_DAY);
        final int minute = calendar.get(calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(textView.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String time = hour + ":" + minute;
                textView.setText(time);
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View row;
        TextView tvTime, tvDose, tvDay;
        ImageView imgGetTime;

        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            row = convertView;
            tvTime = row.findViewById(R.id.tvTime);
            tvDose = row.findViewById(R.id.tvDose);
            tvDay = row.findViewById(R.id.tvDay);
            imgGetTime = row.findViewById(R.id.imgGetTime);
        }

        public View getRow() {
            return row;
        }

        public TextView getTvTime() {
            return tvTime;
        }

        public TextView getTvDose() {
            return tvDose;
        }

        public TextView getTvDay() {
            return tvDay;
        }

        public ImageView getImgGetTime() {
            return imgGetTime;
        }
    }
}


