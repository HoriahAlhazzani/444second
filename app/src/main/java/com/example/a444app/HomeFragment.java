package com.example.a444app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;


public class HomeFragment extends Fragment implements View.OnClickListener  {

    View view;

  private final String LOG = HomeFragment.class.getSimpleName();

  private DatabaseReference ref;
  private ProgressDialog progressDialog;
    Intent intent;

  private Button areaPin1,areaPin2;
  private Button areaPin3,areaPin4;
    private Button areaPin5,areaPin6;
    private Button areaPin7,areaPin8,areaPin9;

//  private LockersArea lockersFragment;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);



        areaPin1 =(Button)view.findViewById(R.id.area_pin1);
        areaPin2 =(Button)view.findViewById(R.id.area_pin2);
        areaPin3 =(Button)view.findViewById(R.id.area_pin3);
        areaPin4 =(Button)view.findViewById(R.id.area_pin4);
        areaPin5 =(Button)view.findViewById(R.id.area_pin5);
        areaPin6 =(Button)view.findViewById(R.id.area_pin6);
        areaPin7 =(Button)view.findViewById(R.id.area_pin7);
        areaPin8 =(Button)view.findViewById(R.id.area_pin8);
        areaPin9 =(Button)view.findViewById(R.id.area_pin9);







                areaPin1.setOnClickListener(this);
                areaPin2.setOnClickListener(this);
        areaPin3.setOnClickListener(this);
                areaPin4.setOnClickListener(this);
                areaPin5.setOnClickListener(this);
                areaPin6.setOnClickListener(this);
                areaPin7.setOnClickListener(this);
                areaPin8.setOnClickListener(this);
                areaPin9.setOnClickListener(this);



//        areaPin1.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                readDB();
//                Intent intent = new Intent(getActivity(), LockersArea.class);
//                startActivity(intent);
//
//            }
//        });


        return view;

    }//end onCreateView

    @Override
    public void onClick(View view) {
//        if (MySharedPrefrence.getBoolean(getContext(), Constants.Keys.IS_ADMIN, false)) {
//            Toast.makeText(getContext(), "Sorry you can't access this now, Soon you will be able .."
//                    ,
//                    Toast.LENGTH_SHORT).show();
//return;
//
//        }
        switch (view.getId()){
            case R.id.area_pin1:
                //action
                MySharedPrefrence.putint(getContext(), Constants.Keys.AREA, 1);
                 intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);

                break;

            case R.id.area_pin2:
                MySharedPrefrence.putint(getContext(), Constants.Keys.AREA, 2);
                 intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);

                break;

            case R.id.area_pin3:
                MySharedPrefrence.putint(getContext(), Constants.Keys.AREA, 3);
                intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);
                break;

            case R.id.area_pin4:
                MySharedPrefrence.putint(getContext(), Constants.Keys.AREA, 4);
                intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);
                break;

            case R.id.area_pin5:
                MySharedPrefrence.putint(getContext(), Constants.Keys.AREA, 5);
                intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);
                break;

            case R.id.area_pin6:
                MySharedPrefrence.putint(getContext(), Constants.Keys.AREA, 6);
                intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);
                break;

            case R.id.area_pin7:
                MySharedPrefrence.putint(getContext(), Constants.Keys.AREA, 7);
                intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);
                break;

            case R.id.area_pin8:
                MySharedPrefrence.putint(getContext(), Constants.Keys.AREA, 8);
                intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);
                break;

            case R.id.area_pin9:
                MySharedPrefrence.putint(getContext(), Constants.Keys.AREA, 9);
                intent = new Intent(getActivity(), LockersArea.class);
                startActivity(intent);
                break;

        }
    }

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