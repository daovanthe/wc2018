package data.raw;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "fixtures")
public class Fixtures {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "round")
    private String round;

    @ColumnInfo(name = "home_name")
    private String home_name;

    @ColumnInfo(name = "away_name")
    private String away_name;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "league_id")
    private String league_id;

    @ColumnInfo(name = "home_id")
    private String home_id;

    @ColumnInfo(name = "away_id")
    private String away_id;

    @ColumnInfo(name = "group_id")
    private String group_id;

    @ColumnInfo(name = "match_id")
    private String match_id;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public Fixtures() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getHome_id() {
        return home_id;
    }

    public void setHome_id(String home_id) {
        this.home_id = home_id;
    }

    public String getAway_id() {
        return away_id;
    }

    public void setAway_id(String away_id) {
        this.away_id = away_id;
    }

    @Override
    public String toString() {
        return "Fixtures{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", round='" + round + '\'' +
                ", home_name='" + home_name + '\'' +
                ", away_name='" + away_name + '\'' +
                ", location='" + location + '\'' +
                ", league_id='" + league_id + '\'' +
                ", home_id='" + home_id + '\'' +
                ", away_id='" + away_id + '\'' +
                '}';
    }
}
