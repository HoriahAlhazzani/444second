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



public class Settings extends Fragment {
    Switch notification;
    CheckedTextView language;
    private ProgressDialog progressDialog;
    private final String LOG = Settings.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settingsx, container, false);

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

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Languages.class);
                startActivity(intent);

            }
        });//end setOnClickListener



        return view;
    }

//    private void executeEnableNotiApiRequest(final String enable) {
//        showProgressDialog(false);
//
//        ApiClient.getApi().getEnableNotiRequest(
//                MySharedPrefence.getString(getContext(),Constants.Keys.USER_ID,"en"),enable).enqueue(new Callback<GeneralResponse>() {
//                    @Override
//                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
//                        hideProgressDialog();
//                        Log.i(LOG,"onResponse : Success");
//                           if(response.body()!=null){
//                               if(response.body().getStatus()==1){
//                                   MySharedPrefence.putString(getContext(),Constants.Keys.ENABLE_NOTI,enable);
//                                   }else{
//                                        showDialog(response.body().getMsg());
//                                             }//end else
//                                                       }//end if
//                                                          }//end onResponse()
//
//         @Override
//                     public void onFailure(Call<GeneralResponse> call, Throwable t) {
//
//         }
//                }
//        );//end enqueue
//    }//end executeLoginApiRequest()

//    public void showProgressDialog(boolean isCancelable){
//        try{
//            if(progressDialog!=null||progressDialog.isShowing()){
//                progressDialog= new ProgressDialog(getContext());
//                progressDialog.setMessage(getString(R.string.wait));
//                progressDialog.setCancelable(isCancelable);
//                progressDialog.show();
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }// end showProgressDialog
//
//    public void hideProgressDialog(){
//
//        if(progressDialog!=null)
//            if(progressDialog.isShowing()&& !isVisible())
//                progressDialog.dismiss();
//
//    }//end hideProgressDialog
//
//    public void showDialog(String msg){
//        final AlertDialog alertDialog=new AlertDialog.Builder(getContext()).create();
//
//        alertDialog.setMessage(msg);
//        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                alertDialog.dismiss();
//            }
//        });
//        alertDialog.show();
//    }//end showDialog()


}