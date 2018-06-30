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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import data.obtain.LoadData;
import data.raw.History;
import th.wc2018.R;
import th.wc2018.adapter.HistoryScoreAdapter;
import th.wc2018.api.OnLoadApiCompletedListener;
import th.wc2018.api.apiImp.HistoryAPI;
import th.wc2018.broadcast.MAction;

public class HistoryScoreFragment extends CommonFragment implements SwipeRefreshLayout.OnRefreshListener {
    private HistoryScoreAdapter historyScoreAdapter;
    private List<Object> listHistoryScore = new ArrayList<>();
    private SwipeRefreshLayout swipe_content;
    private boolean isLoaded = false;

    private AdView mAdView;

    @Override
    public void onResume() {
        super.onResume();
        if (!isLoaded) {
            LoadDataFromSQLTask task = new LoadDataFromSQLTask();
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_layout, container, false);
        ListView listScoreView = (ListView) view.findViewById(R.id.list_history_score);
        swipe_content = (SwipeRefreshLayout) view.findViewById(R.id.swipe_content);
        swipe_content.setOnRefreshListener(this);
        //
//        listScoreView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                mGestureDetector.onTouchEvent(motionEvent);
//                return false;
//            }
//        });

        historyScoreAdapter = new HistoryScoreAdapter(getActivity(), 0, listHistoryScore);
        listScoreView.setAdapter(historyScoreAdapter);
        if (!isLoaded) {
            swipe_content.setRefreshing(true);
            LoadDataFromSQLTask task = new LoadDataFromSQLTask();
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MAction.REQUEST_DATABASE_SCORE_CHANGE);
//        getActivity().registerReceiver(mDataBaseChangeListener, intentFilter);
        // refresh elements

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        return view;
    }

    void refresh() {
        if (!isLoaded) {
            LoadDataFromSQLTask task = new LoadDataFromSQLTask();
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onRefresh() {
        new Thread() {
            public void run() {
                HistoryAPI historyAPI = new HistoryAPI();
                historyAPI.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
                    @Override
                    public void loadApiCompleted(Object... result) {
                        History[] liveScoreDatas = (History[]) result;
                        if (liveScoreDatas.length == 0) return;
                        LoadData mLoadData = new LoadData(getActivity(), "wcdata");
                        if (mLoadData != null) {
                            mLoadData.getHistoryScoreDao().insert(liveScoreDatas);
                            Log.e("THE_DV", "mLoadData History OK ");
                            Intent intent = new Intent();
                            intent.setAction(MAction.REQUEST_DATABASE_HISTORY_CHANGE);
                            getActivity().sendBroadcast(intent);
                        }
                    }
                });
                historyAPI.loadObjectFromIntenet();
            }
        }.start();
    }


    class LoadDataFromSQLTask extends AsyncTask<Void, Object, List<Object>> {

        @Override
        protected void onPreExecute() {
            swipe_content.setRefreshing(true);
            isLoaded = true;
        }

        @Override
        protected List<Object> doInBackground(Void... v) {
            Log.e("THE_DV", "Executing asyntask of History collection");
            List<Object> allLiveScoreMatches = new ArrayList<>();
//            if (allLiveScoreMatches.size() != 0) {
            allLiveScoreMatches.removeAll(allLiveScoreMatches);
            LoadData loadData = null;
            try {
                loadData = new LoadData(getActivity(), "wcdata");
            } catch (IllegalArgumentException w) {
                w.printStackTrace();
            }
            if (loadData != null) {
                List<History> listMatchesPerDay = loadData.getHistoryScoreDao().getScore();
                for (History singleMatch : listMatchesPerDay) {
                    String statusMatch = singleMatch.getStatus();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
                    if (
//                            statusMatch.trim().equals("") ||
                            statusMatch.trim().equals("INSUFFICIENT DATA")) {
                        continue;
                    }//
                    allLiveScoreMatches.add(singleMatch);
                }
                loadData.closeConnect();
            }
//            }
            return allLiveScoreMatches;
        }

        @Override
        protected void onPostExecute(List<Object> list) {
            isLoaded = false;
            if (list.size() != 0) {
                listHistoryScore.removeAll(listHistoryScore);
                for (Object o : list) {
                    listHistoryScore.add(o);
                }
                if (swipe_content != null) {
                    swipe_content.setRefreshing(false);
                }
            } else {
                Log.e("THE_DV", "refresh if list is 0");
                ((SwipeRefreshLayout.OnRefreshListener) HistoryScoreFragment.this).onRefresh();
                if (swipe_content != null)
                    swipe_content.setRefreshing(true);
            }
            historyScoreAdapter.notifyDataSetChanged();
        }
    }

    public interface IActivityCallBack {
        void callBack();
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilterf = new IntentFilter();
        intentFilterf.addAction(MAction.REQUEST_DATABASE_HISTORY_CHANGE);
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
            if (intent.getAction().equals(MAction.REQUEST_DATABASE_HISTORY_CHANGE))
                getActivity().runOnUiThread(() -> {
                    refresh();
                });
        }
    }
}
