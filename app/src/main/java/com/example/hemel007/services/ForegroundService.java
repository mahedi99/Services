package com.example.hemel007.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hemel99 on 9/18/2016.
 */
public class ForegroundService extends Service{

    public static boolean IS_SERVICE_RUNNING = false;
    private static final String LOG_TAG = "ForegroundService";

    private Handler handler;
    private Runnable runnable;


    @Override
    public void onCreate() {
        super.onCreate();

        /*Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplication(),"Running", Toast.LENGTH_LONG).show();
            }
        });*/
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                Toast.makeText(getApplication(),"Running", Toast.LENGTH_LONG).show();

                handler.postDelayed(this, 5000);
            }
        };
        runnable.run();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION))
        {
            Log.i(LOG_TAG, "Received Start Foreground Intent ");
            showNotification();
            Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show();
        }
        else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION))
        {
            Log.i(LOG_TAG, "Clicked Previous");
            Toast.makeText(this, "Clicked Previous!", Toast.LENGTH_SHORT).show();
        }
        else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION))
        {
            Log.i(LOG_TAG, "Clicked Play");
            Toast.makeText(this, "Clicked Play!", Toast.LENGTH_SHORT).show();
        }
        else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION))
        {
            Log.i(LOG_TAG, "Clicked Next");
            Toast.makeText(this, "Clicked Next!", Toast.LENGTH_SHORT).show();
        }
        else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION))
        {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;

        //return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent previousIntent = new Intent(this, ForegroundService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, ForegroundService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        Intent nextIntent = new Intent(this, ForegroundService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Music Player")
                .setTicker("My Music Player")
                .setContentText("My song")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_media_previous, "Previous",
                        ppreviousIntent)
                .addAction(android.R.drawable.ic_media_play, "Play",
                        pplayIntent)
                .addAction(android.R.drawable.ic_media_next, "Next",
                        pnextIntent).build();
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
        Toast.makeText(this, "Service Destroyed!", Toast.LENGTH_SHORT).show();

        if (handler!=null)
        {
            handler.removeCallbacks(runnable);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
