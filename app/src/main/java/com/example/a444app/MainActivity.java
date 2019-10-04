package com.example.a444app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth.AuthStateListener authStateListener;

    HomeFragment homeFragment;


    private TextView txtName,txtId;
    private ImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            setTitle(R.string.menu_home);
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapActivity()).commit();//put home fragment here instead
            navigationView.setCheckedItem(R.id.nav_home);
        }//end if
//
        txtName = navigationView.getHeaderView(0).findViewById(R.id.navName);
        txtId=navigationView.getHeaderView(0).findViewById(R.id.navID);

//
        homeFragment = new HomeFragment();
        replaceFrag(homeFragment);
//
        if (!MySharedPrefrence.getString(this, Constants.Keys.USER_FNAME, "").equals(""))
            txtName.setText(MySharedPrefrence.getString(this, Constants.Keys.USER_FNAME, ""));
        if(!MySharedPrefrence.getString(this, Constants.Keys.USER_ID, "").equals(""))
           txtId.setText(MySharedPrefrence.getString(this, Constants.Keys.USER_ID, ""));
        //end if


    }//end onCreate()

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//
//        if (count == 0) {
//            super.onBackPressed();
//            //additional code
//        } else {
//            getSupportFragmentManager().popBackStack();
//        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            setTitle(R.string.menu_home);
            replaceFrag(homeFragment);



        } else if (id == R.id.nav_profile) {
            setTitle(R.string.menu_profile);

            Profile profile = new Profile();
            replaceFrag(profile);


        } else if (id == R.id.nav_MyLocker) {
            setTitle(R.string.menu_MyLocker);
            MyLocker myLocker = new MyLocker();
            replaceFrag(myLocker);

        } else if (id == R.id.nav_setting) {
            setTitle(R.string.menu_setting);
            Settings settings = new Settings();
            replaceFrag(settings);}


        else if (id == R.id.nav_bye) {
            setTitle(R.string.menu_Logout);
            new AlertDialog.Builder(this).setTitle("Logout").setMessage("Are you sure you want to log out?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                          //  logout();
                            FirebaseAuth.getInstance().signOut();
                            logout();

                        }}).setNegativeButton("NO",null).show();}



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




public  void logout(){
    String applang = MySharedPrefrence.getString(this,Constants.Keys.APP_LANGUAGE,"en");
    MySharedPrefrence.clearData(this);
    MySharedPrefrence.putString(this,Constants.Keys.APP_LANGUAGE ,applang);
    Intent intent = new Intent(this , LoginActivity.class);
    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
    intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);



    finish();



    startActivity(intent);
    //finish();

}
    private void replaceFrag(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();


    } //end replaceFrag

}