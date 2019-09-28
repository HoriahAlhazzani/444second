package com.example.a444app;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;

import androidx.appcompat.widget.Toolbar;



public class Languages extends BaseActivity {

    Toolbar toolbar;
    CheckedTextView Arabic, English;
    private final String LOG = Languages.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        toolbar = findViewById(R.id.toolbar);



        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.language));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Arabic = findViewById(R.id.lang1);
        English = findViewById(R.id.lang2);

       if (MySharedPrefrence.getString(this, Constants.Keys.APP_LANGUAGE, "en").equals("en")){

           English.setCheckMarkDrawable(R.drawable.ic_check_24dp);
           Arabic.setCheckMarkDrawable(null);

       }//end if
       else
       {
           Arabic.setCheckMarkDrawable(R.drawable.ic_check_24dp);
           English.setCheckMarkDrawable(null);

       }//end else



        Arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Arabic.toggle();
                if (Arabic.isChecked()) {
                    Arabic.setCheckMarkDrawable(R.drawable.ic_check_24dp);

                    English.setCheckMarkDrawable(null);
//                    executeLanguageApiRequest("ar");


                }//end if
            }
        });//end setOnClickListener

        English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                English.toggle();
                if (English.isChecked()) {
                    English.setCheckMarkDrawable(R.drawable.ic_check_24dp);

                    Arabic.setCheckMarkDrawable(null);
//                    executeLanguageApiRequest("en");


                }//end if

            }
        });//end setOnClickListener


    }//end onCreate



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }//end onSupportNavigateUp

    private void setLanguage(String language) {

        MySharedPrefrence.putString(this, Constants.Keys.APP_LANGUAGE, language);

        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }//end setLanguage
}//end class
