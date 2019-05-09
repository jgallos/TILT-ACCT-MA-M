package com.programmer.jgallos.ma_m;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AcadActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    String subjectBuffer = null;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcadActivity.this, NotifyActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewAcad);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        subjectBuffer = getIntent().getExtras().getString("subjectReviewed");

        mDatabase = FirebaseDatabase.getInstance().getReference().child(subjectBuffer + "_storage");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<AcadRecord, AcadViewHolder> FBRA = new FirebaseRecyclerAdapter<AcadRecord, AcadViewHolder>(
                AcadRecord.class,
                R.layout.acad_list,
                AcadViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(AcadViewHolder viewHolder, AcadRecord model, int position) {

                final String acad_key = getRef(position).getKey().toString();

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setUsername(model.getUsername());
                viewHolder.setDate(model.getDate());
                viewHolder.setImageUrl(getApplicationContext(), model.getImageUrl());
                //Toast.makeText(ViewAcadActivity.this, model.getImageUrl().toString(), Toast.LENGTH_LONG).show();
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


            }
        };
        recyclerView.setAdapter(FBRA);
    }

    public static class AcadViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public AcadViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setTitle(String title) {
            TextView acad_title = mView.findViewById(R.id.acadTitle);
            acad_title.setText("Title: " + title);
        }

        public void setDesc(String desc) {
            TextView acad_desc = mView.findViewById(R.id.acadDesc);
            acad_desc.setText("Description: " + desc);
        }

        public void setUsername(String username) {
            TextView acad_username = mView.findViewById(R.id.acadName);
            acad_username.setText("Uploader: " + username);
        }

        public void setDate(String date) {
            TextView acad_date = mView.findViewById(R.id.acadDate);
            acad_date.setText("Date uploaded: " + date);
        }

        public void setImageUrl(Context ctx, String imageUrl) {
            ImageView acad_image = mView.findViewById(R.id.acadImage);
            Picasso.with(ctx).load(imageUrl).into(acad_image);

        }




    }

}
