package th.wc2018;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import data.firebase.DataFireBase;
import th.wc2018.api.API;
import th.wc2018.api.apiImp.FixturesAPI;
import th.wc2018.api.apiImp.LeaguesApi;
import th.wc2018.api.apiImp.ScoreApi;

public class WcService extends Service {

    public static String TAG = "THE_DV";

    private API fixturesApi, leagueApi, scoreApi;

    private Object o = new Object();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "service WC created");
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
//                                scoreApi = new ScoreApi(city.getKey(), city.getSecret(), city.getLegue1());
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
        leagueApi = new LeaguesApi();
        scoreApi = new ScoreApi();
        runTask();



    }

    public void runTask() {
        new Thread() {
            int count = 0;

            public void run() {
                while (true) {
                    try {
                        count++;
                        Log.d(TAG, "get API automatically");
                        getObjectApi();
                        Thread.sleep(1800000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void getObjectApi() {
        Log.d(TAG, "WcService -> getObjectApi()");
        new Thread() {
            public void run() {
                fixturesApi.getObject();
            }
        }.start();

        new Thread() {
            public void run() {
                scoreApi.getObject();
            }
        }.start();

        new Thread() {
            public void run() {
                leagueApi.getObject();
            }
        }.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "service WC Destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public WcService getService() {
            return WcService.this;
        }
    }

}
