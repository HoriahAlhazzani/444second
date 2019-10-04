package com.example.a444app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText CurrentPassword,newPassword,confirmNewPassword;
    Button saveNewPasswordButton;
    FirebaseUser user;
    private final String TAG = Profile.class.getSimpleName();
    DatabaseReference ref;
//    FirebaseUser user;
Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        CurrentPassword=findViewById(R.id.CurrentPassword);
        newPassword=findViewById(R.id.newPassword);
        confirmNewPassword=findViewById(R.id.confirmNewPassword);
        saveNewPasswordButton=findViewById(R.id.saveNewPasswordButton);

        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.reset_password1));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        user=FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference();


        saveNewPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkDataEntered()) {

                    user = FirebaseAuth.getInstance().getCurrentUser();
                    final String email = user.getEmail();

                    AuthCredential credential = EmailAuthProvider
                            .getCredential(email, CurrentPassword.getText().toString().trim());

// Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        ///
                                        if (checkDataEntered()) {


                                            user.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.d(TAG, "Password updated");//toast message
                                                        Toast.makeText(ForgotPasswordActivity.this, "password has been updated successfully."
                                                                ,
                                                                Toast.LENGTH_SHORT).show();

                                                        updatePasswordInDatabase();

                                                        startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));
                                                    } else {
                                                        Log.d(TAG, "password not updated.");
                                                        Toast.makeText(ForgotPasswordActivity.this, "password not updated."
                                                                ,
                                                                Toast.LENGTH_SHORT).show();
//                                                CurrentPassword.setError(getResources().getString(R.string.incorrectPassword));


                                                    }
                                                }
                                            });
                                            ////
                                        }
                                    } else {
                                        Toast.makeText(ForgotPasswordActivity.this, "password not updated."
                                                ,
                                                Toast.LENGTH_SHORT).show();
                                        CurrentPassword.setError(getResources().getString(R.string.incorrectPassword));


                                        Log.d(TAG, "Error auth failed");
                                    }
                                }
                            });


                }
            }});


    }
    public boolean checkDataEntered(){


        boolean flag = true;
        if (isEmpty(CurrentPassword)) {

            CurrentPassword.setError(getResources().getString(R.string.enterPassword));
            flag = false;
        }//end if
        if (isEmpty(newPassword)) {

            newPassword.setError(getResources().getString(R.string.enterPassword));
            flag = false;
        }//end if

        if (isEmpty(confirmNewPassword)) {

            confirmNewPassword.setError(getResources().getString(R.string.enterPassword));
            flag = false;
        }//end if
//        if (!isEmpty(CurrentPassword)) {
//
//            if(CurrentPassword.length()<6){
//            CurrentPassword.setError(getResources().getString(R.string.enterPassword));
//            flag = false;}
//        }//end if
        if (!isEmpty(newPassword)) {

            if(newPassword.length()<6){
                newPassword.setError(getResources().getString(R.string.passLength));
                flag = false;}
        }//end if

        if (!isEmpty(confirmNewPassword)) {

            if(confirmNewPassword.length()<6){
                confirmNewPassword.setError(getResources().getString(R.string.passLength));
                flag = false;}
        }//end if

        if (!confirmPassword()) {
            newPassword.setError(getResources().getString(R.string.mismatch));
            confirmNewPassword.setError(getResources().getString(R.string.mismatch));
            flag = false;
        }//end if



        return flag;
    }//end checkDataEntered

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }//end isEmpty

    boolean confirmPassword() {
        String pass = newPassword.getText().toString();
        String cpass = confirmNewPassword.getText().toString();
        return pass.equals(cpass);
    } // end confirmPassword



public void updatePasswordInDatabase() {
//todo
    ref.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            dataSnapshot.getRef().child("upassword").setValue(newPassword.getText().toString().trim());

//                            dialog.dismiss();

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d("User", databaseError.getMessage());
        }
    });

}
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }//end onSupportNavigateUp
}