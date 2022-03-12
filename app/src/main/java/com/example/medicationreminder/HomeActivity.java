package com.example.medicationreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    boolean clicked=false;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button;
    FloatingActionButton floatingActionButton,floatingActionButton2,floatingActionButton3;
    TextView txtAddMed,txtAddMidfriend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new HomeFragment()).commit();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.viewLayout);
        NavController navController = navHostFragment.getNavController();
            NavGraph navGraph = navHostFragment.getNavController().getNavInflater().inflate(R.navigation.nav_graph);
            navGraph.setStartDestination(R.id.fragment_home);
            navController.setGraph(navGraph);





        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navView);
        toolbar=findViewById(R.id.navToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        menuItem.setChecked(true);



                        drawerLayout.closeDrawers();
                        switch (menuItem.getItemId()){
                            case R.id.logout:
                                //Toast.makeText(HomeActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                                FirebaseAuth.getInstance().signOut();

                                Intent intent=new Intent(HomeActivity.this,StartActivity.class);
                                startActivity(intent);

                               editor.remove("email");
                                editor.remove("password");
                                editor.clear();
                                editor.commit();

                                Toast.makeText(HomeActivity.this, sharedPreferences.getString("email",null), Toast.LENGTH_SHORT).show();


                                break;
                        }

                        return true;
                    }
                });

        floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton2=findViewById(R.id.floatingActionButton2);
        floatingActionButton3=findViewById(R.id.floatingActionButton3);
        txtAddMed=findViewById(R.id.txtAddMed);
        txtAddMidfriend=findViewById(R.id.txtAddMid);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClick();
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new AddMedFragment1()).commit();
            }
        });


    }

    private void onAddButtonClick() {
        setVisibile(clicked);
        setAnimtion(clicked);
        clicked= !clicked;
    }
    private void setVisibile(boolean clicked) {
        if(!clicked){
            floatingActionButton2.setVisibility(View.VISIBLE);
            floatingActionButton3.setVisibility(View.VISIBLE);
        }
        else{
            floatingActionButton2.setVisibility(View.INVISIBLE);
            floatingActionButton3.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimtion(boolean clicked) {
        if(!clicked){
            floatingActionButton2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_btn));
            floatingActionButton3.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_btn));
            txtAddMidfriend.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_btn));
            txtAddMed.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_btn));
            floatingActionButton.startAnimation(AnimationUtils.loadAnimation(this,R.anim.float_btn));




        }
        else{
            floatingActionButton2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.to_btn));
            floatingActionButton3.startAnimation(AnimationUtils.loadAnimation(this,R.anim.to_btn));
            txtAddMidfriend.startAnimation(AnimationUtils.loadAnimation(this,R.anim.to_btn));
            txtAddMed.startAnimation(AnimationUtils.loadAnimation(this,R.anim.to_btn));
            floatingActionButton.startAnimation(AnimationUtils.loadAnimation(this,R.anim.close_float_btn));

        }


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;


        }
        return super.onOptionsItemSelected(item);

    }
}