package th.wc2018.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import data.obtain.LoadData;
import data.raw.Fixtures;
import th.wc2018.R;
import th.wc2018.WcService;
import th.wc2018.adapter.MatchAdapter;

public class MatchesActivity extends Activity {

    private ListView allMatches;
    private List<Object> matchesData;
    private MatchAdapter matchesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_matches_layout);
        Log.d("THE_DV", "MatchesActivity on created () ;");
        allMatches = (ListView) findViewById(R.id.all_matches);
        matchesData = new ArrayList<>();
        matchesAdapter = new MatchAdapter(getBaseContext(), R.layout.date_match_layout, matchesData);
        allMatches.setAdapter(matchesAdapter);
        new LoadDataFromSQLTask().execute(matchesData);
    }


    class LoadDataFromSQLTask extends AsyncTask<List<Object>, Void, Void> {
        @Override
        protected Void doInBackground(List<Object>... allmatchesInfos) {
            List<Object> allmatchesInfo = allmatchesInfos[0];
            LoadData loadData = new LoadData(MatchesActivity.this, "wcdata");
            List<String> days = loadData.getFixturesDao().getDay();
            for (String day : days) {
                allmatchesInfo.add(day);
                List<Fixtures> listMatchesPerDay = loadData.getFixturesDao().getMatchByDay(day);
                boolean hasMatch = false;
                for (Fixtures singleMatch : listMatchesPerDay) {
                    String time = singleMatch.getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
                    Date date1 = null;
                    try {
                        date1 = dateFormat.parse(day + " " + time);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Date currentTime = Calendar.getInstance().getTime();
                    if (date1.after(currentTime)) {
                        allmatchesInfo.add(singleMatch);
                        hasMatch = true;
                    }
                }
                if (!hasMatch) {
                    allmatchesInfo.remove(day);
                }
            }
            loadData.closeConnect();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            matchesAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent serviceIntent = new Intent();
        serviceIntent.setPackage("th.wc2018");
        serviceIntent.setClass(this, WcService.class);
//        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
        //startService(serviceIntent);

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
            LoadData loadData = new LoadData(MatchesActivity.this, "wcdata");
            mservice.startLoadData(loadData);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };

}
