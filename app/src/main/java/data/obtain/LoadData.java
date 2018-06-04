package data.obtain;

import android.arch.persistence.room.Room;
import android.content.Context;

import data.dao.AppDatabase;
import data.dao.FixturesDao;
import data.dao.LeaguesDao;
import data.dao.ScoreDao;

public class LoadData {

    private AppDatabase db;
    private FixturesDao fixturesDao;
    private LeaguesDao leaguesDao;
    private ScoreDao scoreDao;




     public LoadData(Context pContext, String nameDatabase) {
        db = Room.databaseBuilder(pContext,
                AppDatabase.class, nameDatabase).build();
        scoreDao = db.getScoreDao();
        leaguesDao = db.getLeaguesDao();
        fixturesDao = db.getFixtureDao();
    }
    public void closeConnect() {
         db.close();
    }



    public FixturesDao getFixturesDao() {
        return fixturesDao;
    }

    public LeaguesDao getLeaguesDao() {
        return leaguesDao;
    }


    public ScoreDao getScoreDao() {
        return scoreDao;
    }

}
