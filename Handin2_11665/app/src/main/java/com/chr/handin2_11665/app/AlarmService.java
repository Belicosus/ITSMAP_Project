package com.chr.handin2_11665.app;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.ProgressBar;
import android.widget.TextView;



public class AlarmService extends Service {
    public static final String EXTRA_SECONDS = "com.chr.handin2_11665.app.extra.SECONDS";
    public static final String EXTRA_MSG = "com.chr.handin2_11665.app.extra.MSG";
    public static final String BROADCAST_TIME = "com.chr.handin2_11665.app.action.TIME";

    private Alarm mAlarm = new Alarm();
    private Handler mHandler;

    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        final int sec = intent.getIntExtra(EXTRA_SECONDS, 0);
        String msg = intent.getStringExtra(EXTRA_MSG);
        mAlarm.setOnetimeTimer(this, sec, msg);

        mHandler = new Handler();

        new Thread(new Runnable() {
            int time = 0;
            @Override
            public void run() {
                while (time <= sec) {
                    try {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Intent broadcastIntent = new Intent(BROADCAST_TIME);
                                broadcastIntent.putExtra(EXTRA_SECONDS, time);
                                sendBroadcast(broadcastIntent);
                            }
                        });

                        Thread.sleep(1000);
                        time++;
                    } catch (Exception e) {
                        //Todo: handle exception
                    }
                }
            }
        }).start();

        return Service.START_STICKY;
    }
}
