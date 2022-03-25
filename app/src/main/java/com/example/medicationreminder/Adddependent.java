package com.example.medicationreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Adddependent extends Fragment {
    FirebaseDatabase root;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    EditText firstnameadd,lastfirstadd,emailadd,passwordadd;
    Button donedepend;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_adddependent, container, false);
        //listView=view.findViewById(R.id.list);
        firstnameadd=(EditText) view.findViewById(R.id.firstname);
        lastfirstadd=(EditText)view.findViewById(R.id.lastname);
        emailadd=(EditText)view.findViewById(R.id.email);
        passwordadd=(EditText)view.findViewById(R.id.password);
        donedepend=view.findViewById(R.id.done);
        FirebaseAuth.getInstance().getCurrentUser().getUid();
        donedepend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root=FirebaseDatabase.getInstance();
                // reference=root.getReference("dependent");

                String namee=firstnameadd.getText().toString();
                String lname=lastfirstadd.getText().toString();
                String email=emailadd.getText().toString();
                String pass=passwordadd.getText().toString();



                //  ArrayList<String>listt=new ArrayList<>();
                // ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.listitem,listt);
                //listView.setAdapter(adapter);
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid()).child("dependent");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren())
                        {
                            dataSnapshot.getValue().toString();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                User user=new User(namee,lname,email,pass);
                reference.child(email).setValue(user);
            }
        });

        return view;

    }
}