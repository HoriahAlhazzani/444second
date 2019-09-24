package com.example.a444app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private DatabaseReference databaseReference;
TextView login_text;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
//    FirebaseUser user ;

    EditText fname, cpassword, password,phoneE,ID;//email
    Button registerButton;
    String MobilePattern = "[0-9]{10}";
//    String emailPattern = "[0-9]{9}";

    String phoneToValidate;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.name);
//        email =findViewById(R.id.email);
        password =  findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        phoneE =  findViewById(R.id.phone);
        ID = findViewById(R.id.id);
        checkBox=findViewById(R.id.checkbox);

        login_text=findViewById(R.id.login_text);

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        registerButton = findViewById(R.id.register_button);

        databaseReference= FirebaseDatabase.getInstance().getReference();

// [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkDataEntered()) {//start Activity

                    String Email = ID.getText().toString().trim() + "@student.ksu.edu.sa";

                    mAuth.createUserWithEmailAndPassword(Email, password.getText().toString())
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    try {
                                        //check if successful
                                        if (task.isSuccessful()) {
                                            //User is successfully registered and logged in
                                            //start Profile Activity here

                                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if(task.isSuccessful()){
                                                    Toast.makeText(RegisterActivity.this,getResources().getString(R.string.complete_Registeration)
                                                            ,
                                                            Toast.LENGTH_SHORT).show();
//                                                    finish();
//                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                                        executeSignUp();
                                                        saveUserInfoDatabase();
                                                }
                                                else {

                                                        Toast.makeText(RegisterActivity.this,
                                                                task.getException().getMessage(),
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });



                                        }else{
                                            Toast.makeText(RegisterActivity.this, "Couldn't register, try again "
                                                            +task.getException().getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });

//        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//                    user= mAuth.getCurrentUser();
//                    user.sendEmailVerification()
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Log.d(TAG, "Email sent.");
//                                    }
//                                }
//                            });
                } else {
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });




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

//        if (isEmpty(email)) {
//
//            email.setError(getResources().getString(R.string.enterEmail));
//            flag = false;
//        }//end if

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
//
//        if (!isEmpty(email)) {
//            if (!isEmail(email.getText().toString())) {
//                email.setError(getResources().getString(R.string.enterVEmail));
//                flag = false;
//            }//end if
//        }
        if(password.length()<6){
            password.setError(getResources().getString(R.string.passLength));
            cpassword.setError(getResources().getString(R.string.passLength));
        }
        if (!confirmPassword()) {
            password.setError(getResources().getString(R.string.mismatch));
            cpassword.setError(getResources().getString(R.string.mismatch));
            flag = false;
        }//end if

        if (!isEmpty(phoneE)) {
            phoneToValidate = "" + phoneE.getText().toString();

            if (!phoneToValidate.matches(MobilePattern) || !phoneToValidate.substring(0, 2).equals("05")) {
                phoneE.setError(getResources().getString(R.string.phone_error));//string
                flag = false;
            }
        }


        if (!isEmpty(ID)) {

            if (!ID.getText().toString().substring(0, 1).equals("4") || ID.getText().toString().length() < 9) {
                ID.setError(getResources().getString(R.string.entervId));//string
                flag = false;
            }
        }

        if(!checkBox.isChecked()){
            flag=false;
            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.checkBox_error),
                    Toast.LENGTH_LONG).show();
        }


        return flag;
    }//end checkDataEntered


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }//end isEmpty

//    private boolean isEmail(String email) {
//        //check email is belong elm.sa
//         if (isUniversityEmail(email)){
//
//             String emailToValidate=email.substring(0,email.indexOf("@"));
//             return emailToValidate.matches(emailPattern);
//
//
//         }
        //check isEmail
//        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
//                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
//                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
//                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
//         else return false;
//    }//end isEmail()

        boolean confirmPassword() {
        String pass = password.getText().toString();
        String cpass = cpassword.getText().toString();
        return pass.equals(cpass);
    } // end confirmPassword
//

//    private boolean isUniversityEmail(String email) {
//        String domain = email.substring(email.indexOf("@") + 1);
//        return domain.equals("student.ksu.edu.sa");
//    }//end isElmEmail()



    private void executeSignUp() {

//        MySharedPrefrence.putBoolean(this, Constants.Keys.DARK_MODE, false);
        MySharedPrefrence.putBoolean(this, Constants.Keys.IS_LOGIN, true);
        MySharedPrefrence.putString(this, Constants.Keys.USER_FNAME, fname.getText().toString());
        //lname.getText().toString()
        MySharedPrefrence.putString(this, Constants.Keys.USER_EMAIL, ID.getText().toString()+"@student.ksu.edu.sa");
        MySharedPrefrence.putString(this, Constants.Keys.PHONE, phoneE.getText().toString());
        MySharedPrefrence.putString(this,Constants.Keys.USER_ID,ID.getText().toString());
        MySharedPrefrence.putString(this, Constants.Keys.USER_PASS, password.getText().toString());



        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//        startActivity(intent);
//        finish();


    } //end executeSignUp

    private void saveUserInfoDatabase(){

         String unamer=fname.getText().toString().trim();
         String uemailr=ID.getText().toString()+"@student.ksu.edu.sa".trim();
         String uidr=ID.getText().toString().trim();
         String upasswordr=password.getText().toString().trim();
         String uphoner=phoneE.getText().toString().trim();

         UserInformation userInformation=new UserInformation(unamer,uemailr,uidr,upasswordr,uphoner);
         FirebaseUser user=mAuth.getCurrentUser();
         databaseReference.child(user.getUid()).setValue(userInformation);


    }

}
