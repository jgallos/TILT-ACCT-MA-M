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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.instacart.library.truetime.TrueTimeRx;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
                String timeHolder = null;

                stringNotif = notifContentEdit.getText().toString().trim();

                timeHolder = updateTime(1);
                newNotif.child("nnotif").setValue("Notification from Manager: " + stringNotif);
                newNotif.child("ndate").setValue(timeHolder);
                timeHolder = updateTime(2);
                newNotif.child("ntime").setValue(timeHolder).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(NotifyActivity.this,"Notification sent!", Toast.LENGTH_SHORT).show();
                            finish();

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


    private String updateTime(int flag) {
        TextView textTime = (TextView) findViewById(R.id.textViewNTime);

        if (!TrueTimeRx.isInitialized()) {
            Toast.makeText(NotifyActivity.this, "TrueTime is not yet initialized.", Toast.LENGTH_SHORT).show();
            return " ";
        }

        Date trueTime = TrueTimeRx.now();
        if (flag == 1) {
            textTime.setText(getString(R.string.tt_time_gmt, _formatDate(trueTime, "yyyy-MM-dd", TimeZone.getTimeZone("GMT+08:00"))));

        } else if (flag==2) {
            textTime.setText(getString(R.string.tt_time_gmt, _formatDate(trueTime, "HH:mm:ss", TimeZone.getTimeZone("GMT+08:00"))));
        }
        return textTime.getText().toString();
    }

    private String _formatDate(Date date, String pattern, TimeZone timeZone) {
        DateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        format.setTimeZone(timeZone);
        return format.format(date);
    }



}
