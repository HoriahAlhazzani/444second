package com.example.a444app;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;



public class AdminSettings extends Fragment {
    Switch notification;
    CheckedTextView language,setResetPassword;
    private ProgressDialog progressDialog;
    private final String LOG = Settings.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_settings, container, false);

        notification = (Switch) view.findViewById(R.id.setNotif);
        boolean isEnableNoti=MySharedPrefrence.getString(getContext(),Constants.Keys.ENABLE_NOTI,"1").equals("1");

        notification.setChecked(isEnableNoti);
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if(isChecked)
//                    executeEnableNotiApiRequest("1");
//                else
//                    executeEnableNotiApiRequest("0");
            }
        });//end setOnCheckedChangeListener

        language = (CheckedTextView) view.findViewById(R.id.setLang);
        setResetPassword = (CheckedTextView) view.findViewById(R.id.setResetPassword);

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Languages.class);
                startActivity(intent);

            }
        });//end setOnClickListener

        setResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForgotPasswordActivity.class);
                startActivity(intent);

            }
        });//end setOnClickListener


        return view;
    }



}