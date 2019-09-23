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

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

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
                                                    Toast.makeText(RegisterActivity.this,
                                                            "registration successful, please check your email for verification.",
                                                            Toast.LENGTH_SHORT).show();
//                                                    finish();
//                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

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
        if (!confirmPassword()) {
            password.setError(getResources().getString(R.string.mismatch));
            cpassword.setError(getResources().getString(R.string.mismatch));
            flag = false;
        }//end if

        if (!isEmpty(phoneE)) {
            phoneToValidate = "" + phoneE.getText().toString();

            if (!phoneToValidate.matches(MobilePattern) || !phoneToValidate.substring(0, 2).equals("05")) {
                phoneE.setError("Please enter valid 10 digit phone number");//string
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
            Toast.makeText(RegisterActivity.this, "You have to agree to complete Registeration!",
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




//    private void updateUI(FirebaseUser user) {
//        hideProgressDialog();
//        if (user != null) {
//            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
//                    user.getEmail(), user.isEmailVerified()));
//            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//
//            findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
//            findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
//            findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);
//
//            findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());
//        } else {
//            mStatusTextView.setText(R.string.signed_out);
//            mDetailTextView.setText(null);
//
//            findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
//            findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
//            findViewById(R.id.signedInButtons).setVisibility(View.GONE);
//        }
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
////        updateUI(currentUser);
//    }

}
