package com.chr.handin2_11665.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText mEditTextMsg;
    EditText mEditTextSec;
    Alarm mAlarm;
    private ProgressBar mProgressBar;
    private static final String LOG_INFO = "MainActivity.info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mEditText = (EditText) findViewById(R.id.editText);
        mAlarm = new Alarm();
        mEditTextMsg = (EditText) findViewById(R.id.editTextMsg);
        mEditTextSec = (EditText) findViewById(R.id.editTextSec);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        Log.i(LOG_INFO, "OnCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startService(View view) {
        Context context = this.getApplicationContext();
        String s = mEditTextSec.getText().toString();

        if (s.length() > 0) {
            int sec = Integer.parseInt(s);
            String msg = mEditTextMsg.getText().toString();

            Intent intent = new Intent(this, AlarmService.class);
            intent.putExtra(AlarmService.EXTRA_SECONDS, sec);
            intent.putExtra(AlarmService.EXTRA_MSG, msg);

            Toast.makeText(context, "Alarm will fire in " + sec + " seconds", Toast.LENGTH_SHORT).show();

            mProgressBar.setMax(sec - 1);
            context.startService(intent);
        } else {
            Toast.makeText(context, "Remember to set time!", Toast.LENGTH_SHORT).show();
        }
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mProgressBar.setProgress(intent.getIntExtra(AlarmService.EXTRA_SECONDS, 0));
        }
    };

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
        registerReceiver(broadcastReceiver, new IntentFilter(AlarmService.BROADCAST_TIME));
    }

    protected void onPause() {
        super.onPause();
        Log.i(LOG_INFO, "onPause");
        unregisterReceiver(broadcastReceiver);
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
