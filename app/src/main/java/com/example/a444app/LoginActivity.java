package com.example.a444app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private static final String TAG = "EmailPassword";

    Intent intent;

    private final String LOG = LoginActivity.class.getSimpleName();

    TextView signup_txt,fpass ;
    EditText password,id;
    Button loginButton;

    DatabaseReference ref;
    FirebaseUser user;
    String Email;
    String uid;
    String email;

//    ArrayAdapter<Strin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        signup_txt= findViewById(R.id.signup_txt);
        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        password=findViewById(R.id.editTextPassword);
        id=findViewById(R.id.editTextID);


        loginButton=findViewById(R.id.loginButton);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkDataEntered()) {

                    if(id.getText().toString().equals("400000000")){
                         Email = "athalghannam@gmail.com";
                    }
                    else{
                     Email = id.getText().toString().trim() + "@student.ksu.edu.sa";}
                    String Password = password.getText().toString().trim();
                    mAuth.signInWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        currentUser = mAuth.getCurrentUser();
                                        if(currentUser.isEmailVerified()){
//                                        finish();
//                                        startActivity(new Intent(getApplicationContext(),
//                                                MainActivity.class));

                                            /// forgot password need to this option to change password in database
                                            user=FirebaseAuth.getInstance().getCurrentUser();
                                            uid=user.getUid();

                                            ref = FirebaseDatabase.getInstance().getReference();

//todo
                                            ref.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {

                                                    dataSnapshot.getRef().child("upassword").setValue(password.getText().toString().trim());

//                            dialog.dismiss();

                                                }
                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                    Log.d("User", databaseError.getMessage());
                                                }
                                            });



                                            ref.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    // This method is called once with the initial value and again
                                                    // whenever data at this location is updated.

                                                    String uname=dataSnapshot.child("users").child(uid).child("uname").getValue(String.class);
                                                    String uemail=dataSnapshot.child("users").child(uid).child("uemail").getValue(String.class);
                                                    String uiid=dataSnapshot.child("users").child(uid).child("uid").getValue(String.class);
                                                    String upassword=dataSnapshot.child("users").child(uid).child("upassword").getValue(String.class);
                                                    String uphone=dataSnapshot.child("users").child(uid).child("uphone").getValue(String.class);

                                                    MySharedPrefrence.putString(LoginActivity.this, Constants.Keys.USER_FNAME, uname);
                                                    //lname.getText().toString()
                                                    MySharedPrefrence.putString(LoginActivity.this, Constants.Keys.USER_EMAIL, uemail);
                                                    MySharedPrefrence.putString(LoginActivity.this, Constants.Keys.PHONE, uphone);
                                                    MySharedPrefrence.putString(LoginActivity.this,Constants.Keys.USER_ID,uiid);
                                                    MySharedPrefrence.putString(LoginActivity.this, Constants.Keys.USER_PASS, upassword);


                                                }

                                                @Override
                                                public void onCancelled(DatabaseError error) {
                                                    // Failed to read value
                                                    Log.w(LOG, "Failed to read value.", error.toException());
                                                }
                                            });


                                            executeLogin();
                                        }
                                        else {
                                            Toast.makeText(LoginActivity.this, "please verify your student email to login.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(LoginActivity.this, "couldn't login "
                                                +task.getException().getMessage(),

                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }}});

        fpass = (TextView) findViewById(R.id.forgot_password_tv);

        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView textView = new TextView(LoginActivity.this);
                textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                textView.setText(getResources().getString(R.string.forgot_password));
                textView.setPadding(260, 30, 20, 30);
                textView.setTextSize(20F);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                final EditText input = new EditText(LoginActivity.this);

                int maxLength = 9;
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(maxLength);

                input.setHint(getResources().getString(R.string.login_justid_hint));
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setFilters(FilterArray);
                alertDialog.setView(input);
                alertDialog.setCustomTitle(textView);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton(getResources().getString(R.string.send), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        if(input.getText().toString().equals("400000000")){
                            email="athalghannam@gmail.com";
                        }

                        else{
                            email=input.getText().toString()+ "@student.ksu.edu.sa";
                        }

                        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,
                                            "please check your student mail for reset your password.",
                                            Toast.LENGTH_SHORT).show();
//                                                    finish();
//                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                }
                                else {

                                    Toast.makeText(LoginActivity.this,
                                            task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });

                alertDialog.show();
            }
        });


    }
    public boolean checkDataEntered(){


        boolean flag = true;
        if (isEmpty(id)) {

            id.setError(getResources().getString(R.string.enterId));
            flag = false;
        }//end if
        if (isEmpty(password)) {

            password.setError(getResources().getString(R.string.enterPassword));
            flag = false;
        }//end if

        if (!isEmpty(id)) {

            if(!id.getText().toString().substring(0,1).equals("4")||id.getText().toString().length()<9){
            id.setError(getResources().getString(R.string.entervId));//string
            flag=false;
        }}

        return flag;
    }//end checkDataEntered

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }//end isEmpty
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
    private void executeLogin() {

        MySharedPrefrence.putBoolean(this, Constants.Keys.IS_LOGIN, true);
//        MySharedPrefrence.putString(this, Constants.Keys.USER_EMAIL, id.getText().toString()+"@student.ksu.edu.sa");
//        MySharedPrefrence.putString(this,Constants.Keys.USER_ID,id.getText().toString());
//        MySharedPrefrence.putString(this, Constants.Keys.USER_PASS, password.getText().toString());

//
//        MySharedPrefrence.putString(this, Constants.Keys.PHONE, phoneE.getText().toString());
//        MySharedPrefrence.putString(this, Constants.Keys.USER_FNAME, fname.getText().toString());
//
        if(id.getText().toString().equals("400000000")) {
            intent = new Intent(this, Main2Activity.class);
}

      else {         intent = new Intent(this, MainActivity.class);
      }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    }

