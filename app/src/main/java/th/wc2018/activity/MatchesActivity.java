package th.wc2018.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import data.obtain.LoadData;
import data.raw.Fixtures;
import th.wc2018.R;
import th.wc2018.adapter.MatchAdapter;

public class MatchesActivity extends Activity {

    private ListView allMatches;
    private List<Object> matchesData = new ArrayList<>();
    private MatchAdapter matchesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_matches_layout);
        allMatches = (ListView) findViewById(R.id.all_matches);
         matchesAdapter = new MatchAdapter(getBaseContext(), R.layout.date_match_layout, matchesData);

        new Thread(() -> {
            loadData(matchesData);
        }).start();


        allMatches.setAdapter(matchesAdapter);


    }

    private void loadData(List<Object> allmatchesInfo) {
        //
        LoadData loadData = new LoadData(this, "wcdata");

        List<String> days = loadData.getFixturesDao().getDay();
        for (String day : days) {
            allmatchesInfo.add(day);
            List<Fixtures> listMatchesPerDay = loadData.getFixturesDao().getMatchByDay(day);
            for (Fixtures singleMatch : listMatchesPerDay) {
                runOnUiThread(() -> {
                    matchesAdapter.notifyDataSetChanged();
                });
                allmatchesInfo.add(singleMatch);
            }
        }
        // test
//        Object[] obj = new Object[2];
//        obj[0] = new String("Match 1");
//        Fixtures fixtures = new Fixtures("856735",
//                "2018-06-14",
//                "18:00:00",
//                "GS",
//                "Russia",
//                "Saudi Arabia",
//                "Luzhniki Stadium, Moscow",
//                "793",
//                "1431",
//                "1432",
//                "1",
//                "112");
//        obj[1] = fixtures;
//        return obj;
    }


}
