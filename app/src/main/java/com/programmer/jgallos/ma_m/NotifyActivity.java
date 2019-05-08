package com.programmer.jgallos.ma_m;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotifyActivity extends AppCompatActivity {

    private Button sendNotif;
    private EditText notifTitleEdit;
    private EditText notifContentEdit;
    private DatabaseReference mNotifDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sendNotif = (Button)findViewById(R.id.sendNotifButton);
        notifTitleEdit = (EditText)findViewById(R.id.notifTitleEditText);
        notifContentEdit = (EditText)findViewById(R.id.notifContentEditText);

        mNotifDatabase = FirebaseDatabase.getInstance().getReference().child("Notifications");

        sendNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference newNotif = mNotifDatabase.push();
                String stringNotif = null;

                stringNotif = notifContentEdit.getText().toString().trim();

                newNotif.child("nnotif").setValue("Notification from Manager: " + stringNotif);
                newNotif.child("ndate").setValue("x");
                newNotif.child("ntime").setValue("x").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {


                        }
                    }
                });
            }
        });



        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */
    }

}
