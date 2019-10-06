package com.example.a444app;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.app.PendingIntent.getActivity;
import static com.example.a444app.R.id.booking_button;

public class booking extends AppCompatActivity {

    Button booking_button;
     DatabaseReference databaseReference;
    TextView booking_text;
    TextView payment,idText;
    String Strlocker_id;
    int locker_id;
//    Locker locker;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        booking_button= findViewById(R.id.booking_button);
        idText=findViewById(R.id.idText);
        Strlocker_id=MySharedPrefrence.getString(this, Constants.Keys.LOCKOER_ID, "");
        locker_id=Integer.parseInt(Strlocker_id.trim());
        databaseReference = FirebaseDatabase.getInstance().getReference();

//        Query query = databaseReference.child("lockers").orderByChild("id").equalTo(locker_id);
        Query query = databaseReference.child("lockers").orderByChild("id").equalTo(locker_id);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Locker locker = dataSnapshot.getValue(Locker.class);
//                idText.setText(locker.getId()+"");
                idText.setText(locker_id+"");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        booking_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(booking.this, getResources().getString(R.string.bookingmessage),
                        Toast.LENGTH_LONG).show();
                booking_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        payment = findViewById(R.id.paymentID);
                        Intent intent = new Intent(booking.this, payment.class);
                        startActivity(intent);

                    }
                });
            }
        });



    }
}

