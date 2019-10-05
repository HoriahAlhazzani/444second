package com.example.a444app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

public class LockersArea extends BaseActivity{


    private final String LOG = LockersArea.class.getSimpleName();

    private Toolbar toolbar;
    private Button lockerBtn;
    private LockerInfo LockerInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockers_area);

        toolbar = findViewById(R.id.toolbar);



        setSupportActionBar(toolbar);
//        setTitle(getResources().getString(getAreaTitle()));
        setTitle("Lockers");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lockerBtn=findViewById(R.id.lockerBtn);

        lockerBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LockersArea.this, LockerInfo.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}//end class