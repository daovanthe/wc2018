package data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.raw.Events;


@Dao
public interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Events... liveScores);

    @Query("SELECT * FROM events")
    public List<Events> getEvent();

    @Query("SELECT * FROM events WHERE league_id = :leagueId AND match_id = :matchId")
    public List<Events> getEventBy(String leagueId, String matchId);

    @Query("SELECT * FROM events WHERE id = :scoreId ")
    public List<Events> getScoreById(String scoreId);

}
