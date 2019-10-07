package com.example.a444app;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



class LockerItemHolder extends RecyclerView.ViewHolder{
    private TextView num,area,availability,size;
//        private int sId;// for admin


    public LockerItemHolder(View v) {
        super(v);
        num=(TextView)itemView.findViewById(R.id.lockerNum);
        area=(TextView)itemView.findViewById(R.id.lockerArea);
        availability=(TextView)itemView.findViewById(R.id.lockerAva);
        size =(TextView)itemView.findViewById(R.id.lockerSize);
    }//end LockerItemHolder()

    public void setLocker(Locker locker){
        String n=locker.getId()+"/t"+num.getText();
        num.setText(n);
        String ar=locker.getArea()+"";
        area.setText(ar);// rtn name of area

        if(locker.isAvailability())
            availability.setText(R.string.available);
        else
            availability.setText(R.string.unavailable);
        String s=locker.getSize()+"";
        size.setText(s);// rtn name of size

    }//end bind()

}//end inner class