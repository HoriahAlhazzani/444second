package com.example.a444app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class LockersArea extends BaseActivity{


    private final String LOG = LockersArea.class.getSimpleName();


    private Toolbar toolbar;
    private Button lockerBtn;
    DatabaseReference reference;
    List<Locker> lockers;

    private RecyclerView recyclerView;
    private  DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Locker> firebaseRecyclerOptions;
    private FirebaseRecyclerAdapter<Locker, LockerAreaHolder> firebaseRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockers_area);

        toolbar = findViewById(R.id.toolbar);

        reference= FirebaseDatabase.getInstance().getReference();
//        DB();


        setSupportActionBar(toolbar);
//        setTitle(getResources().getString(getAreaTitle()));
        setTitle("Lockers");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_area);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("lockers");

        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Locker>()
                .setQuery(query, Locker.class)
                .build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Locker, LockerAreaHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull LockerAreaHolder blogPostHolder, int position, @NonNull Locker blogPost) {
                blogPostHolder.setLocker(blogPost);
            }

            @Override
            public LockerAreaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locker_area_recycler, parent, false);

                return new LockerAreaHolder(view);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (firebaseRecyclerAdapter!= null) {
            firebaseRecyclerAdapter.stopListening();
        }
    }

    private void DB() {
        //(int id, int num, char area, int sId, boolean availability, char size)
        LinearLayout layout;
        lockers=new ArrayList<>();
//        int i=1;
//        //--A
//
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//        lockers.add(new Locker(i++,1,"-1",true,"s"));
//
        //--2
        int c=1;

        for(int i=1;i<=9;i++){

            for(int j=1;j<=16;j++){
                lockers.add(new Locker(c++,i,"-1",true,"s"));




            }}


        reference.child("lockers").setValue(lockers);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}//end class