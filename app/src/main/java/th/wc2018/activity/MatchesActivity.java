package th.wc2018.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import th.wc2018.R;
import th.wc2018.adapter.MatchAdapter;

public class MatchesActivity extends Activity {

    private ListView allMatches;
    private Object[] matchesData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_matches_layout);
        allMatches = (ListView) findViewById(R.id.all_matches);

        matchesData = loadData();
        MatchAdapter matchesAdapter = new MatchAdapter(getBaseContext(), 0, matchesData);
        allMatches.setAdapter(matchesAdapter);


    }

    private Object[] loadData() {



        return new Object[0];
    }


}
