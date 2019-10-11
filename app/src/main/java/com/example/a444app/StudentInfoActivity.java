package com.example.a444app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;

public class StudentInfoActivity extends AppCompatActivity {
    EditText name, email,id,phone,locker_no,locker_Size;
    String stu_id;
    DatabaseReference databaseReference;
    String namee,emil,idd,uphonee;


    String loc_siz;
    int locId;
    Locker locker;
    UserInformation userInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        name = findViewById(R.id.pname_signup);
        email = findViewById(R.id.pemail_signup);
        id=findViewById(R.id.id);
        phone=findViewById(R.id.phone);
        locker_no=findViewById(R.id.locker_no);
        locker_Size=findViewById(R.id.locker_Size);

        name.setFocusable(false);
        email.setFocusable(false);
        id.setFocusable(false);
        phone.setFocusable(false);
        locker_no.setFocusable(false);
        locker_Size.setFocusable(false);


        stu_id = MySharedPrefrence.getString(this, Constants.Keys.SEARCHED_STUDENT, "");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = databaseReference.child("users").orderByChild("uid").equalTo(stu_id);
        Query query2 = databaseReference.child("lockers").orderByChild("sId").equalTo(stu_id);

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                dataSnapshot.getChildren();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                     namee = ds.child("uname").getValue(String.class);
                     emil = ds.child("uemail").getValue(String.class);
                     idd = ds.child("uid").getValue(String.class);
                     uphonee=ds.child("uphone").getValue(String.class);
//                    Log.d("TAG", executed + " / " + text + " / " + timestamp);
                }

                userInformation = dataSnapshot.getValue(UserInformation.class);
//                idText.setText(locker.getId()+"");
//                idText.setText(locker_id+"");
                name.setText(namee);
                id.setText(emil);
                email.setText(idd+"@student.ksu.edu.sa");
                phone.setText(uphonee);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                dataSnapshot.getChildren();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    locId = ds.child("id").getValue(int.class);
                    loc_siz = ds.child("size").getValue(String.class);

//                    Log.d("TAG", executed + " / " + text + " / " + timestamp);
                }
//                idText.setText(locker.getId()+"");
//                idText.setText(locker_id+"");

                locker_no.setText(locId+"");

                if(loc_siz.equalsIgnoreCase("l"))
                locker_Size.setText(getResources().getString(R.string.larg));
                else
                    locker_Size.setText(getResources().getString(R.string.small));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(StudentInfoActivity.this).setTitle("Call")
                        .setMessage("Are you sure you want to call " +namee+"?")
                        .setPositiveButton("call", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:"+uphonee));

                                if (ActivityCompat.checkSelfPermission(StudentInfoActivity.this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(StudentInfoActivity.this, new String[]{CALL_PHONE}, 200);

                                }
                                startActivity(intent);
                                //todo calling
                            }}).setNegativeButton("cancel",null).show();}

        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "subject text"+ "&body=" + "body text " + "&to=" + emil);
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));
            }
        });
    }
}
