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
        LoadDataFromSQLTask task = new LoadDataFromSQLTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MAction.REQUEST_DATABASE_SCORE_CHANGE);
        return view;
    }

    private void refresh() {
        Log.e("THE_DV", "refreshing to load data .... (started Asyntask)");
        LoadDataFromSQLTask task = new LoadDataFromSQLTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public void onRefresh() {
        refresh();
    }

    class LoadDataFromSQLTask extends AsyncTask<Void, LiveScore, List<Object>> {
        @Override
        protected List<Object> doInBackground(Void... allmatchesInfos) {
            List<Object> listOBject = new ArrayList<>();
            LiveScoreApi liveScoreApi = new LiveScoreApi();
            liveScoreApi.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
                @Override
                public void loadApiCompleted(Object... result) {
                    LiveScore[] liveScoreDatas = (LiveScore[]) result;
                    for (LiveScore liveScore : liveScoreDatas)
                        listOBject.add(liveScore);
                }
            });
            liveScoreApi.loadObjectFromIntenet();
            return listOBject;
        }

        @Override
        protected void onPostExecute(List<Object> list) {
            if (list.size() != 0) {
                listLiveScoreData.removeAll(listLiveScoreData);
                for (Object o : list) {
                    listLiveScoreData.add(o);
                }
                liveScoreAdapter.notifyDataSetChanged();
                if (swipe_content != null)
                    swipe_content.setRefreshing(false);
            } else {
                Log.e("THE_DV", "refresh if list is 0");
                ((SwipeRefreshLayout.OnRefreshListener) LiveScoreFragment.this).onRefresh();
                if (swipe_content != null)
                    swipe_content.setRefreshing(true);
            }
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
