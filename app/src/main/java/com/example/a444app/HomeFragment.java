package com.example.a444app;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class HomeFragment extends Fragment {

    View view;

  private final String LOG = HomeFragment.class.getSimpleName();

  private DatabaseReference ref;
  private ProgressDialog progressDialog;
  private ImageView areaPin,areaPin2;
  private ImageView areaPin3,areaPin4;
//  private LockersArea lockersFragment;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        areaPin =view.findViewById(R.id.area_pin1);

        areaPin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                readDB();
                Intent intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);

            }
        });

        return view;

    }//end onCreateView

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        goToArea(view);

    }



//        //---2
//        areaPin2 =getView().findViewById(R.id.area_pin2);
//
//        areaPin2.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                lockersFragment=new LockersArea();
//
//
//                getFragmentManager().beginTransaction().replace(R.id.container, lockersFragment).commit();
//            }
//        });
//



    private void readDB() {


//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }//end onCreate




}//end fragment