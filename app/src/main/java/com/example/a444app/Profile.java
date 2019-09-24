package com.example.a444app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class Profile extends Fragment {

    EditText name, email, password,id,phoneE;
    TextView resetPass_text;
    private ProgressDialog progressDialog;
    FirebaseAuth auth ;
    String phoneToValidate;
    String MobilePattern = "[0-9]{10}";
    Button saveChanges_button;


    private final String LOG = Profile.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.profilex, container, false);
        auth = FirebaseAuth.getInstance();
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
                auth.sendPasswordResetEmail(MySharedPrefrence.getString(getContext(), Constants.Keys.USER_EMAIL, ""))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(),"Check your Email to reset your password."
                                            ,
                                            Toast.LENGTH_SHORT).show();
                                    Log.d(LOG, "Check your Email to reset your password.");
                                }
                            }
                        });
            }
        });

        saveChanges_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered())
                    saveChanges();
                Toast.makeText(getActivity(),getResources().getString(R.string.changes_saved)
                        ,
                        Toast.LENGTH_SHORT).show();
startActivity(new Intent(getActivity(),MainActivity.class));
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



//            Glide.with(this).load(MySharedPrefrence.getString(getContext(),Constants.Keys.USER_IMG,"")) .apply(RequestOptions.circleCropTransform()).into(imgUser);


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
