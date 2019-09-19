package com.example.a444app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkDataEntered()){
                    //   startActivity();
                }

            }
        });

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
        if(!id.getText().toString().substring(0,1).equals("4")||id.getText().toString().length()<9){
            id.setError(getResources().getString(R.string.entervId));//string
            flag=false;
        }

        return flag;
    }//end checkDataEntered

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }//end isEmpty
}
