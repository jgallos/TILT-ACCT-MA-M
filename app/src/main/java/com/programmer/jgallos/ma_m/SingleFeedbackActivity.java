package com.programmer.jgallos.ma_m;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SingleFeedbackActivity extends AppCompatActivity {

    private TextView levelTextView, statusTextView, feedbackTextView;
    private Button replyButton, resolvedButton;
    private RecyclerView recyclerView;

    private DatabaseReference mDatabase;
    private DatabaseReference escalateDatabaseRef;
    private DatabaseReference escalateMarkerRef;

    String subjectBuffer = null;
    String post_key = null;
    String uniLevel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleFeedbackActivity.this, NotifyActivity.class);
                startActivity(intent);
            }
        });

        levelTextView = (TextView)findViewById(R.id.slevelTextView);
        statusTextView = (TextView)findViewById(R.id.sstatusTextView);
        feedbackTextView = (TextView)findViewById(R.id.sfeedbackTextView);
        replyButton = (Button)findViewById(R.id.sreplyButton);
        resolvedButton = (Button)findViewById(R.id.sresolvedButton);


        subjectBuffer = getIntent().getExtras().getString("subjectReviewed");

        mDatabase = FirebaseDatabase.getInstance().getReference().child(subjectBuffer + "_feedback");
        post_key = getIntent().getExtras().getString("feedbackID");
        escalateDatabaseRef = FirebaseDatabase.getInstance().getReference().child(subjectBuffer + "_feedback").child(post_key);
        escalateMarkerRef = FirebaseDatabase.getInstance().getReference().child("Reply_" + post_key);

        //Toast.makeText(this,mDatabase.child(post_key).toString(),Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewSingleFeed);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        mDatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String feedback_level = (String) dataSnapshot.child("level").getValue();
                String feedback_desc = (String) dataSnapshot.child("desc").getValue();
                String feedback_status = (String) dataSnapshot.child("status").getValue();

                uniLevel = feedback_level;

                levelTextView.setText("Level: " + feedback_level);
                statusTextView.setText("Status: " + feedback_status);
                feedbackTextView.setText("Feedback: " + feedback_desc);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ReplyRecord, SFBViewHolder> FBRA = new FirebaseRecyclerAdapter<ReplyRecord, SingleFeedbackActivity.SFBViewHolder>(
                ReplyRecord.class,
                R.layout.reply_list,
                SingleFeedbackActivity.SFBViewHolder.class,
                escalateMarkerRef
        ) {
            @Override
            protected void populateViewHolder(SingleFeedbackActivity.SFBViewHolder viewHolder, ReplyRecord model, int position) {


                final String reply_key = getRef(position).getKey().toString();

                viewHolder.setDate(model.getDate());
                viewHolder.setTime(model.getTime());
                viewHolder.setReply(model.getReply());


                //Toast.makeText(ViewAttendanceActivity.this, model.getSignout().toString(), Toast.LENGTH_LONG).show();
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        };
        recyclerView.setAdapter(FBRA);
    }

    public static class SFBViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public SFBViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setDate(String date) {
            TextView response_date = mView.findViewById(R.id.textViewResponseDate);
            response_date.setText("Date: " + date);
        }

        public void setTime(String time) {
            TextView response_time = mView.findViewById(R.id.textViewResponseTime);
            response_time.setText("Time: " + time);
        }

        public void setReply(String reply) {
            TextView response_reply = mView.findViewById(R.id.textViewResponse);
            response_reply.setText(reply);
        }

    }

}
