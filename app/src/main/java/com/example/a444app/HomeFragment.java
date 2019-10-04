package com.example.a444app;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment {

    View view;

  private final String LOG = HomeFragment.class.getSimpleName();

  private ProgressDialog progressDialog;
  private ImageView areaPin,areaPin2;
  private ImageView areaPin3,areaPin4;
  private LockersFragment lockersFragment;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        return view;

    }//end onCreateView

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        goToArea(view);

        areaPin =getView().findViewById(R.id.area_pin1);

        areaPin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                lockersFragment=new LockersFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, lockersFragment).commit();
            }
        });
        //---2
        areaPin2 =getView().findViewById(R.id.area_pin2);

        areaPin2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                lockersFragment=new LockersFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, lockersFragment).commit();
            }
        });
        //---3
        areaPin3 =getView().findViewById(R.id.area_pin3);

        areaPin3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                lockersFragment=new LockersFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, lockersFragment).commit();
            }
        });

        //---4
        areaPin4 =getView().findViewById(R.id.area_pin4);

        areaPin4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                lockersFragment=new LockersFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, lockersFragment).commit();
            }
        });

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
//                    // handle back button's click listener
//                    Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();
//                    return true;
//            }
//
//                return false;
//            }
//        });
//    }

//    private void goToArea(View view) {
//        int id = view.getId();
//
//
//        if (id == R.id.area_pin1) {
//            setTitle(R.string.menu_home);
//            replaceFrag(homeFragment);
//            //  getSupportFragmentManager().beginTransaction().replace(R.id.container, new map()).commit();
////            startActivity(new Intent(MainActivity.this,MapActivity.class));
//
////            MapActivity mapActivity = new MapActivity();
////            getSupportFragmentManager().beginTransaction().replace(R.id.container, mapActivity).commit();
//
//
//        } else if (id == R.id.nav_profile) {
//            setTitle(R.string.menu_profile);
//
//            Profile profile = new Profile();
//            replaceFrag(profile);
////            History history = new History();
////            getSupportFragmentManager().beginTransaction().replace(R.id.container, history).commit();
//
//        } else if (id == R.id.nav_MyLocker) {
//            setTitle(R.string.menu_MyLocker);
//            MyLocker myLocker = new MyLocker();
//            replaceFrag(myLocker);
////            checkIsCreditCardSaved();
//
////        } else if (id == R.id.nav_Offers) {
////            setTitle(R.string.menu_Offers);
//////            getSupportFragmentManager().beginTransaction().replace(R.id.container, new Offers()).commit();
//
//        } else if (id == R.id.nav_setting) {
//            setTitle(R.string.menu_setting);
//            Settings settings = new Settings();
//            replaceFrag(settings);}
////            getSupportFragmentManager().beginTransaction().replace(R.id.container, new Settings()).commit();}
//
////        } else if (id == R.id.nav_Contact_us) {
////            setTitle(R.string.menu_Contactus);
////            getSupportFragmentManager().beginTransaction().replace(R.id.container, new Contact_us()).commit();
////
////        }
////
//        else if (id == R.id.nav_bye) {
//            setTitle(R.string.menu_Logout);
//            new AlertDialog.Builder(this).setTitle("Logout").setMessage("Are you sure you want to log out?")
//                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            //  logout();
//                            FirebaseAuth.getInstance().signOut();
//                            logout();
//
//                        }}).setNegativeButton("NO",null).show();}
//
////            getSupportFragmentManager().beginTransaction().replace(R.id.container, new BecomeDriver()).commit();
//
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create new fragment and transaction
//        Fragment newFragment = new LockersFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//// Replace whatever is in the fragment_container view with this fragment,
//// and add the transaction to the back stack if needed
//        transaction.replace(R.id.fragment_container, newFragment);
//        transaction.addToBackStack(null);
//
//// Commit the transaction
//        transaction.commit();


    }//end onCreate




}//end fragment