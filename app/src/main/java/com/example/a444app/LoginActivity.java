package com.example.a444app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
     TextView signup_txt ;
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
