package com.example.medicationreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import androidx.work.OneTimeWorkRequest;

import com.example.medicationreminder.AddMed.View.AddMedFragment1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    boolean clicked=false;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button;
    BottomNavigationView bottomNav;

    //notification
    public static List<OneTimeWorkRequest>requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //notification
        requests=new ArrayList<>();

        bottomNav=findViewById(R.id.bottomNav);

        //getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new HomeFragment()).commit();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.viewLayout);
        NavController navController = navHostFragment.getNavController();
            NavGraph navGraph = navHostFragment.getNavController().getNavInflater().inflate(R.navigation.nav_graph);
          // navGraph.setStartDestination(R.id.fragmentAddMed1);
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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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

                            case R.id.add_dependent:
                                getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new Adddependent()).commit();
                                break;
                            case R.id.add_midfriend:
                                getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new findfriendfra()).commit();
                                break;

                        }

                        return true;
                    }
                });

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_add_medication:
                        getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new AddMedFragment1()).commit();
                        bottomNav.setVisibility(View.INVISIBLE);
                        getSupportActionBar().hide();
                        return true;

                    case R.id.bottom_medication:
                        getSupportFragmentManager().beginTransaction().replace(R.id.viewLayout,new MedicationsListFragment()).commit();
                        return true;
                }
                return false;
            }
        });

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