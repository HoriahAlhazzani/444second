package com.example.a444app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyLocker extends Fragment {
    View view;

    private final String LOG = HomeFragment.class.getSimpleName();
    private ProgressDialog progressDialog;



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mylocker_fragment, container, false);

        return view;

    }//end onCreateView

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }//end onCreate

}
