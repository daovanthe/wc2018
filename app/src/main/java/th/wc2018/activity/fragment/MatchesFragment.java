package th.wc2018.activity.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;

import data.obtain.LoadData;
import data.raw.Fixtures;
import th.wc2018.R;
import th.wc2018.adapter.MatchAdapter;
import th.wc2018.api.OnLoadApiCompletedListener;
import th.wc2018.api.apiImp.FixturesAPI;
import th.wc2018.broadcast.MAction;

public class MatchesFragment extends CommonFragment implements SwipeRefreshLayout.OnRefreshListener {

    private android.support.v4.widget.SwipeRefreshLayout mSwipeContent;
    private boolean isLoaded = false;
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches_layout, container, false);
        mSwipeContent = view.findViewById(R.id.match_refresh_content);
        mSwipeContent.setOnRefreshListener(this);

        Log.d("THE_DV", "MatchesActivity on created () ;");
        allMatchesListView = view.findViewById(R.id.all_matches);
        matchesData = new ArrayList<>();

        matchesAdapter = new MatchAdapter(getActivity(), matchesData);
        allMatchesListView.setAdapter(matchesAdapter);
        Log.e("THE_DV", "on Create View Matches Fragment ...");

        if (!isLoaded) {
            mSwipeContent.setRefreshing(true);
            new MatchesFromSQLTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        Log.e("THE_DV", "pass task.execute()... isCannel: ");


        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onRefresh() {
        Log.e("THE_DV", "on refresh");
        new Thread() {
            @Override
            public void run() {
                Log.e("THE_DV", "on refresh");
                FixturesAPI fixturesApi = new FixturesAPI();
                fixturesApi.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
                    @Override
                    public void loadApiCompleted(Object... result) {
                        Fixtures[] fixturesData = (Fixtures[]) result;
                        LoadData mLoadData = new LoadData(getActivity(), "wcdata");
                        if (mLoadData != null) {
                            Log.e("THE_DV", "on load data OK");
                            mLoadData.getFixturesDao().insert(fixturesData);
                            Intent intent = new Intent();
                            intent.setAction(MAction.FIXTURES_DATABASE_CHANGE);
                            Log.e("THE_DV", "on send data OK: FIXTURES_DATABASE_CHANGE");
                            getActivity().sendBroadcast(intent);
                        }
                    }
                });
                fixturesApi.loadObjectFromIntenet();
            }
        }.start();
    }

    class MatchesFromSQLTask extends AsyncTask<Void, Void, List<Object>> {

        @Override
        protected void onPreExecute() {

            isLoaded = true;
        }

        @Override
        protected List<Object> doInBackground(Void... allmatchesInfos) {
            Log.e("THE_DV", "Executing asyntask of Matches collection");
            List<Object> allmatchesInfo = new ArrayList<>();
            allmatchesInfo.removeAll(allmatchesInfo);
            LoadData loadData = null;
            try {
                loadData = new LoadData(getActivity(), "wcdata");
            } catch (IllegalArgumentException w) {
                w.printStackTrace();
            }
            if (loadData != null) {
                List<String> days = loadData.getFixturesDao().getDay();
                for (String day : days) {
                    allmatchesInfo.add(day.toString());
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
                        onProgressUpdate();
                    }
                    if (!hasMatch) {
                        allmatchesInfo.remove(day);
                    }
                }
                loadData.closeConnect();
            }
            return allmatchesInfo;
        }

        @Override
        protected void onPostExecute(List<Object> list) {
            isLoaded = false;

            if (list.size() != 0) {
                matchesData.removeAll(matchesData);
                for (Object o : list) {
                    matchesData.add(o);
                }
                matchesAdapter.notifyDataSetChanged();

                if (mSwipeContent != null)
                    mSwipeContent.setRefreshing(false);
            } else {
                Log.e("THE_DV", "refresh if list is 0");
                ((SwipeRefreshLayout.OnRefreshListener) MatchesFragment.this).onRefresh();
                if (mSwipeContent != null)
                    mSwipeContent.setRefreshing(true);
            }
        }

    }


    public void refreshData() {
        if (!isLoaded) {
            MatchesFromSQLTask task = new MatchesFromSQLTask();
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }


    private ListView allMatchesListView;
    private List<Object> matchesData;
    private ConcurrentLinkedQueue data;
    private MatchAdapter matchesAdapter;


    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilterf = new IntentFilter();
        intentFilterf.addAction(MAction.FIXTURES_DATABASE_CHANGE);
        getActivity().registerReceiver(mDataBaseChangeListener, intentFilterf);


    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mDataBaseChangeListener);
    }

    private DataBaseChangeListener mDataBaseChangeListener = new DataBaseChangeListener();

    class DataBaseChangeListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MAction.FIXTURES_DATABASE_CHANGE)) {
                Log.e("THE_DV", "Broadcast Receiver matches OK");
                refreshData();
            }
        }
    }


}
