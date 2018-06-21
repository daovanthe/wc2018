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

import java.util.ArrayList;
import java.util.List;

import data.obtain.LoadData;
import data.raw.History;
import data.raw.LiveScore;
import th.wc2018.R;
import th.wc2018.adapter.LiveScoreAdapter;
import th.wc2018.api.OnLoadApiCompletedListener;
import th.wc2018.api.apiImp.LiveScoreApi;
import th.wc2018.broadcast.MAction;

public class LiveScoreFragment extends CommonFragment implements SwipeRefreshLayout.OnRefreshListener {
    private LiveScoreAdapter liveScoreAdapter;
    private List<Object> listLiveScoreData = new ArrayList<>();
    private LiveScoreAdapter matchesAdapter;
    private SwipeRefreshLayout swipe_content;
    private ListView listScoreView;

    private boolean isLoaded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score_layout, container, false);
        listScoreView = (ListView) view.findViewById(R.id.list_live_score);
        swipe_content = (SwipeRefreshLayout) view.findViewById(R.id.swipe_content);
        swipe_content.setOnRefreshListener(this);
        liveScoreAdapter = new LiveScoreAdapter(getActivity(), 0, listLiveScoreData);
        listScoreView.setAdapter(liveScoreAdapter);
        // start thread to get live score
        Log.e("THE_DV", "is loaded: " + isLoaded);

        if (!isLoaded) {
            swipe_content.setRefreshing(true);
            LoadDataFromSQLTask task = new LoadDataFromSQLTask();
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MAction.REQUEST_DATABASE_SCORE_CHANGE);
        return view;
    }

    private void refresh() {
        if (!isLoaded) {
            Log.e("THE_DV", "refreshing to load data .... (started Asyntask)");
            LoadDataFromSQLTask task = new LoadDataFromSQLTask();
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    class LoadDataFromSQLTask extends AsyncTask<Void, LiveScore, List<Object>> {

        @Override
        protected void onPreExecute() {
            Log.e("THE_DV", "loaded = true");
            isLoaded = true;
        }

        @Override
        protected List<Object> doInBackground(Void... allmatchesInfos) {
            List<Object> localListLiveScoreData = new ArrayList<>();

            LiveScoreApi liveScoreApi = new LiveScoreApi();
            liveScoreApi.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
                @Override
                public void loadApiCompleted(Object... result) {
                    Log.e("THE_DV", "load live done");
                    LiveScore[] liveScoreDatas = (LiveScore[]) result;
                    for (LiveScore liveScore : liveScoreDatas) {
                        localListLiveScoreData.add(liveScore);
                        publishProgress(liveScore);
                    }

                }
            });
            liveScoreApi.loadObjectFromIntenet();


            LoadData loadData = null;
            try {
                loadData = new LoadData(getActivity(), "wcdata");
            } catch (IllegalArgumentException w) {
                w.printStackTrace();
            }
            for (Object liveScore : localListLiveScoreData) {
                LiveScore liveScore1 = (LiveScore) liveScore;
                if (liveScore1.getStatus().toLowerCase().equals("finished")) {
                    loadData.getHistoryScoreDao().insert(History.makeHistory(liveScore1));
                }
            }

            return localListLiveScoreData;
        }

        @Override
        protected void onProgressUpdate(LiveScore... l) {

            LiveScore li = l[0];
            if (li == null) {
                Log.e("THE_DV", "Live SCORE EROOR on PUBLIC WHEN LOAD FROM INTERNET");
                return;
            }

            boolean isContain = false;
            for (Object live : listLiveScoreData) {
                if (live instanceof LiveScore) {
                    LiveScore ls = (LiveScore) live;
                    if (ls.getKey() == li.getKey()) {
                        listLiveScoreData.remove(ls);
                        listLiveScoreData.add(li);
                        isContain = true;
                        break;
                    }
                }
            }
            if (!isContain) {
                listLiveScoreData.add(li);
            }
            liveScoreAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(List<Object> list) {
            Log.e("THE_DV", "loaded = false");
            isLoaded = false;
//            listLiveScoreData.removeAll(listLiveScoreData);
//            for (Object o : list) {
//                listLiveScoreData.add(o);
//            }
//            liveScoreAdapter.notifyDataSetChanged();

            if (swipe_content != null)
                swipe_content.setRefreshing(false);
        }
    }

    public interface ILoadEmptyFragment {
        public void loadEmptyFragment(boolean isEmpty);
    }


    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilterf = new IntentFilter();
        intentFilterf.addAction(MAction.REQUEST_DATABASE_SCORE_CHANGE);
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
            if (intent.getAction().equals(MAction.REQUEST_DATABASE_SCORE_CHANGE))
                Log.e("THE_DV", "Broadcast Receiver Score OK");
            getActivity().runOnUiThread(() -> {
                refresh();
            });
        }
    }


}
