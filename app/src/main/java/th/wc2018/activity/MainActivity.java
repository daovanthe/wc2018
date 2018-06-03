package th.wc2018.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import data.obtain.LoadData;
import th.wc2018.R;
import th.wc2018.WcService;


public class MainActivity extends Activity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_matches_layout);

        Intent intent = new Intent(this, MatchesActivity.class);
        startActivity(intent);

    }




    public void refresh() {

    }

    //region SERVICE_CONNECT

    @Override
    protected void onStart() {
        super.onStart();
        Intent serviceIntent = new Intent();
        serviceIntent.setPackage("th.wc2018");
        serviceIntent.setClass(this, WcService.class);
       // bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
//        startService(serviceIntent);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }


    private WcService mservice;
    private boolean isBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d("THE_DV", "Connected Service loadData");
            WcService.LocalBinder binder = (WcService.LocalBinder) service;
            mservice = binder.getService();
            LoadData loadData = new LoadData(MainActivity.this, "wcdata");
            mservice.startLoadData(loadData);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };

    //endregion

}
