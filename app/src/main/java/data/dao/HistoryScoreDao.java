package data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.raw.History;
import data.raw.LiveScore;


@Dao
public interface HistoryScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(History... liveScores);

    @Query("SELECT * FROM history Order by added desc")
    public List<History> getScore();

    @Query("SELECT * FROM history WHERE id = :scoreId ")
    public List<History> getScoreById(String scoreId);

    @Query("SELECT * FROM history WHERE league_id = :leagueId")
    public List<History> getLiveScoreByLeagueId(String leagueId);


}
