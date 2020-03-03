package com.app.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private FloatingActionButton fab;
    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Toast.makeText(this, "Clicked Fab", Toast.LENGTH_SHORT).show();
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId())   {
                case R.id.action_home:
                    selectedFragment = HomeFragment.getInstance();
                    break;

                case R.id.action_hospital:
                    selectedFragment = Profile.getInstance();
                    break;
            }

            setFragment(selectedFragment);

            return false;
        });
        setFragment(HomeFragment.getInstance());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
    }
}
