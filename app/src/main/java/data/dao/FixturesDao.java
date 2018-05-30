package data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import wc2018.data.raw.Fixtures;

@Dao
public interface FixturesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Fixtures... fixtures);

    @Query("SELECT * FROM fixtures")
    public List<Fixtures> getFixtures();

    @Query("SELECT * FROM fixtures WHERE id = :fixtureId ")
    public List<Fixtures> getFixturesById(String fixtureId);

}
