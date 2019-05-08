package com.programmer.jgallos.ma_m;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ClassReviewActivity extends AppCompatActivity {

    private TextView subjectTextView;
    String subjectBuffer = null;
    private TextView instructorTextView;
    String instructorBuffer = null;
    private TextView scheduleTextView;
    String scheduleBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassReviewActivity.this, NotifyActivity.class);
                startActivity(intent);
            }
        });

        subjectTextView = (TextView)findViewById(R.id.classTextView);
        instructorTextView = (TextView)findViewById(R.id.instructorTextView);
        scheduleTextView = (TextView)findViewById(R.id.scheduleTextView);

        subjectBuffer = getIntent().getExtras().getString("subjectReviewed");
        instructorBuffer = "x";
        scheduleBuffer = "x";

        subjectTextView.setText("Class: " + subjectBuffer);
        instructorTextView.setText("Instructor: " + "x");
        scheduleTextView.setText("Schedule: " + "x");

    }

}
