package com.example.a444app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private static final String TAG = "EmailPassword";


    TextView signup_txt,fpass ;
    EditText password,id;
    Button loginButton;

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

                    String Email = id.getText().toString().trim() + "@student.ksu.edu.sa";
                    String Password = password.getText().toString().trim();
                    mAuth.signInWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        currentUser = mAuth.getCurrentUser();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(),
                                                MainActivity.class));
                                    }else {
                                        Toast.makeText(LoginActivity.this, "couldn't login",
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
                input.setHint(getResources().getString(R.string.login_email_hint));
                alertDialog.setView(input);
                alertDialog.setCustomTitle(textView);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton(getResources().getString(R.string.send), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        mAuth.sendPasswordResetEmail(input.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,
                                            "please check your email for reset your password.",
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
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }


}
