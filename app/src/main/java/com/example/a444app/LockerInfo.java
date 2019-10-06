package com.example.a444app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;
import java.util.Queue;

//XXXXXXXXXXXXXXXXXXXX

public class LockerInfo extends BaseActivity{

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private  DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Locker> firebaseRecyclerOptions;
    private FirebaseRecyclerAdapter<Locker, LockerItemHolder> firebaseRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locker_info);

        //set Toolbar
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
//        setTitle(getResources().getString(getAreaTitle()));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_locker);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("lockers");

        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Locker>()
                .setQuery(query, Locker.class)
                .build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Locker, LockerItemHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull LockerItemHolder blogPostHolder, int position, @NonNull Locker blogPost) {
                blogPostHolder.setLocker(blogPost);
            }

            @Override
            public LockerItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locker_info, parent, false);

                return new LockerItemHolder(view);
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

//        recyclerView.setAdapter(new RecyclerView.Adapter() {
//            @NonNull
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return 0;
//            }
//        });
//
//        new DatabaseHelper().readLockers(new DatabaseHelper.DataStatus() {
//            @Override
//            public void DataIsLoaded(List<Locker> lockers, List<String> keys) {
//                new RecyclerViewLocker().setRecycler(recyclerView,LockerInfo.this,lockers,keys);
//            }
//
//            @Override
//            public void DataIsInserted() {
//
//            }
//
//            @Override
//            public void DataIsUpdated() {
//
//            }
//
//            @Override
//            public void DataIsDeleted() {
//
//            }
//        });



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}//end class
