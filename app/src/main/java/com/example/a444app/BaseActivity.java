package com.example.a444app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;//1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleDefaultAppLocale();
    }//end onCreate

    private void handleDefaultAppLocale() {

        setAppLocale(MySharedPrefrence.getString(this, Constants.Keys.APP_LANGUAGE, getSystemLocalLanguage()));


    }//end handleDefaultAppLocale

    protected String getSystemLocalLanguage() {

        Locale locale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            locale = getResources().getSystem().getConfiguration().getLocales().get(0);

        else
            locale = getResources().getSystem().getConfiguration().locale;

        return locale.getLanguage();


    }// end getSystemLocalLanguage

    private void setAppLocale(String language) {

        MySharedPrefrence.putString(this, Constants.Keys.APP_LANGUAGE, language);


        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());

    }//end setAppLocale

    //2

    public void showProgressDialog(boolean isCancelable) {

        try {

            if (progressDialog == null || progressDialog.isShowing()) {

                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getString(R.string.plz));
                progressDialog.setCancelable(isCancelable);

            progressDialog.show();

        }
        } catch (Exception e){

            e.printStackTrace();

    }


    }//end showProgressDialog

    public void hideProgressDialog(){

     if(progressDialog!=null)
         if(progressDialog.isShowing()&& !isDestroyed())
             progressDialog.dismiss();

    }//end hideProgressDialog

    public void showDialog(String msg){
        final AlertDialog alertDialog= new AlertDialog.Builder(this).create();
        alertDialog.setMessage(msg);
        alertDialog.setIcon(R.mipmap.ic_launcher);//////////
        alertDialog.setButton(
                androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE,
                getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                }
        );
        alertDialog.show();
    }


}//end class