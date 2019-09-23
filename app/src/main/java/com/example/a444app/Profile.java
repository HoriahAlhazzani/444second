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

import androidx.fragment.app.Fragment;



public class Profile extends Fragment {

    EditText name, email, password;
    ImageView imgUser;
    private ProgressDialog progressDialog;
    private final String LOG = Profile.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.profilex, container, false);

        name = view.findViewById(R.id.pname_signup);
        email = view.findViewById(R.id.pemail_signup);
        password = view.findViewById(R.id.ppw_signup);
        imgUser = view.findViewById(R.id.personalImage);

        fillData();
//        executeProfileApiRequest();
        return view;
    }

    private void fillData() {
        //fill username
        if (!MySharedPrefrence.getString(getContext(), Constants.Keys.USER_NAME, "").equals("")) {
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
    }//end fillData()



}
