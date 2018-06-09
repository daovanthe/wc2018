package data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.raw.LiveScore;


@Dao
public interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(LiveScore... liveScores);

    @Query("SELECT * FROM score Order by added desc")
    public List<LiveScore> getScore();

    @Query("SELECT * FROM score WHERE id = :scoreId ")
    public List<LiveScore> getScoreById(String scoreId);

}
