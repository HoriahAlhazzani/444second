package com.example.a444app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class LockersArea extends BaseActivity {


    private final String LOG = LockersArea.class.getSimpleName();


    private int selectedPos = RecyclerView.NO_POSITION;

    private Toolbar toolbar;
    private Button lockerBtn;
    DatabaseReference reference;
    List<Locker> lockers;

    TextView lockerSelected,lockerUnselected;

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

//                            lockerSelected.setVisibility(View.GONE);
//                            lockerUnselected.setVisibility(View.GONE);





        setSupportActionBar(toolbar);
//        setTitle(getResources().getString(getAreaTitle()));
        setTitle("Lockers");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_area);

        recyclerView.setLayoutManager(new GridLayoutManager(this,4));

        databaseReference = FirebaseDatabase.getInstance().getReference();
        int ar = MySharedPrefrence.getint(this, Constants.Keys.AREA,-1 );
        Query query = databaseReference.child("lockers").orderByChild("area").equalTo(ar);

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
//                lockerSelected=view.findViewById(R.id.lockerSelected);
//                lockerUnselected=view.findViewById(R.id.lockerUnselected);
////
////
//                lockerSelected.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        MySharedPrefrence.putString(LockersArea.this,Constants.Keys.LOCKOER_ID,lockerSelected.getText().toString().replaceAll("\n","").trim());
//                        startActivity(new Intent(LockersArea.this,booking.class));
//
//                    }
//                });
//                lockerUnselected.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        MySharedPrefrence.putString(LockersArea.this,Constants.Keys.LOCKOER_ID,lockerUnselected.getText().toString().replaceAll("\n","").trim());
//startActivity(new Intent(LockersArea.this,booking.class));
////
//                    }
//                });
                return new LockerAreaHolder(view);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(LockersArea.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        if (MySharedPrefrence.getBoolean(LockersArea.this, Constants.Keys.IS_ADMIN, false)) {
                            Toast.makeText(LockersArea.this, "Sorry you can't access this now, Soon you will be able .."
                                    ,
                                    Toast.LENGTH_SHORT).show();
                            return;

                        }
                        lockerSelected=view.findViewById(R.id.lockerSelected);
                lockerUnselected=view.findViewById(R.id.lockerUnselected);
//
//
                lockerSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MySharedPrefrence.putString(LockersArea.this,Constants.Keys.LOCKOER_ID,lockerSelected.getText().toString().replaceAll("\n","").trim());
                        startActivity(new Intent(LockersArea.this,booking.class));

                    }
                });
                lockerUnselected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        MySharedPrefrence.putString(LockersArea.this,Constants.Keys.LOCKOER_ID,lockerUnselected.getText().toString().replaceAll("\n","").trim());
//startActivity(new Intent(LockersArea.this,booking.class));

                        Toast.makeText(LockersArea.this, getResources().getString(R.string.unav)
                                ,
                                Toast.LENGTH_SHORT).show();


//
                    }
                });
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
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
//LinearLayout layout=null;
        lockers=new ArrayList<>();
        Locker locker;
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

                locker=new Locker(c++,i,"-1",true,"s");
                if(c%3==0) {
                    locker.setAvailability(false);
                    locker.setsId("439003258");
                }
               if(i%3==0){
                   locker.setSize("l");}

                   lockers.add(locker);
            }}


        reference.child("lockers").setValue(lockers);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}//end class