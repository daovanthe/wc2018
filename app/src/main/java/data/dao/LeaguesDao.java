package data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.raw.Leagues;


@Dao
public interface LeaguesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Leagues... legues);

    @Query("SELECT * FROM leagues")
    public List<Leagues> getLegues();

    @Query("SELECT * FROM leagues WHERE leagueId = :leagueId ")
    public List<Leagues> getLeguesById(String leagueId);
}
