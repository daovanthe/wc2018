package data.raw;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "events", primaryKeys = { "id", "league_id" })

public class Events {

    @NonNull
    private String id;

    @ColumnInfo(name = "match_id")
    private String match_id;

    @ColumnInfo(name = "player")
    private String player;

    @ColumnInfo(name = "time")
    private String time;

    @NonNull
    @ColumnInfo(name = "league_id")
    private String league_id;

    public String getHome_away_name() {
        return home_away_name;
    }

    public void setHome_away_name(String home_away_name) {
        this.home_away_name = home_away_name;
    }

    private String home_away_name;


    public Events(@NonNull String id, String match_id, String player, String time, String league_id, String event, String sort, String home_away) {
        this.id = id;
        this.match_id = match_id;
        this.player = player;
        this.time = time;
        this.league_id = league_id;
        this.event = event;
        this.sort = sort;
        this.home_away = home_away;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getHome_away() {
        return home_away;
    }

    public void setHome_away(String home_away) {
        this.home_away = home_away;
    }

    @ColumnInfo(name = "event")
    private String event;

    @ColumnInfo(name = "sort")
    private String sort;

    @ColumnInfo(name = "home_away")
    private String home_away;
}
