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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    String subjectBuffer = null;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedbackActivity.this, NotifyActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewFeedback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        subjectBuffer = getIntent().getExtras().getString("subjectReviewed");
        mDatabase = FirebaseDatabase.getInstance().getReference().child(subjectBuffer + "_feedback");





    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<FeedbackRecord, FeedbackActivity.FeedbackViewHolder> FBRA = new FirebaseRecyclerAdapter<FeedbackRecord, FeedbackViewHolder>(
                FeedbackRecord.class,
                R.layout.feedback_list,
                FeedbackActivity.FeedbackViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(FeedbackActivity.FeedbackViewHolder viewHolder, FeedbackRecord model, int position) {
                final String feedback_key = getRef(position).getKey().toString();

                viewHolder.setDesc(model.getDesc());
                viewHolder.setLevel(model.getLevel());
                viewHolder.setStatus(model.getStatus());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleFeedback = new Intent(FeedbackActivity.this, SingleFeedbackActivity.class);
                        singleFeedback.putExtra("feedbackID", feedback_key);
                        singleFeedback.putExtra("subjectReviewed", subjectBuffer);
                        startActivity(singleFeedback);
                    }
                });
            }
        };
        recyclerView.setAdapter(FBRA);
    }

    public static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public FeedbackViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setDesc(String desc) {
            TextView feedback_desc = mView.findViewById(R.id.feedbackTextView);
            feedback_desc.setText("Feedback: " + desc);
        }

        public void setLevel(String level) {
            TextView feedback_level = mView.findViewById(R.id.levelTextView);
            feedback_level.setText("Level: " + level);
        }

        public void setStatus(String status) {
            TextView feedback_status = mView.findViewById(R.id.statusTextView);
            feedback_status.setText("Status: " + status);
        }


    }


}
