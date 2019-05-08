package com.programmer.jgallos.ma_m;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("MA-S");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (mAuth.getCurrentUser()==null){
                    //Toast.makeText(getApplicationContext(), "not authenticated", Toast.LENGTH_LONG).show();

                    Intent loginIntent = new Intent(MainActivity.this, RegisterActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                    finish();
                } else {


                }
            }
        };



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */
                //Toast.makeText(MainActivity.this,"dgdg", Toast.LENGTH_SHORT).show();

                /* Notification.Builder mBuilder =
                        new Notification.Builder(MainActivity.this);

                //Create the intent that’ll fire when the user taps the notification//

                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.androidauthority.com/"));
                Intent intent = new Intent(MainActivity.this, AvailClassesActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                mBuilder.setContentIntent(pendingIntent);

                mBuilder.setSmallIcon(R.drawable.login);
                mBuilder.setContentTitle("Feedback Alert");
                mBuilder.setContentText("DEFCON 1 has been raised by an MA-S system!");


                NotificationManager mNotificationManager =

                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                mNotificationManager.notify(001, mBuilder.getNotification()); */


             /*   NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

                    // Configure the notification channel.
                    notificationChannel.setDescription("Channel description");
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                }


                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this, NOTIFICATION_CHANNEL_ID);

                notificationBuilder.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.login)
                        .setTicker("Hearty365")
                       // .setPriority(Notification.IMPORTANCE_HIGH)
                        .setContentTitle("Default notification")
                        .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                        .setContentInfo("Info");
                */
                //notificationManager.notify(/*notification id*/1, notificationBuilder.build());

            Intent intent = new Intent(MainActivity.this, NotifyActivity.class);
            startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_logout) {
            mAuth.signOut();
            Intent logoutIntent = new Intent(MainActivity.this, RegisterActivity.class);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logoutIntent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClickSignin(View view) {
        Intent i = new Intent(this, AvailClassesActivity.class);
        startActivity(i);
        finish();

    }


}
