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




public class StudentsHolder extends RecyclerView.ViewHolder
//        implements View.OnClickListener
{
    //    private Button id;
//        private int sId;// for admin
    private TextView student_name,student_id;
    private Context context;

    LockersArea lockersArea;
    public StudentsHolder(View v) {
        super(v);
//        id=(Button) itemView.findViewById(R.id.lockerId);
        student_name= (TextView)itemView.findViewById(R.id.student_name);
        student_id=itemView.findViewById(R.id.student_id);


//        v.setOnClickListener( this);
    }//end LockerItemHolder()

    public void setStudent(UserInformation userInformation){
        String n="";
//        id.setText(n);
        student_name.setText(userInformation.getUname());
        student_id.setText(userInformation.getUid());
    }//end bind()
}
