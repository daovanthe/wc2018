package th.wc2018.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import data.obtain.LoadData;
import data.raw.GroupScore;
import th.wc2018.R;
import th.wc2018.adapter.GroupScoreAdapter;

public class GroupScoreFragment extends CommonFragment {

    private GroupScoreAdapter groupScoreAdapter;
    private List<Object> listLiveScore = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_score_layout, container, false);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        ListView groupView = (ListView) view.findViewById(R.id.group_detail);
        groupScoreAdapter = new GroupScoreAdapter(getContext(), 0, listLiveScore);
        groupView.setAdapter(groupScoreAdapter);

        LoadGroupDataFromSQLTask task = new LoadGroupDataFromSQLTask();
        task.execute(listLiveScore);


        return view;
    }


    class LoadGroupDataFromSQLTask extends AsyncTask<List<Object>, Void, List<Object>> {
        @Override
        protected List<Object> doInBackground(List<Object>... allmatchesInfos) {
            List<Object> groupDataFromListView = allmatchesInfos[0];// new ArrayList<>();
            groupDataFromListView.removeAll(groupDataFromListView);
            LoadData loadData = null;
            try {
                loadData = new LoadData(getActivity(), "wcdata");
            } catch (IllegalArgumentException w) {
                w.printStackTrace();
            }
            if (loadData != null) {
                List<GroupScore> listGroupFromSqlData = loadData.getGroupScoreData();
                groupDataFromListView.removeAll(groupDataFromListView);
                for (GroupScore GroupData : listGroupFromSqlData) {
                    groupDataFromListView.add(GroupData);
                }
                onProgressUpdate();
                loadData.closeConnect();
            }
            return groupDataFromListView;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
            getActivity().runOnUiThread(() -> {
                groupScoreAdapter.notifyDataSetChanged();
            });
        }

        @Override
        protected void onPostExecute(List<Object> list) {
            super.onPostExecute(list);
        }
    }
}
