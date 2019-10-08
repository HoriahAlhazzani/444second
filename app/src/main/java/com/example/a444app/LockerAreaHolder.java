package com.example.a444app;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



class LockerAreaHolder extends RecyclerView.ViewHolder{
//    private Button id;
//        private int sId;// for admin
    private TextView lockerSelected,lockerUnselected;


    public LockerAreaHolder(View v) {
        super(v);
//        id=(Button) itemView.findViewById(R.id.lockerId);
        lockerSelected= (TextView)itemView.findViewById(R.id.lockerSelected);
        lockerUnselected= (TextView)itemView.findViewById(R.id.lockerUnselected);
    }//end LockerItemHolder()

    public void setLocker(Locker locker){
        String n=locker.getId()+"";
//        id.setText(n);
        lockerSelected.setText("\n"+n);
                lockerUnselected.setText("\n"+n);
        lockerSelected.setTextColor(Color.rgb(255, 255, 255));
        lockerSelected.setPadding(0,30 ,0,0);
        lockerUnselected.setTextColor(Color.rgb(255, 255, 255));
        lockerUnselected.setPadding(0,30 ,0,0);
        lockerSelected.setTextSize(20);
        lockerUnselected.setTextSize(20);

      if(locker.isAvailability())
          lockerUnselected.setVisibility(View.GONE);
       else
          lockerSelected.setVisibility(View.GONE);


    }//end bind()

}//end inner class