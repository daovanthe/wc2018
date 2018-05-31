package data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.raw.Legues;


@Dao
public interface LeguesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Legues... legues);

    @Query("SELECT * FROM legues")
    public List<Legues> getLegues();

    @Query("SELECT * FROM legues WHERE leagueId = :leagueId ")
    public List<Legues> getLeguesById(String leagueId);
}
