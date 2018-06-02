package data.raw;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "score")
public class Score {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "home_name")
    private String home_name;

    @ColumnInfo(name = "away_name")
    private String away_name;

    @ColumnInfo(name = "score")
    private String score;

    @ColumnInfo(name = "ht_score")
    private String ht_score;

    @ColumnInfo(name = "ft_score")
    private String ft_score;

    @ColumnInfo(name = "et_score")
    private String et_score;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "league_id")
    private String league_id;

    @ColumnInfo(name = "league_name")
    private String league_name;

    @ColumnInfo(name = "added")
    private String added;

    @ColumnInfo(name = "last_changed")
    private String last_changed;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "home_id")
    private String home_id;

    @ColumnInfo(name = "away_id")
    private String awayId;

    @ColumnInfo(name = "events")
    private String events;

    public Score() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getHome_name() {
        return home_name;
    }

    public void setHome_name(String home_name) {
        this.home_name = home_name;
    }

    public String getAway_name() {
        return away_name;
    }

    public void setAway_name(String away_name) {
        this.away_name = away_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getHt_score() {
        return ht_score;
    }

    public void setHt_score(String ht_score) {
        this.ht_score = ht_score;
    }

    public String getFt_score() {
        return ft_score;
    }

    public void setFt_score(String ft_score) {
        this.ft_score = ft_score;
    }

    public String getEt_score() {
        return et_score;
    }

    public void setEt_score(String et_score) {
        this.et_score = et_score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getLast_changed() {
        return last_changed;
    }

    public void setLast_changed(String last_changed) {
        this.last_changed = last_changed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHome_id() {
        return home_id;
    }

    public void setHome_id(String home_id) {
        this.home_id = home_id;
    }

    public String getAwayId() {
        return awayId;
    }

    public void setAwayId(String awayId) {
        this.awayId = awayId;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id='" + id + '\'' +
                ", home_name='" + home_name + '\'' +
                ", away_name='" + away_name + '\'' +
                ", score='" + score + '\'' +
                ", ht_score='" + ht_score + '\'' +
                ", ft_score='" + ft_score + '\'' +
                ", et_score='" + et_score + '\'' +
                ", time='" + time + '\'' +
                ", league_id='" + league_id + '\'' +
                ", league_name='" + league_name + '\'' +
                ", added='" + added + '\'' +
                ", last_changed='" + last_changed + '\'' +
                ", status='" + status + '\'' +
                ", home_id='" + home_id + '\'' +
                ", awayId='" + awayId + '\'' +
                ", events='" + events + '\'' +
                '}';
    }
}
