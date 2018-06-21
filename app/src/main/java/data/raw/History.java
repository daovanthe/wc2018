package data.raw;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Entity(tableName = "history")
public class History {

    @Ignore
    private Object object;

    public void setTag(Object tag) {
        this.object = tag;
    }

    public Object getTag() {
        return object;
    }

    // id of the match
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
    private String away_id;

    @ColumnInfo(name = "events")
    private String events;

    public String getAway_id() {
        return away_id;
    }

    public void setAway_id(String away_id) {
        this.away_id = away_id;
    }

    public History() {
        listEvents = new ArrayList<>();
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
        String timeConverted = null;
        try {
            Date time = dateFormat.parse(last_changed);
            timeConverted = new SimpleDateFormat("HH:mm:ss").format(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeConverted == null ? last_changed : timeConverted;
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


    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public void addEvent(Events pListEvent) {
        this.listEvents.add(pListEvent);
    }

    public List<Events> getListEvents() {
        return this.listEvents;
    }

    @Ignore
    private List<Events> listEvents;

    @Override
    public String toString() {
        return "LiveScore{" +
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
                ", awayId='" + away_id + '\'' +
                ", events='" + events + '\'' +
                '}';
    }

    public static History makeHistory(LiveScore liveScore1) {
        return new History(
                liveScore1.id ,
                liveScore1.home_name,
                liveScore1.away_name,
                liveScore1.score,
                liveScore1.ht_score,
                liveScore1.ft_score,
                liveScore1.et_score,
                liveScore1.time,
                liveScore1.league_id,
                liveScore1.league_name,
                liveScore1.added,
                liveScore1.last_changed,
                liveScore1.status,
                liveScore1.home_id,
                liveScore1.away_id,
                liveScore1.events
        );
    }

    public History(@NonNull String id, String home_name, String away_name, String score, String ht_score, String ft_score, String et_score, String time, String league_id, String league_name, String added, String last_changed, String status, String home_id, String away_id, String events ) {
        this.listEvents = new ArrayList<>();
        this.id             = id;
        this.home_name      = home_name;
        this.away_name      = away_name;
        this.score          = score;
        this.ht_score       = ht_score;
        this.ft_score       = ft_score;
        this.et_score       = et_score;
        this.time           = time;
        this.league_id      = league_id;
        this.league_name    = league_name;
        this.added          = added;
        this.last_changed       = last_changed;
        this.status         = status;
        this.home_id        = home_id;
        this.away_id        = away_id;
        this.events         = events;
    }
}
