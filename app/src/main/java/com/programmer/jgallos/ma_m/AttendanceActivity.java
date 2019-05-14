package com.programmer.jgallos.ma_m;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AttendanceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    String subjectBuffer = null;
    private DatabaseReference mDatabase;
    String firstTwoChars = "";
    String searchString = "";


    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AttendanceActivity.this, NotifyActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewAttendance);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        subjectBuffer = getIntent().getExtras().getString("subjectReviewed");

        mDatabase = FirebaseDatabase.getInstance().getReference().child(subjectBuffer + "_attendance");


        Spinner spinner = (Spinner) findViewById(R.id.spinnerMonths);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        searchBtn = (Button)findViewById(R.id.buttonSearch);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerContent = (Spinner)findViewById(R.id.spinnerMonths);
                final String sMonths = spinnerContent.getSelectedItem().toString();

                if (sMonths.equals("January")){
                    searchString = "01";
                } else if (sMonths.equals("February")){
                    searchString = "02";
                } else if (sMonths.equals("March")) {
                    searchString = "03";
                } else if (sMonths.equals("April")) {
                    searchString = "04";
                } else if (sMonths.equals("May")) {
                    searchString = "05";
                } else if (sMonths.equals("June")) {
                    searchString = "06";
                } else  if (sMonths.equals("July")) {
                    searchString = "07";
                } else if (sMonths.equals("August")) {
                    searchString = "08";
                } else if (sMonths.equals("September")) {
                    searchString = "09";
                } else if (sMonths.equals("October")) {
                    searchString = "10";
                } else if (sMonths.equals("November")) {
                    searchString = "11";
                } else if (sMonths.equals("December")) {
                    searchString = "12";
                }


                FirebaseRecyclerAdapter<AttendanceRecord, AttendanceViewHolder> FBRA = new FirebaseRecyclerAdapter<AttendanceRecord, AttendanceViewHolder>(
                        AttendanceRecord.class,
                        R.layout.attendance_list,
                        AttendanceViewHolder.class,
                        mDatabase
                ) {
                    @Override
                    protected void populateViewHolder(AttendanceViewHolder viewHolder, AttendanceRecord model, int position) {

                        if (model.getDate().length() > 2)
                        {
                            firstTwoChars = model.getDate().substring(5,7 );
                        }
                        else
                        {
                            firstTwoChars = model.getDate();
                        }

                        //Toast.makeText(AttendanceActivity.this,firstTwoChars,Toast.LENGTH_LONG).show();

                        if (firstTwoChars.equals(searchString)) {
                            viewHolder.setDate(model.getDate());
                            viewHolder.setSignin(model.getSignin());
                            viewHolder.setSignout(model.getSignout());
                            viewHolder.setName(model.getName());

                            viewHolder.mView.setVisibility(View.VISIBLE);
                        } else {
                            viewHolder.mView.setVisibility(View.GONE);
                            viewHolder.mView.setLayoutParams(new RecyclerView.LayoutParams(0,0));
                        }

                    }
                };
                recyclerView.setAdapter(FBRA);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<AttendanceRecord, AttendanceViewHolder> FBRA = new FirebaseRecyclerAdapter<AttendanceRecord, AttendanceViewHolder>(
                AttendanceRecord.class,
                R.layout.attendance_list,
                AttendanceViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(AttendanceViewHolder viewHolder, AttendanceRecord model, int position) {
                viewHolder.setDate(model.getDate());
                viewHolder.setSignin(model.getSignin());
                viewHolder.setSignout(model.getSignout());
                viewHolder.setName(model.getName());
            }
        };
        recyclerView.setAdapter(FBRA);
    }

    public static class AttendanceViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public AttendanceViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setDate(String date) {
            TextView attendance_date = mView.findViewById(R.id.textDate);
            attendance_date.setText("Date: " + date);
        }

        public void setSignin(String signin) {
            TextView attendance_signin = mView.findViewById(R.id.textSigninTime);
            attendance_signin.setText("Sign-in Time: " + signin);
        }

        public void setSignout(String signout) {
            TextView attendance_signout = mView.findViewById(R.id.textSignoutTime);
            attendance_signout.setText("Sign-out Time: " + signout);
        }
        public void setName(String name) {
            TextView attendance_uid = mView.findViewById(R.id.textName);
            attendance_uid.setText("Name: " + name);
        }


    }

}
