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

        fragmentTransaction.add(R.id.fragment_container, new FragmentHome(), FragmentHome.class.getName());
        fragmentTransaction.addToBackStack(FragmentHome.class.getName());
        fragmentTransaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                fragmentTransaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = fragmentManager.findFragmentByTag(FragmentHome.class.getName());
                        if (selectedFragment == null) {
                            selectedFragment = new FragmentHome();
                            fragmentTransaction.add(R.id.fragment_container, selectedFragment, FragmentHome.class.getName());
                            fragmentTransaction.addToBackStack(FragmentHome.class.getName());
                            fragmentTransaction.commit();
                        } else {
                            fragmentManager.popBackStack(FragmentHome.class.getName(), 0);
                        }

                        break;

                    case R.id.nav_search:
                        selectedFragment = fragmentManager.findFragmentByTag(FragmentSearch.class.getName());
                        if (selectedFragment == null) {
                            selectedFragment = new FragmentSearch();
                            fragmentTransaction.add(R.id.fragment_container, selectedFragment, FragmentSearch.class.getName());
                            fragmentTransaction.addToBackStack(FragmentSearch.class.getName());
                            fragmentTransaction.commit();
                        } else {
                            fragmentManager.popBackStack(FragmentSearch.class.getName(), 0);
                        }
                        break;

                    case R.id.nav_profile:
                        selectedFragment = fragmentManager.findFragmentByTag(FragmentProfile.class.getName());
                        if (selectedFragment == null) {
                            selectedFragment = new FragmentProfile();
                            fragmentTransaction.add(R.id.fragment_container, selectedFragment, FragmentProfile.class.getName());
                            fragmentTransaction.addToBackStack(FragmentProfile.class.getName());
                            fragmentTransaction.commit();
                        } else {
                            fragmentManager.popBackStack(FragmentProfile.class.getName(), 0);
                        }
                        break;

                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment instanceof FragmentHome) {
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                }
                if (fragment instanceof FragmentSearch) {
                    bottomNavigationView.getMenu().getItem(1).setChecked(true);
                }
                if (fragment instanceof FragmentProfile) {
                    bottomNavigationView.getMenu().getItem(2).setChecked(true);
                }

                if (fragmentManager.getBackStackEntryCount() == 0) {
                    fragmentManager.executePendingTransactions();
                    MainActivity.super.onBackPressed();
                }
            }
        });
        super.onBackPressed();
    }
}
