package com.example.teamtilt;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.teamtilt.databinding.ActivityMainBinding;
import com.example.teamtilt.fragments.DashboardFragment;
import com.example.teamtilt.fragments.ScheduleFragment;
import com.example.teamtilt.fragments.MaterialsFragment;
import com.example.teamtilt.fragments.NoticesFragment;
import com.example.teamtilt.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNav.setOnItemSelectedListener(this);
        
        // Set default fragment
        if (savedInstanceState == null) {
            loadFragment(new DashboardFragment());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.nav_dashboard) {
            fragment = new DashboardFragment();
        } else if (itemId == R.id.nav_schedule) {
            fragment = new ScheduleFragment();
        } else if (itemId == R.id.nav_materials) {
            fragment = new MaterialsFragment();
        } else if (itemId == R.id.nav_notices) {
            fragment = new NoticesFragment();
        } else if (itemId == R.id.nav_profile) {
            fragment = new ProfileFragment();
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
            return true;
        }
        return false;
    }
}