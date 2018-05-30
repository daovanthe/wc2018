package data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import wc2018.data.raw.Score;

@Dao
public interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Score... scores);

    @Query("SELECT * FROM score")
    public List<Score> getScore();

    @Query("SELECT * FROM score WHERE id = :scoreId ")
    public List<Score> getScoreById(String scoreId);

}
