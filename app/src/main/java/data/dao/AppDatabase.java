package data.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import data.raw.Events;
import data.raw.Fixtures;
import data.raw.Leagues;
import data.raw.LiveScore;

@Database(entities = {Leagues.class, Fixtures.class, LiveScore.class, Events.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract LeaguesDao getLeaguesDao();
    public abstract FixturesDao getFixtureDao();
    public abstract ScoreDao getScoreDao();
    public abstract EventsDao getEventsDao();
}
