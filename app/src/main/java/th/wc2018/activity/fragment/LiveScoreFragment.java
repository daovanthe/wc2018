package th.wc2018.activity.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
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
import java.util.concurrent.ExecutionException;

import data.obtain.LoadData;
import data.raw.Events;
import data.raw.LiveScore;
import th.wc2018.R;
import th.wc2018.adapter.LiveScoreAdapter;
import th.wc2018.adapter.LiveScoreEventAdapter;
import th.wc2018.broadcast.MAction;
import th.wc2018.ulity.DateUnity;

public class LiveScoreFragment extends CommonFragment implements SwipeRefreshLayout.OnRefreshListener {
    private LiveScoreAdapter liveScoreAdapter;
    private List<Object> listLiveScore = new ArrayList<>();
    private LiveScoreAdapter matchesAdapter;
    private SwipeRefreshLayout swipe_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score_layout, container, false);

        ListView listScoreView = (ListView) view.findViewById(R.id.list_live_score);
        swipe_content = (SwipeRefreshLayout) view.findViewById(R.id.swipe_content);
        swipe_content.setOnRefreshListener(this);
        //
        listScoreView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

        liveScoreAdapter = new LiveScoreAdapter(getActivity(), 0, listLiveScore);
        listScoreView.setAdapter(liveScoreAdapter);

        LoadDataFromSQLTask task = new LoadDataFromSQLTask();
        task.execute(listLiveScore);


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MAction.REQUEST_DATABASE_CHANGE);
//        getActivity().registerReceiver(mDataBaseChangeListener, intentFilter);
        // refresh elements

        return view;
    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadDataFromSQLTask task = new LoadDataFromSQLTask();
                task.execute(listLiveScore);
                swipe_content.setRefreshing(false);
            }
        }, 5000);
    }


    class LoadDataFromSQLTask extends AsyncTask<List<Object>, Object, List<Object>> {
        @Override
        protected List<Object> doInBackground(List<Object>... allmatchesInfos) {
            List<Object> allLiveScoreMatches = allmatchesInfos[0];// new ArrayList<>();
            allLiveScoreMatches.removeAll(allLiveScoreMatches);

            LoadData loadData = null;

            try {
                loadData = new LoadData(getActivity(), "wcdata");
            } catch (IllegalArgumentException w) {
                w.printStackTrace();
            }

            if (loadData != null) {
                List<LiveScore> listMatchesPerDay = loadData.getLiveScoreDao().getScore();

                for (LiveScore singleMatch : listMatchesPerDay) {

                    String statusMatch = singleMatch.getStatus();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));

                    if (statusMatch.trim().equals("FINISHED") || statusMatch.trim().equals("INSUFFICIENT DATA")) {
                        continue;
                    }//

                    List<Events> events = loadData.getEventLiveScoreDao().getEventBy(singleMatch.getLeague_id(), singleMatch.getId());
                    for (Events e : events) {
                        singleMatch.addEvent(e);
                    }
                    onProgressUpdate(singleMatch);
                    allLiveScoreMatches.add(singleMatch);
                }
                loadData.closeConnect();
            }
            return allLiveScoreMatches;
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
            getActivity().runOnUiThread(() -> {
                liveScoreAdapter.notifyDataSetChanged();

            });

            LiveScore liveScore = (LiveScore) values[0];
            LiveScoreEventAdapter adapter = (LiveScoreEventAdapter) liveScore.getTag();
            if (adapter != null)
                adapter.notifyDataSetChanged();


        }

        @Override
        protected void onPostExecute(List<Object> list) {
            super.onPostExecute(list);
        }
    }

    private DataBaseChangeListener mDataBaseChangeListener = new DataBaseChangeListener();

    class DataBaseChangeListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals(MAction.REQUEST_DATABASE_CHANGE))
//                getActivity().runOnUiThread(() -> {
//                    // refreshData();
//                });
        }
    }

}
