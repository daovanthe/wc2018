package data.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import wc2018.data.dao.FixturesDao;
import wc2018.data.dao.LeguesDao;
import wc2018.data.dao.ScoreDao;
import wc2018.data.raw.Fixtures;
import wc2018.data.raw.Legues;
import wc2018.data.raw.Score;

@Database(entities = {Legues.class, Fixtures.class, Score.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract LeguesDao getLeguesDao();
    public abstract FixturesDao getFixtureDao();
    public abstract ScoreDao getScoreDao();
}
