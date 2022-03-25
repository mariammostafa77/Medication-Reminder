package com.example.medicationreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class midfriendrequest extends Fragment {
    private EditText employeeNameEdt, employeeemailEdt;
    private Button sendDatabtn;
    midfriendmodel geeksmodel1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
   FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_midfriendrequest, container, false);
        auth=FirebaseAuth.getInstance();
        employeeNameEdt = view.findViewById(R.id.idEdtEmployeeName);
        employeeemailEdt = view.findViewById(R.id.idEdtEmployeePhoneNumber);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("reciever").push();
        geeksmodel1 = new midfriendmodel();

        sendDatabtn = view.findViewById(R.id.idBtnSendData);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = employeeNameEdt.getText().toString();
                String phone = employeeemailEdt.getText().toString();

                if (!TextUtils.isEmpty(name) && Patterns.EMAIL_ADDRESS.matcher(phone).matches()&& !TextUtils.isEmpty(phone) ) {

                    addDatatoFirebase(name, phone);

                } else {
                    Toast.makeText(getContext(), "Please add some data.", Toast.LENGTH_SHORT).show();

                }
            }
        });

       return view;
    }
    public void checkemail(View v)
    {
      auth.fetchSignInMethodsForEmail(employeeemailEdt.getText().toString()).
        addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
           boolean check=!task.getResult().getSignInMethods().isEmpty();
           if(!check)
           {
               Toast.makeText(getContext(),"email is empty",Toast.LENGTH_LONG).show();
           }else
           {
               Toast.makeText(getContext(),"email is found",Toast.LENGTH_LONG).show();
           }

            }
        });
    }
    private void addDatatoFirebase(String name, String phone) {

        geeksmodel1.setName(name);
        geeksmodel1.setEmail(phone);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(geeksmodel1);
                Toast.makeText(getContext(), "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });


    }
}