package com.example.a444app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LockersFragment extends Fragment {


    private final String LOG = LockersFragment.class.getSimpleName();

    private View view;
    private ProgressDialog progressDialog;
    private Button lockerBtn;
    private LockerInfoFragment lockerInfoFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lockers_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lockerBtn=getView().findViewById(R.id.lockerBtn);

        lockerBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                lockerInfoFragment=new LockerInfoFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, lockerInfoFragment).commit();
            }
        });
    }
}//end fragment
