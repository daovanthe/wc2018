package data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.raw.Fixtures;


@Dao
public interface FixturesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Fixtures... fixtures);

    @Query("SELECT * FROM fixtures")
    public List<Fixtures> getFixtures();

    @Query("SELECT * FROM fixtures WHERE id = :fixtureId ")
    public List<Fixtures> getFixturesById(String fixtureId);

    @Query("SELECT distinct date FROM fixtures Order by date")
    public List<String> getDay();

    @Query("SELECT time FROM fixtures where date = :date")
    public List<String> getTime(String date);

    @Query("SELECT * FROM fixtures WHERE date = :date  ")
    public List<Fixtures> getMatchByDay(String date );

}
