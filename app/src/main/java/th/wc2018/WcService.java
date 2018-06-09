package th.wc2018;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import data.obtain.LoadData;
import data.raw.Events;
import data.raw.Fixtures;
import data.raw.LiveScore;
import th.wc2018.api.API;
import th.wc2018.api.OnLoadApiCompletedListener;
import th.wc2018.api.apiImp.FixturesAPI;
import th.wc2018.api.apiImp.LeaguesApi;
import th.wc2018.api.apiImp.LiveScoreApi;
import th.wc2018.broadcast.MAction;
import th.wc2018.service.ILoadDataApiListener;

public class WcService extends Service {

    public static String TAG = "THE_DV";

    private API fixturesApi, leagueApi;
    private LiveScoreApi liveScoreApi;
    private Object o = new Object();
    private Runnable mRunnable;

    @Override
    public void onCreate() {
        super.onCreate();
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
        Log.e(TAG, "service WC created");
        //  Write a message to the database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
//        db.collection("test1")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                                DataFireBase city = document.toObject(DataFireBase.class);
//                                fixturesApi = new FixturesAPI(city.getKey(), city.getSecret(), city.getLegue1());
//                                leagueApi = new LeaguesApi(city.getKey(), city.getSecret(), city.getLegue1());
//                                liveScoreApi = new LiveScoreApi(city.getKey(), city.getSecret(), city.getLegue1());
//                                runTask();
//                            }
//
//
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });

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
                }
            }
        });


        liveScoreApi = new LiveScoreApi();
        liveScoreApi.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
            @Override
            public void loadApiCompleted(Object... result) {
                LiveScore[] liveScoreDatas = (LiveScore[]) result;
                if (mLoadData != null) {
                    mLoadData.getLiveScoreDao().insert(liveScoreDatas);
                    Log.e("THE_DV", "mLoadData SCORE OK ");
                    Intent intent = new Intent();
                    intent.setAction(MAction.REQUEST_DATABASE_CHANGE);
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
                    Intent intent = new Intent();
                    intent.setAction(MAction.REQUEST_DATABASE_CHANGE);
                    sendBroadcast(intent);
                }
            }
        });

        leagueApi = new LeaguesApi();
//        leagueApi.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
//            @Override
//            public void loadApiCompleted(Object... result) {
//                Leagues[] LeguesData = (Leagues[]) result;
//                mLoadData.getLeaguesDao().insert(LeguesData);
//            }
//        });
        runTask();
    }


    public void runTask() {
        new Thread() {
            int count = 0;

            public void run() {
                while (true) {
                    try {
                        count++;
                        Log.e(TAG, "get API automatically");
                        getObjectApi();
                        Thread.sleep(1800000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private byte count = 0;

    public void getObjectApi() {
        Log.e(TAG, "WcService -> getObjectApi()");
        Object lock = new Object();

        count = 0;
        new Thread() {
            public void run() {
                fixturesApi.getObject();
                count++;
                if (count == 3) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                liveScoreApi.getObject();

                count++;
                if (count == 3) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                leagueApi.getObject();
                count++;
                if (count == 3) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            }
        }.start();

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //  // notify to Load data OK!
        if (mILoadDataApiListener != null) {
            mILoadDataApiListener.loadDone();
        }
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
