package th.wc2018.activity.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import data.obtain.LoadData;
import data.raw.GroupScore;
import th.wc2018.R;
import th.wc2018.adapter.GroupScoreAdapter;

public class GroupScoreFragment extends CommonFragment implements SwipeRefreshLayout.OnRefreshListener {

    private GroupScoreAdapter groupScoreAdapter;
    private List<Object> listGroupScore = new ArrayList<>();
    private SwipeRefreshLayout mSwipeContent;
    private boolean isLoaded = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_score_layout, container, false);
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                mGestureDetector.onTouchEvent(motionEvent);
//                return true;
//            }
//        });

        mSwipeContent = (SwipeRefreshLayout) view.findViewById(R.id.group_refresh_content);
        mSwipeContent.setOnRefreshListener(this);

        ListView groupView = (ListView) view.findViewById(R.id.group_detail);
        groupScoreAdapter = new GroupScoreAdapter(getContext(), 0, listGroupScore);
        groupView.setAdapter(groupScoreAdapter);
        if (!isLoaded) {
            mSwipeContent.setRefreshing(true);
            Log.e("THE_DV", "GROUPING RUNNING");
            LoadGroupDataFromSQLTask task = new LoadGroupDataFromSQLTask(getActivity());
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        return view;
    }

    public void refreshFromActivity(Context context) {
        if (!isLoaded) {
            LoadGroupDataFromSQLTask task = new LoadGroupDataFromSQLTask(context);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onRefresh() {
        if (!isLoaded) {
            LoadGroupDataFromSQLTask task = new LoadGroupDataFromSQLTask(getActivity());
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } if (isLoaded) {
            mSwipeContent.setRefreshing(false);
        }
    }

    class LoadGroupDataFromSQLTask extends AsyncTask<Void, Void, List<Object>> {
        Context context;

        LoadGroupDataFromSQLTask(Context pContext) {
            context = pContext;
        }

        @Override
        protected void onPreExecute() {
            isLoaded = true;
        }

        @Override
        protected List<Object> doInBackground(Void... c) {
            Log.e("THE_DV", "Executing asyntask of Groupscore collection");
            List<Object> groupDataFromListView = new ArrayList<>();
            groupDataFromListView.removeAll(groupDataFromListView);
            LoadData loadData = null;
            try {
                loadData = new LoadData(context, "wcdata");
            } catch (IllegalArgumentException w) {
                Log.e("THE_DV", w.getMessage());
            }
            if (loadData != null) {
                List<GroupScore> listGroupFromSqlData = loadData.getGroupScoreData();
                groupDataFromListView.removeAll(groupDataFromListView);
                for (GroupScore GroupData : listGroupFromSqlData) {
                    groupDataFromListView.add(GroupData);
                }
                loadData.closeConnect();
            }
            return groupDataFromListView;
        }

        @Override
        protected void onPostExecute(List<Object> objects) {
            Log.e("THE_DV", "load done GROUPING");
            isLoaded = false;
            if (objects.size() != 0) {
                listGroupScore.removeAll(listGroupScore);
                for (Object o : objects) {
                    listGroupScore.add(o);
                }
                groupScoreAdapter.notifyDataSetChanged();
                mSwipeContent.setRefreshing(false);
            }
        }
    }
}
