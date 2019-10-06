package com.example.a444app;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        lockerSelected.setText(n);
                lockerUnselected.setText(n);

//        if(locker.isAvailability())
//            availability.setText(R.string.available);
//        else
//            availability.setText(R.string.unavailable);


    }//end bind()

}//end inner class