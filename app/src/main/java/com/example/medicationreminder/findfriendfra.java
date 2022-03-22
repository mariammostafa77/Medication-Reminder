package com.example.medicationreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

import java.util.ArrayList;

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


}