package com.example.a444app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class LockerInfo extends BaseActivity{

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locker_info);
        toolbar = findViewById(R.id.toolbar);



        setSupportActionBar(toolbar);
//        setTitle(getResources().getString(getAreaTitle()));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }//end onCreate()

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}//end class
