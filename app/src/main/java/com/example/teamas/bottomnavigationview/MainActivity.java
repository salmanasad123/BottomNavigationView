package com.example.teamas.bottomnavigationview;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new FragmentHome());
        fragmentTransaction.commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                fragmentTransaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new FragmentHome();
                        break;

                    case R.id.nav_search:
                        selectedFragment = new FragmentSearch();
                        break;

                    case R.id.nav_profile:
                        selectedFragment = new FragmentProfile();
                        break;
                }

                fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                fragmentTransaction.commit();
                return true;
            }
        });
    }
}
