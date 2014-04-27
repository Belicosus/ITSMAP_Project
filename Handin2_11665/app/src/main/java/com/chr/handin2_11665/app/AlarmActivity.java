package com.chr.handin2_11665.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

public class AlarmActivity extends Activity {
    private static final String LOG_INFO = "AlarmActivity.info";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_view);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = (TextView) findViewById(R.id.textViewB);
        Intent intent = getIntent();
        String msg = intent.getStringExtra(Alarm.MSG);

        if (msg != null) {
            textView.setText(msg);
        }
    }

    protected void onStart() {
        super.onStart();
        Log.i(LOG_INFO, "OnStart");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_INFO, "onRestart");
    }

    protected void onResume() {
        super.onResume();
        Log.i(LOG_INFO, "onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.i(LOG_INFO, "onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.i(LOG_INFO, "onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_INFO, "onDestroy");
    }
}
