package com.example.a444app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.app.PendingIntent.getActivity;
import static com.example.a444app.R.id.booking_button;

public class booking extends BaseActivity {

    private Toolbar toolbar;
    Button booking_button;
     DatabaseReference databaseReference;
     TextView num,area,availability,size;

    TextView booking_text;
    TextView payment,idText;
    String Strlocker_id;
    int locker_id;

//    private FirebaseRecyclerAdapter<Locker, LockerItemHolder> firebaseRecyclerAdapter;

 Locker locker;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


//        idText = findViewById(R.id.idText);
        toolbar = findViewById(R.id.toolbar);
        Strlocker_id = MySharedPrefrence.getString(this, Constants.Keys.LOCKOER_ID, "");
//        locker_id = Integer.parseInt(Strlocker_id.trim());
        databaseReference = FirebaseDatabase.getInstance().getReference();
//        Log.i("booking",locker_id+"");
        Log.i("booking",Strlocker_id);

        //---

        num=findViewById(R.id.lockerNum);
        area=findViewById(R.id.lockerArea);
        availability=findViewById(R.id.lockerAva);
        size =findViewById(R.id.lockerSize);
        //--

//        String id = MySharedPrefrence.getString(this, Constants.Keys.LOCKOER_ID,"" );

        setSupportActionBar(toolbar);
//        setTitle("Locker Information");
        setTitle("");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        Query query = databaseReference.child("lockers").orderByChild("id").equalTo(locker_id);
//        Query query = databaseReference.child("lockers").orderByChild("id").equalTo("3");

                locker_id = Integer.parseInt(Strlocker_id.trim());

        Query query = databaseReference.child("lockers").child(locker_id-1+"");


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                dataSnapshot.getChildren();
              locker = dataSnapshot.getValue(Locker.class);
//                idText.setText(locker.getId()+"");
//                idText.setText(locker_id+"");

                    setLocker(locker);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void setLocker(Locker locker){
        String n=num.getText()+"\t"+locker.getId();
        num.setText(n);
        String ar=locker.getArea()+"";
        area.setText(ar);// rtn name of area

        //price

        if(locker.isAvailability())
            availability.setText(R.string.available);
        else
            availability.setText(R.string.unavailable);
        String s=locker.getSize()+"";
        size.setText(s);// rtn name of size

    }//end bind()

//        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder()
//                .setQuery(query, Locker.class)
//                .build();
//        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Locker, LockerItemHolder>(firebaseRecyclerOptions) {
//
//            @NonNull
//            @Override
//            public LockerItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookinglayout, parent, false);
//
//
//
//
//                return new LockerItemHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull LockerItemHolder lockerItemHolder, int i, @NonNull Locker locker) {
//                lockerItemHolder.setLocker(locker);
//            }
//
//        };
//        recyclerView.setAdapter(firebaseRecyclerAdapter);



//    @Override
//    protected void onStart() {
//        super.onStart();
//        firebaseRecyclerAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        if (firebaseRecyclerAdapter!= null) {
//            firebaseRecyclerAdapter.stopListening();
//        }






    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

