package com.example.a444app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Profile extends Fragment {

    EditText name, email, password,id,phoneE;
    TextView resetPass_text;
    private ProgressDialog progressDialog;
    String phoneToValidate;
    String MobilePattern = "[0-9]{10}";
    Button saveChanges_button;

     FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseUser user;
    String uid;



    private final String LOG = Profile.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.profilex, container, false);


        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        ref = FirebaseDatabase.getInstance().getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                 String uname=dataSnapshot.child(uid).child("uname").getValue(String.class);
                 String uemail=dataSnapshot.child(uid).child("uemail").getValue(String.class);
                 String uiid=dataSnapshot.child(uid).child("uid").getValue(String.class);
                 String upassword=dataSnapshot.child(uid).child("upassword").getValue(String.class);
                 String uphone=dataSnapshot.child(uid).child("uphone").getValue(String.class);

                MySharedPrefrence.putString(getContext(), Constants.Keys.USER_FNAME, uname);
                MySharedPrefrence.putString(getContext(), Constants.Keys.USER_EMAIL, uemail);
                MySharedPrefrence.putString(getContext(), Constants.Keys.PHONE, uphone);
                MySharedPrefrence.putString(getContext(),Constants.Keys.USER_ID,uiid);
                MySharedPrefrence.putString(getContext(), Constants.Keys.USER_PASS, upassword);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(LOG, "Failed to read value.", error.toException());
            }
        });


        name = view.findViewById(R.id.pname_signup);
        email = view.findViewById(R.id.pemail_signup);
        password = view.findViewById(R.id.ppw_signup);
        id=view.findViewById(R.id.id);
//        imgUser = view.findViewById(R.id.personalImage);
        saveChanges_button=view.findViewById(R.id.saveChanges_button);
        phoneE=view.findViewById(R.id.phone);
        resetPass_text=view.findViewById(R.id.resetPass_text);
        resetPass_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                auth.sendPasswordResetEmail(MySharedPrefrence.getString(getContext(), Constants.Keys.USER_EMAIL, ""))
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(getActivity(),"Check your Email to reset your password."
//                                            ,
//                                            Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(getContext(),LoginActivity.class));
//                                    Log.d(LOG, "Check your Email to reset your password.");
//                                }
//                            }
//                        });
                                                    startActivity(new Intent(getContext(),ForgotPasswordActivity.class));

            }
        });

        saveChanges_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered()){



                    ref.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            dataSnapshot.getRef().child("uname").setValue(name.getText().toString().trim());
                            dataSnapshot.getRef().child("uphone").setValue(phoneE.getText().toString().trim());

//                            dialog.dismiss();

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("User", databaseError.getMessage());
                        }
                    });



                    saveChanges();
                Toast.makeText(getActivity(),getResources().getString(R.string.changes_saved)
                        ,
                        Toast.LENGTH_SHORT).show();
startActivity(new Intent(getActivity(),MainActivity.class));

                }
            }
        });

        fillData();
//        executeProfileApiRequest();
        return view;
    }

    private void fillData() {
        //fill username
        if (!MySharedPrefrence.getString(getContext(), Constants.Keys.USER_FNAME, "").equals("")) {
            name.setText(MySharedPrefrence.getString(getContext(), Constants.Keys.USER_FNAME, ""));
//            name.setEnabled(false);
        }//end if
        //fill email
        if (!MySharedPrefrence.getString(getContext(), Constants.Keys.USER_EMAIL, "").equals("")) {
            email.setText(MySharedPrefrence.getString(getContext(), Constants.Keys.USER_EMAIL, ""));
            email.setEnabled(false);
        }//end if
        //fill password
        if (!MySharedPrefrence.getString(getContext(), Constants.Keys.USER_PASS, "").equals("")) {
            password.setText(MySharedPrefrence.getString(getContext(), Constants.Keys.USER_PASS, ""));
            password.setEnabled(false);


        }//end if
        if (!MySharedPrefrence.getString(getContext(), Constants.Keys.USER_ID, "").equals("")) {
            id.setText(MySharedPrefrence.getString(getContext(), Constants.Keys.USER_ID, ""));
            id.setEnabled(false);
        }//end if
        if (!MySharedPrefrence.getString(getContext(), Constants.Keys.PHONE, "").equals("")) {
            phoneE.setText(MySharedPrefrence.getString(getContext(), Constants.Keys.PHONE, ""));
        }//end if


    }//end fillData()

    public boolean checkDataEntered() {
        boolean flag = true;
        if (isEmpty(name)) {

            name.setError(getResources().getString(R.string.enterfName));
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


        if (!isEmpty(phoneE)) {
            phoneToValidate = "" + phoneE.getText().toString();

            if (!phoneToValidate.matches(MobilePattern) || !phoneToValidate.substring(0, 2).equals("05")) {
                phoneE.setError(getResources().getString(R.string.phone_error));//string
                flag = false;
            }
        }



        return flag;
    }//end checkDataEntered

    public void saveChanges(){

        MySharedPrefrence.putString(getContext(), Constants.Keys.PHONE, phoneE.getText().toString());
        MySharedPrefrence.putString(getContext(), Constants.Keys.USER_FNAME, name.getText().toString());

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }//end isEmpty


}
