package com.example.medicationreminder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;

import com.example.medicationreminder.RegisterPackage.RegistrationsModel.IRegistersView;
import com.example.medicationreminder.RegisterPackage.RegistrationsPresenter.RegisterPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class RgisterationFragment extends Fragment implements IRegistersView {
    Button btnRegister;
    NavController navController;
    NavDirections navDirections;
    private FirebaseAuth mAuth;
    EditText edtUsername,edtPhone,edtEmailR,edtPasswordR,edtConPassword;
    ImageView imgGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 100;
    GoogleSignInOptions gso;
    FirebaseFirestore firebaseFirestore;
    String userID;
    RegisterPresenter presenter;
    String username,phone,email,password,confirmPassword;





    public RgisterationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        presenter=new RegisterPresenter(this);


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

                 username = edtUsername.getText().toString();
                 phone = edtPhone.getText().toString();
                 email = edtEmailR.getText().toString();
                 password = edtPasswordR.getText().toString();
                 confirmPassword = edtConPassword.getText().toString();

                if (!username.isEmpty() && !phone.isEmpty() && !email.isEmpty() &&
                        !password.isEmpty() && !confirmPassword.isEmpty() &&
                        confirmPassword.equals(password) && password.length() >= 6 &&
                        Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        presenter.onRegistrationBtnClick(username,phone,email,password);


                } else {
                   validation();
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

    private void validation() {
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

                presenter.onRegistrationGoogleBtnClick(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getActivity(), "failur", Toast.LENGTH_SHORT).show();
            }
        }
    }





    @Override
    public void goToLogin(boolean result) {
        if(result==true) {
            Intent i = new Intent(getActivity(), HomeActivity.class);
            startActivity(i);
            Toast.makeText(getActivity(), "Registration Successfully", Toast.LENGTH_SHORT).show();
            StartFragment.isGuest=false;

        }
        else{
            Toast.makeText(getActivity(), "Error in registration", Toast.LENGTH_SHORT).show();
        }
    }
}