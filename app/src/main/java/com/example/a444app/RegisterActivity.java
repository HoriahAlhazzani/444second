package com.example.a444app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText fname, email, cpassword, password,phoneE,ID;
    Button registerButton;
    String MobilePattern = "[0-9]{10}";
    String phoneToValidate;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.name);
        email =findViewById(R.id.email);
        password =  findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        phoneE =  findViewById(R.id.phone);
        ID = findViewById(R.id.id);
        checkBox=findViewById(R.id.checkbox);
        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkDataEntered())
                {//start Activity
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                }
    }});


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(RegisterActivity.this);

            }
        });


}
    public boolean checkDataEntered() {
        boolean flag = true;
        if (isEmpty(fname)) {

            fname.setError(getResources().getString(R.string.enterfName));
            flag = false;
        }//end if

        if (isEmpty(email)) {

            email.setError(getResources().getString(R.string.enterEmail));
            flag = false;
        }//end if

        if (isEmpty(phoneE)) {

            phoneE.setError(getResources().getString(R.string.enterPhone));
            flag = false;
        }//end if
        if (isEmpty(ID)) {

            ID.setError(getResources().getString(R.string.enterId));
            flag = false;
        }//end if
        if (isEmpty(password)) {

            password.setError(getResources().getString(R.string.enterPassword));
            flag = false;
        }//end if
        if (isEmpty(cpassword)) {

            cpassword.setError(getResources().getString(R.string.entercPassword));
            flag = false;
        }//end if

        if (!isEmail(email.getText().toString())) {
            email.setError(getResources().getString(R.string.enterVEmail));
            flag = false;
        }//end if
        if (!confirmPassword()) {
            password.setError(getResources().getString(R.string.mismatch));
            cpassword.setError(getResources().getString(R.string.mismatch));
            flag = false;
        }//end if

        phoneToValidate=""+phoneE.getText().toString();

        if(!phoneToValidate.matches(MobilePattern)||!phoneToValidate.substring(0,2).equals("05") ) {
            phoneE.setError("Please enter valid 10 digit phone number");//string
            flag=false;
        }

        if(!ID.getText().toString().substring(0,1).equals("4")||ID.getText().toString().length()<9){
            ID.setError(getResources().getString(R.string.entervId));//string
            flag=false;
        }

        if(!checkBox.isChecked()){
            flag=false;
            Toast.makeText(RegisterActivity.this, "You have to agree to complete Registeration!",
                    Toast.LENGTH_LONG).show();
        }


        return flag;
    }//end checkDataEntered


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }//end isEmpty

    private boolean isEmail(String email) {
        //check email is belong elm.sa
        //  if (isElmEmail(email))
        //check isEmail
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
        //else return false;
    }//end isEmail()

        boolean confirmPassword() {
        String pass = password.getText().toString();
        String cpass = cpassword.getText().toString();
        return pass.equals(cpass);
    } // end confirmPassword




}
