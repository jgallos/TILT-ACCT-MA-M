package com.programmer.jgallos.ma_m;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackReplyActivity extends AppCompatActivity {

    String feedback_key = null;
    String subjectBuffer = null;
    private EditText editText_reply;
    private Button button_send;

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_reply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedbackReplyActivity.this, NotifyActivity.class);
                startActivity(intent);
            }
        });

        feedback_key = getIntent().getExtras().getString("feedbackID");
        subjectBuffer = getIntent().getExtras().getString("subjectReviewed");

        editText_reply = (EditText)findViewById(R.id.editTextReply);
        button_send = (Button)findViewById(R.id.buttonSend);

        databaseRef = database.getInstance().getReference().child("Reply_" + feedback_key);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stringReply = "Manager's reply: " + editText_reply.getText().toString().trim();
                final DatabaseReference newReply = databaseRef.push();

                newReply.child("reply").setValue(stringReply);
                newReply.child("date").setValue("x");
                newReply.child("time").setValue("x").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            //Intent reply_back = new Intent(FeedbackReplyActivity.this, SingleFeedbackActivity.class);
                            //reply_back.putExtra("FeedbackID",feedback_key);
                            //reply_back.putExtra("SigninSubject", signin_subject);
                            //startActivity(reply_back);
                            Toast.makeText(FeedbackReplyActivity.this,"Reply sent!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });


            }
        });

    }

}
