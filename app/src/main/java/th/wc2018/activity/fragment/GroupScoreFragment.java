package th.wc2018.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import data.obtain.LoadData;
import data.raw.Events;
import data.raw.GroupScore;
import data.raw.LiveScore;
import th.wc2018.R;
import th.wc2018.adapter.GroupScoreAdapter;
import th.wc2018.adapter.LiveScoreEventAdapter;

public class GroupScoreFragment extends CommonFragment {

    private GroupScoreAdapter liveScoreAdapter;
    private List<GroupScore> listLiveScore = new ArrayList<>();


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
        liveScoreAdapter = new GroupScoreAdapter(getContext(), 0, listLiveScore);
        groupView.setAdapter(liveScoreAdapter);

        LoadDataFromSQLTask task = new LoadDataFromSQLTask();
        task.execute(listLiveScore);


        return view;
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
}
