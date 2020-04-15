package com.example.lab3_fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.MainFragment, new LoginFragment())
                    .commit();
        }
        //FragmentManager fm = getSupportFragmentManager();
        //FragmentTransaction ft = fm.beginTransaction();
       // ft.add(R.id.placeFragment, new LoginFragment());
    }
}
