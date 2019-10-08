package com.example.a444app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Color;




class LockerAreaHolder extends RecyclerView.ViewHolder
//        implements View.OnClickListener
{
//    private Button id;
//        private int sId;// for admin
    private TextView lockerSelected,lockerUnselected;
    private Context context;

LockersArea lockersArea;
    public LockerAreaHolder(View v) {
        super(v);
//        id=(Button) itemView.findViewById(R.id.lockerId);
        lockerSelected= (TextView)itemView.findViewById(R.id.lockerSelected);
        lockerUnselected= (TextView)itemView.findViewById(R.id.lockerUnselected);

//        v.setOnClickListener( this);
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


      if(locker.isAvailability()){
          lockerUnselected.setVisibility(View.GONE);

          lockerSelected.setVisibility(View.VISIBLE);//
      }
       else {
          lockerSelected.setVisibility(View.GONE);
          lockerUnselected.setVisibility(View.VISIBLE);//


      }
    }//end bind()

//    @Override
//    public void onClick(View v) {
//        // Below line is just like a safety check, because sometimes holder could be null,
////        // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
////        if (getAdapterPosition() == RecyclerView.NO_POSITION)
////            return;
////                                MySharedPrefrence.putString(lockersArea,Constants.Keys.LOCKOER_ID,lockerSelected.getText().toString().replaceAll("\n","").trim());
//
//
//        // Updating old as well as new positions
////        RecyclerView.Adapter.notifyItemChanged(selected_position);
////        selected_position = getAdapterPosition();
////        notifyItemChanged(selected_position);
//
//        // Do your another stuff for your onClick
//    }

}//end inner class