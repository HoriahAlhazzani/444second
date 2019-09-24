package com.example.a444app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class Profile extends Fragment {

    EditText name, email, password,id;
    TextView resetPass_text;
    private ProgressDialog progressDialog;
    FirebaseAuth auth ;

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
        resetPass_text=view.findViewById(R.id.resetPass_text);
        resetPass_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.sendPasswordResetEmail(MySharedPrefrence.getString(getContext(), Constants.Keys.USER_EMAIL, ""))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(LOG, "Email sent.");
                                }
                            }
                        });
            }
        });
        fillData();
//        executeProfileApiRequest();
        return view;
    }

    private void fillData() {
        //fill username
        if (!MySharedPrefrence.getString(getContext(), Constants.Keys.USER_FNAME, "").equals("")) {
            name.setText(MySharedPrefrence.getString(getContext(), Constants.Keys.USER_NAME, ""));
            name.setEnabled(false);
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


    }//end fillData()



}
