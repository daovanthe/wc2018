package th.wc2018.activity;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.the.wcservice.WcService;

import java.util.List;

import th.wc2018.R;
import wc2018.data.dao.AppDatabase;
import wc2018.data.dao.LeguesDao;
import wc2018.data.raw.Legues;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Intent serviceIntent = new Intent();
        serviceIntent.setPackage("th.wc2018");
        serviceIntent.setClass(this, WcService.class);
        startService(serviceIntent);



        new Thread(() -> {
            AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "db-wc")
                    .allowMainThreadQueries()   //Allows room to do operation on main thread
                    .build();

            LeguesDao leguesDao = database.getLeguesDao();

            Legues legues1 = new Legues();
            legues1.setLeagueId("1");
            legues1.setCountryId("1");
            leguesDao.insert(legues1);

            Legues legues2 = new Legues();
            legues1.setLeagueId("2");
            legues1.setCountryId("2");
            leguesDao.insert(legues2);

            List<Legues> leguesList = leguesDao.getLegues();

            for (Legues legue: leguesList){
                Log.d("Legues", legue.toString());
            }
            Log.d("Legues1 : ", leguesDao.getLeguesById("1").toString());

            Legues leguesUpdate = new Legues();
            legues1.setLeagueId("1");
            legues1.setCountryId("Update Legue 1");

        }).start();




    }
}
