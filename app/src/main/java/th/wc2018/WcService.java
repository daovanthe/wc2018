package th.wc2018;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreSettings;

import data.obtain.LoadData;
import data.raw.Events;
import data.raw.Fixtures;
import data.raw.History;
import data.raw.LiveScore;
import th.wc2018.api.API;
import th.wc2018.api.OnLoadApiCompletedListener;
import th.wc2018.api.apiImp.FixturesAPI;
import th.wc2018.api.apiImp.HistoryAPI;
import th.wc2018.api.apiImp.LeaguesApi;
import th.wc2018.api.apiImp.LiveScoreApi;
import th.wc2018.broadcast.MAction;
import th.wc2018.service.ILoadDataApiListener;

public class WcService extends Service {

    public static String TAG = "THE_DV";

    private API fixturesApi, leagueApi;
    private LiveScoreApi liveScoreApi;
    private HistoryAPI historyAPI;

    private Object o = new Object();
    private Runnable mRunnable;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "service WC created");
        runServiceTask();
    }

    private Context mContext;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLoadData = new LoadData(getApplicationContext(), "wcdata");
        ;//super.onStartCommand(intent, flags, startId);
        return Service.START_NOT_STICKY;
    }

    public void runServiceTask() {

        fixturesApi = new FixturesAPI();
        fixturesApi.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
            @Override
            public void loadApiCompleted(Object... result) {
                Fixtures[] fixturesData = (Fixtures[]) result;
                if (mLoadData != null) {
                    mLoadData.getFixturesDao().insert(fixturesData);
                    Intent intent = new Intent();
                    intent.setAction(MAction.FIXTURES_DATABASE_CHANGE);
                    sendBroadcast(intent);
                    Log.e("THE_DV", "mLoadData Matches OK , send broadcast .... ");
                }
            }
        });


        liveScoreApi = new LiveScoreApi();
        liveScoreApi.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
            @Override
            public void loadApiCompleted(Object... result) {
                LiveScore[] liveScoreDatas = (LiveScore[]) result;
                if (liveScoreDatas.length == 0) return;
                if (mLoadData != null) {
                    mLoadData.getLiveScoreDao().insert(liveScoreDatas);
                    Log.e("THE_DV", "mLoadData SCORE OK , send broadcast .... ");
                    Intent intent = new Intent();
                    intent.setAction(MAction.REQUEST_DATABASE_SCORE_CHANGE);
                    sendBroadcast(intent);
                }
            }
        });

        liveScoreApi.AddLoadLiveScoreEvent(new LiveScoreApi.ILoadLiveScoreEvent() {
            @Override
            public void loadEventCompleted(Events[] liveLiveScoreEvent) {
                if (mLoadData != null) {
                    mLoadData.getEventLiveScoreDao().insert(liveLiveScoreEvent);
                    Log.e("THE_DV", "service load Event OK! ");
                }
            }
        });

        historyAPI = new HistoryAPI();
        historyAPI.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
            @Override
            public void loadApiCompleted(Object... result) {
                History[] liveScoreDatas = (History[]) result;
                if (liveScoreDatas.length == 0) return;
                if (mLoadData != null) {
                    mLoadData.getHistoryScoreDao().insert(liveScoreDatas);
                    Log.e("THE_DV", "mLoadData History OK ");
                    Intent intent = new Intent();
                    intent.setAction(MAction.REQUEST_DATABASE_HISTORY_CHANGE);
                    sendBroadcast(intent);
                }
            }
        });
        leagueApi = new LeaguesApi();
        runTask();
    }


    public void runTask() {
        getObjectApi();
    }

    private byte count = 0;

    private final static int THRESHOLD = 4;

    public void getObjectApi() {
        Log.e(TAG, "WcService -> getObjectApi()");
        Object lock = new Object();
        count = 0;
        new Thread() {
            public void run() {
                fixturesApi.loadObjectFromIntenet();
            }
        }.start();
        new Thread() {
            public void run() {
                liveScoreApi.loadObjectFromIntenet();

            }
        }.start();
        new Thread() {
            public void run() {
                leagueApi.loadObjectFromIntenet();
            }
        }.start();
        new Thread() {
            public void run() {
                historyAPI.loadObjectFromIntenet();
            }
        }.start();
        //  // notify to Load data OK!
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "service WC Destroyed");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("THE_DV", "UNBIND SERVICE ");
        return super.onUnbind(intent);

    }

    private final IBinder binder = new LocalBinder();

    private ILoadDataApiListener mILoadDataApiListener;

    public void onLoadDataOK(ILoadDataApiListener loadDataApiListener) {
        mILoadDataApiListener = loadDataApiListener;
    }

    public class LocalBinder extends Binder {
        public WcService getService() {
            return WcService.this;
        }
    }

    private LoadData mLoadData;// = new LoadData(getApplicationContext(), "wcdata");

}
