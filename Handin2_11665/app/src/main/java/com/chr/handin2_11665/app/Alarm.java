package com.chr.handin2_11665.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Christian Svejstrup on 25-04-2014.
 */
public class Alarm extends BroadcastReceiver {

    final public static String MSG = "msg";
    final private static String WAKE_LOCK_TAG = "com.chr.handin2_11665_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, WAKE_LOCK_TAG);

        wl.acquire();

        Intent activityIntent = new Intent();
        activityIntent.setClassName("com.chr.handin2_11665.app", "com.chr.handin2_11665.app.AlarmActivity");
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityIntent.putExtra(MSG, intent.getStringExtra(MSG));
        context.startActivity(activityIntent);

        wl.release();
    }

    public void setOnetimeTimer(Context context, int seconds, String msg){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, Alarm.class);
        intent.putExtra(MSG, msg);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + seconds*1000, pendingIntent);
    }
}
