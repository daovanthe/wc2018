package th.wc2018.api.apiImp;

import android.util.Log;

import th.wc2018.api.API;


public class LeaguesApi extends API {

    private static final String LINK = "http://livescore-api.com/api-client/fixtures/matches.json?";

    public LeaguesApi(String key, String secret, String[] legue1) {
        super(key, secret, legue1);
    }

    public LeaguesApi() {
    }

    @Override
    public void getObject() {
        Log.d(TAG, "LeaguesApi -> getObject()");

        if (mOnLoadApiComletedListener != null) {
            mOnLoadApiComletedListener.loadApiCompleted();
        }
    }
}
