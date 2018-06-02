package data.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import data.raw.Fixtures;
import data.raw.Leagues;
import data.raw.Score;

@Database(entities = {Leagues.class, Fixtures.class, Score.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract LeaguesDao getLeaguesDao();
    public abstract FixturesDao getFixtureDao();
    public abstract ScoreDao getScoreDao();
}
