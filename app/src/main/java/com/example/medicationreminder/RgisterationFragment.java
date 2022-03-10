package com.example.medicationreminder;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;


import android.text.TextUtils;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;




public class RgisterationFragment extends Fragment {
    Button btnRegister;
    NavController navController;
    NavDirections navDirections;
    private FirebaseAuth mAuth;
    EditText edtUsername,edtPhone,edtEmailR,edtPasswordR,edtConPassword;
    ImageView imgGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 111;
    GoogleSignInOptions gso;






    public RgisterationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_rgisteration, container, false);
        btnRegister = v.findViewById(R.id.btnRegister);
        edtUsername = v.findViewById(R.id.edtUsername);


        edtUsername = v.findViewById(R.id.edtUsername);
        edtPhone = v.findViewById(R.id.edtPhone);
        edtEmailR = v.findViewById(R.id.edtEmailR);
        edtPasswordR = v.findViewById(R.id.edtPasswordR);
        edtConPassword = v.findViewById(R.id.edtConfirmPassword);
        imgGoogle = v.findViewById(R.id.imgGoogle);





        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edtUsername.getText().toString();
                String phone = edtPhone.getText().toString();
                String email = edtEmailR.getText().toString();
                String password = edtPasswordR.getText().toString();
                String confirmPassword = edtConPassword.getText().toString();

                if (!username.isEmpty() && !phone.isEmpty() && !email.isEmpty() &&
                        !password.isEmpty() && !confirmPassword.isEmpty() &&
                        confirmPassword.equals(password) && password.length() >= 6 &&
                        Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(username, phone, email, password);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                            if (firebaseUser.isEmailVerified()) {
                                                Toast.makeText(getActivity(), "User had been registered successfully", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(getActivity(), HomeActivity.class);
                                                startActivity(i);
                                            } else {
                                                firebaseUser.sendEmailVerification();
                                                Toast.makeText(getActivity(), "Check your email for verify your account", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                } else {
                    if (TextUtils.isEmpty(username)) {
                        edtUsername.setError("Username is requried");
                        edtUsername.requestFocus();

                    }
                    if (TextUtils.isEmpty(phone)) {
                        edtPhone.setError("please enter phone");
                        edtPhone.requestFocus();

                    }
                    if (TextUtils.isEmpty(email)) {
                        edtEmailR.setError("please enter email ");
                        edtEmailR.requestFocus();

                    }

                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        edtEmailR.setError("please enter email in correct format");
                        edtEmailR.requestFocus();

                    }
                    if (TextUtils.isEmpty(password)) {
                        edtPasswordR.setError("please enter password");
                        edtPasswordR.requestFocus();

                    }
                    if (password.length() < 6) {
                        edtPasswordR.setError("required more than 6");

                        edtPasswordR.requestFocus();

                    }
                    if (TextUtils.isEmpty(confirmPassword)) {
                        edtConPassword.setError("please enter password");
                        edtConPassword.requestFocus();

                    }
                    if (!confirmPassword.equals(password)) {
                        edtConPassword.setError("must be matched");
                        edtConPassword.requestFocus();


                    }
                }


            }
        });


        imgGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken("286966019798-7nsfc3774p76k4on13sm1nip7ogr9dtc.apps.googleusercontent.com")
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

             signIn();
            }
        });



        return v;
    }




    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getActivity(), "failur", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            Intent i = new Intent(getActivity(), HomeActivity.class);
            startActivity(i);
        }


    }

}