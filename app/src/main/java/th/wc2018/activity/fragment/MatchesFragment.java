package th.wc2018.activity.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;

import data.obtain.LoadData;
import data.raw.Fixtures;
import th.wc2018.R;
import th.wc2018.adapter.MatchAdapter;
import th.wc2018.broadcast.MAction;

public class MatchesFragment extends CommonFragment implements SwipeRefreshLayout.OnRefreshListener {

    private android.support.v4.widget.SwipeRefreshLayout mSwipeContent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches_layout, container, false);
        mSwipeContent = (SwipeRefreshLayout) view.findViewById(R.id.match_refresh_content);
        mSwipeContent.setOnRefreshListener(this);

        Log.d("THE_DV", "MatchesActivity on created () ;");

        allMatchesListView = (ListView) view.findViewById(R.id.all_matches);


        allMatchesListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

        matchesData = new ArrayList<>();
        LoadDataFromSQLTask task = new LoadDataFromSQLTask();
        task.execute(matchesData);
//
//        try {
//            matchesData = task.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        matchesAdapter = new MatchAdapter(getActivity(), matchesData);
        allMatchesListView.setAdapter(matchesAdapter);

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(MAction.FIXTURES_DATABASE_CHANGE);
//        getActivity().registerReceiver(mDataBaseChangeListener, intentFilter);

        return view;
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshData();
                mSwipeContent.setRefreshing(false);
            }
        }, 5000);
    }

    private ListView allMatchesListView;
    private List<Object> matchesData;
    private ConcurrentLinkedQueue data;
    private MatchAdapter matchesAdapter;

    public void refreshData() {
        LoadDataFromSQLTask task = new LoadDataFromSQLTask();
        task.execute(matchesData);
        try {
            List<Object> objects = task.get();

//            synchronized (matchesData) {
//                Iterator<Object> iter = matchesData.iterator();
//                while (iter.hasNext()) {
//                    Object o = iter.next();
//                    matchesData.remove(o);
//                }
//                for (Object o : objects) {
//                    matchesData.add(o);
//                }
            matchesAdapter.notifyDataSetChanged();
//            }

        } catch (
                InterruptedException e) {
            e.printStackTrace();
        } catch (
                ExecutionException e)

        {
            e.printStackTrace();
        }
    }


    class LoadDataFromSQLTask extends AsyncTask<List<Object>, Void, List<Object>> {
        @Override
        protected List<Object> doInBackground(List<Object>... allmatchesInfos) {
            List<Object> allmatchesInfo = allmatchesInfos[0];// new ArrayList<>();
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
                    }
                    if (!hasMatch) {
                        allmatchesInfo.remove(day);
                    }
                }
                onProgressUpdate();
                loadData.closeConnect();
            }
            return allmatchesInfo;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Activity activity = getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        matchesAdapter.notifyDataSetChanged();
                    }
                });
            }
        }

        @Override
        protected void onPostExecute(List<Object> list) {
            super.onPostExecute(list);
            Activity activity = getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        matchesAdapter.notifyDataSetChanged();
                    }
                });
            }
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        //startService(serviceIntent);

    }


    @Override
    public void onStop() {
        super.onStop();
        Log.e("THE_DV", "MatchesFragment.class -> onStop() -> unregisterReceiver(mDataBaseChangeListener);");
//        getActivity().unregisterReceiver(mDataBaseChangeListener);
    }

    private DataBaseChangeListener mDataBaseChangeListener = new DataBaseChangeListener();

    class DataBaseChangeListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals(MAction.FIXTURES_DATABASE_CHANGE))
//                getActivity().runOnUiThread(() -> {
//                    refreshData();
//                });
        }
    }
}
