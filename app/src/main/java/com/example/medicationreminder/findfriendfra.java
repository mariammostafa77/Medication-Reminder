package com.example.medicationreminder;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class findfriendfra extends Fragment {
    TextView textView;
    Button bbbtn;
    String currentstate,receiver_user_id,currentuserid;
    RecyclerView findfriendd;
    DatabaseReference userref,chatref;
    FirebaseAuth mauth;
    EditText editText,editnamee,editphonee;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_findfriendfra, container, false);
        /*findfriendd=view.findViewById(R.id.recyclefindfriend);
       // editText=view.findViewById(R.id.edit);
        //editphonee=view.findViewById(R.id.editphone);
        //editnamee=view.findViewById(R.id.editname);


        mauth=FirebaseAuth.getInstance();
         currentstate="new";
        currentuserid=mauth.getCurrentUser().getUid();
       // bbbtn=view.findViewById(R.id.btnn);
        findfriendd.setLayoutManager(new LinearLayoutManager(getContext()));
        final ArrayList<String>list=new ArrayList<>();
        final ArrayAdapter adapter=new ArrayAdapter<String>(getContext(), R.layout.findfrienditem,list);
        //findfriendd.setAdapter(adapter);
       //  managerequest();
        userref= FirebaseDatabase.getInstance().getReference().child("Users");
        chatref= FirebaseDatabase.getInstance().getReference().child("chat request");
        userref.child("receiver_user_id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    managerequest();
                }
               else
                {
                    managerequest();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
       /* SearchView searchView=view.findViewById(R.id.searchname);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        }
        );*/
        return view;
    }

    /*@Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
         super.onCreateOptionsMenu(menu, inflater);
    }
    public MenuInflater getMenuInflater() {
        return new MenuInflater(getContext());
    }*/

    //public void processsearch(String s) {

       /* FirebaseRecyclerOptions<User>options=new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(userref.orderByChild("email").startAt(s).endAt(s),User.class).build();
                 FirebaseRecyclerAdapter<User,findfriendviewholder>adapter=
                 new FirebaseRecyclerAdapter<User, findfriendviewholder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull findfriendviewholder holder, int position, @NonNull User model) {
                        holder.namee.setText(model.getUsername());
                        holder.email.setText(model.getEmail());
                        holder.requestsend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

      receiver_user_id = getRef(position).getKey();
                        userref.child(receiver_user_id).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists())
                                {
                                    //managerequest();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });


                    }

                    @NonNull
                    @Override
                    public findfriendviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.findfrienditem,parent,false);
                        findfriendviewholder findfriendviewholder1=new findfriendviewholder(view);

                        return findfriendviewholder1;
                    }
                };
                 findfriendd.setAdapter(adapter);
                 adapter.startListening();
    }

    private void managerequest() {
        if(!currentuserid.equals(receiver_user_id))
        {

            }*/
           /* else
            {
               bbbtn.setVisibility(View.INVISIBLE);
            }*/
                /*chatref.child(currentuserid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(receiver_user_id))
                        {
                            String request_type=snapshot.child(receiver_user_id).child("request_type")
                                    .getValue().toString();
                            if(request_type.equals("sent"))
                            {
                                currentstate="request_sent";
                               //bbbtn.setText("cancelrequest");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }*/
   /* public void checkemail(View view)
    {
        mauth.fetchSignInMethodsForEmail(editText.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                   boolean check=task.getResult().getSignInMethods().isEmpty();
                   if(!check)
                   {
                       Toast.makeText(getContext(),"email not found",Toast.LENGTH_LONG).show();
                   }
                    }
                });
    }*/
    /*private void cancelchatrequest()
    {
    chatref.child(currentuserid).child(receiver_user_id)
            .removeValue()
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful())
               {

                   chatref.child(receiver_user_id).child(currentuserid)
                           .removeValue()
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                              if(task.isSuccessful())
                              {
                                //  bbbtn.setEnabled(true);
                                  currentstate="new";
                                 // bbbtn.setText("send request");
                              }
                               }
                           });
               }
                }
            });

    }

    private void sendchatrequest() {
   chatref.push().child(currentuserid).child(receiver_user_id)
           .child("request_type").setValue("sent")
   .addOnCompleteListener(new OnCompleteListener<Void>() {
       @Override
       public void onComplete(@NonNull Task<Void> task) {
           if(task.isSuccessful())
           {
               chatref.child(receiver_user_id).child(currentuserid)
                       .child("request_type").setValue("recieved")
                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful())
                               {
                                  // bbbtn.setEnabled(true);
                                   currentstate="request_send";
                                   //bbbtn.setText("cancel");
                               }
                           }
                       });
           }
       }
   });
    }*/

    /*@Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<User>options=new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(userref,User.class).build();
        FirebaseRecyclerAdapter<User,findfriendviewholder>adapter=
                new FirebaseRecyclerAdapter<User, findfriendviewholder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull findfriendviewholder holder, int position, @NonNull User model) {
                    holder.namee.setText(model.getUsername());
                    holder.email.setText(model.getEmail());
                    holder.requestsend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           sendchatrequest();
                        }
                    });
                      receiver_user_id = getRef(position).getKey();
                        userref.child(receiver_user_id).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists())
                                {
                                    //managerequest();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                          //  getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new HomeFragment()).commit();

                        }
                    });


                    }

                    @NonNull
                    @Override
                    public findfriendviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.findfrienditem,parent,false);
                        findfriendviewholder findfriendviewholder1=new findfriendviewholder(view);

                        return findfriendviewholder1;
                    }
                };
        findfriendd.setAdapter(adapter);
        adapter.startListening();
    }
    public static class findfriendviewholder extends RecyclerView.ViewHolder{

         TextView namee,email;
         Button requestsend;
         public findfriendviewholder(@NonNull View itemView) {
            super(itemView);
             requestsend=itemView.findViewById(R.id.requestbtn);
            namee=itemView.findViewById(R.id.usernamefind);
            email=itemView.findViewById(R.id.emailfind);

        }
    }*/
  /*  int hour,minute,days;
    TimePickerDialog timePickerDialog = new TimePickerDialog(getRow().getContext(),
            new TimePickerDialog.OnTimeSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
            hour = hourOfDay;
            minute = minutes;
            Calendar calendar = Calendar.getInstance();
            calendar.set(0,0,0,hour,minute);
            if(days.size()!=0){
                execute(hour,minute);
            }else{
                String startDate= AddDrugActivity.drugsModel.getStartDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String[] s=startDate.split("/");
                Calendar today=Calendar.getInstance();
                Calendar cStartDate= Calendar.getInstance();
                String endDate = AddDrugActivity.drugsModel.getEndDate();
                cStartDate.set(Integer.parseInt(s[2]),Integer.parseInt(s[1]),Integer.parseInt(s[0]),hour,minute);
                long diffInMinutes=((cStartDate.getTimeInMillis()-today.getTimeInMillis())/60000);
                diffInMinutes=diffInMinutes-44640;
                Date d1 = null;
                Date d2 = null;
                try {
                    d1 = simpleDateFormat.parse(startDate);
                    d2 = simpleDateFormat.parse(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long differenceInTime = d2.getTime() - d1.getTime();
                long diffInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
                // Log.i("TAG","Days: "+diffInDays);
                Log.i("TAG","diff In Minutes: " + diffInMinutes);
                Log.i("Tagg","num of dayes"+String.valueOf(diffInDays));
                Log.i("Tagg",String.valueOf(diffInMinutes));
                Data inputData  = new Data.Builder()
                        .putString("medId",drugsModel.getId())
                        .putString("medName",drugsModel.getName())
                        .putString("medStrength",drugsModel.getStrength())
                        .putInt("medPill",drugsModel.getNumOfPills())
                        .build();
                OneTimeWorkRequest workRequest= new  OneTimeWorkRequest.Builder(ReminderWorker.class)
                        .setInitialDelay(diffInMinutes, TimeUnit.MINUTES)
                        .setInputData(inputData).build();
                if(diffInMinutes>0){
                    requests.add(workRequest);
                    Log.i("Tagg","done");
                }
                for(int i=1;i<=diffInDays;i++){
                    Long duration=Math.abs(diffInMinutes+1440*i);
                    workRequest=new OneTimeWorkRequest.Builder(ReminderWorker.class)
                            .setInitialDelay(duration, TimeUnit.MINUTES)
                            .setInputData(inputData).build();
                    requests.add(workRequest);
                }
            }
            String time = hour + ":" +minute;
            timesSelected.add(time);
            txtReminderTimes.setText(DateFormat.format("hh:mm aa", calendar));
        }
    },12,0,false);
                    timePickerDialog.updateTime(hour,minute);
                    timePickerDialog.show(););
                    }
    public View getRow() {
         return View;
               }
public TextView getTxtReminderTimes() {
        return txtReminderTimes;
        }
        }
        void execute(int hour,int minute){
        Format f = new SimpleDateFormat("EEEE");
        Date dt = new Date();
        Calendar today=Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        Calendar cs=Calendar.getInstance();
        String endDate = AddDrugActivity.drugsModel.getEndDate();
        Date endDatee = null;
        try {
        endDatee=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        } catch (ParseException e) {
        e.printStackTrace();
        }
        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
        for(int i=1;i<=7;i++){
        String compareDate=sdformat.format(dt);
        //int compare=endDatee.compareTo(dt);
        // Log.i("Tagg",String.valueOf(dt));
        if(endDatee.after(dt)||endDate.equals(compareDate)){
        Log.i("Tagg","done");
        String str = f.format(dt);
        if(days.contains(str)){
        Log.i("Tagg",str);
        Calendar n=Calendar.getInstance();
        n.setTime(dt);
        int year= n.get(Calendar.YEAR);
        int month=n.get(Calendar.MONTH);
        int day=n.get(Calendar.DAY_OF_MONTH);
        cs.set(year,month+1,day,hour,minute);
        Log.i("Tagg","year"+year+"month"+(month+1)+"day"+day);
        long diffInMinutes=((cs.getTimeInMillis()-today.getTimeInMillis())/60000);
        diffInMinutes=diffInMinutes-44640;
        Log.i("Tagg",String.valueOf(diffInMinutes));
        Data inputData  = new Data.Builder()
        .putString("medId",drugsModel.getId())
        .putString("medName",drugsModel.getName())
        .putString("medStrength",drugsModel.getStrength())
        .putInt("medPill",drugsModel.getNumOfPills())
        .build();
        OneTimeWorkRequest workRequest= new  OneTimeWorkRequest.Builder(ReminderWorker.class)
        .setInitialDelay(diffInMinutes, TimeUnit.MINUTES)
        .setInputData(inputData).build();
        if(diffInMinutes>0){
        requests.add(workRequest);
        //Log.i("Tagg","done");
        }

        }
        c.add(Calendar.DATE, 1);
        dt = c.getTime();

        }
        }
        }*/


}