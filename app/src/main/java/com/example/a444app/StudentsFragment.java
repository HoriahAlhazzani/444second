package com.example.a444app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class StudentsFragment extends Fragment {
    View view;

    private final String LOG = StudentsFragment.class.getSimpleName();
    private ProgressDialog progressDialog;


    private TextView llockerSelected,llockerUnselected;

    private int selectedPos = RecyclerView.NO_POSITION;

    private Toolbar toolbar;
    private Button lockerBtn;
    DatabaseReference reference;
    List<UserInformation> students;

    TextView student_id;

    private RecyclerView recyclerView;
    private  DatabaseReference databaseReference;
    FirebaseRecyclerOptions<UserInformation> firebaseRecyclerOptions;
    private FirebaseRecyclerAdapter<UserInformation, StudentsHolder> firebaseRecyclerAdapter;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.students_fragment, container, false);

        reference= FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_students);

        //todo

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        //{
//        public boolean canScrollVertically() {
//            return false;
//        }
//    };

        databaseReference = FirebaseDatabase.getInstance().getReference();
     //   int ar = MySharedPrefrence.getint(getContext(), Constants.Keys.AREA,-1 );
        Query query = databaseReference.child("users").orderByChild("book").equalTo(true);

        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<UserInformation>()
                .setQuery(query, UserInformation.class)
                .build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserInformation, StudentsHolder>(firebaseRecyclerOptions) {


            @Override
            protected void onBindViewHolder(@NonNull StudentsHolder studentsHolder, int i, @NonNull UserInformation userInformation) {
           //     if(userInformation.getSize().equalsIgnoreCase("l"))
                    studentsHolder.setStudent(userInformation);
//
            }


            @Override
            public StudentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_recycler, parent, false);

                return new StudentsHolder(view);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        student_id=view.findViewById(R.id.student_id);

                        MySharedPrefrence.putString(getContext(),Constants.Keys.SEARCHED_STUDENT,student_id.getText().toString().trim());
                        startActivity(new Intent(getContext(),StudentInfoActivity.class));

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        return view;

    }//end onCreateView

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }//end onCreate
    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (firebaseRecyclerAdapter!= null) {
            firebaseRecyclerAdapter.stopListening();
        }
    }



}
