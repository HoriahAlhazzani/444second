package com.example.a444app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity
//        implements View.OnClickListener
{

    LinearLayout splashLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.Logo);

// Load the animation like this
        Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);

// Start the animation like this
        imageView.startAnimation(animSlide);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                finish();

                checkIsLogin();
            }
        }, 3000); // end timer
//        splashLayout = (LinearLayout) findViewById(R.id.splashlayout);
//        splashLayout.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View view) {
//
//        checkIsLogin();
//        //finish();
//
////        startActivity(new Intent(this, LoginActivity.class));
////        finish();
//
//    }//end onClick


    private void checkIsLogin() {

        if (MySharedPrefrence.getBoolean(this, Constants.Keys.IS_LOGIN, false)) {
            if (MySharedPrefrence.getString(this, Constants.Keys.USER_ID, "").equals("400000000"))
                startActivity(new Intent(this, Main2Activity.class));

            else
            startActivity(new Intent(this, MainActivity.class));

            finish();
        }// end if
        else {

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }//end else

    }//end checkIsLogin
}
