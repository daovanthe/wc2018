package data.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import data.raw.Fixtures;
import data.raw.Legues;
import data.raw.Score;

@Database(entities = {Legues.class, Fixtures.class, Score.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract LeguesDao getLeguesDao();
    public abstract FixturesDao getFixtureDao();
    public abstract ScoreDao getScoreDao();
}
