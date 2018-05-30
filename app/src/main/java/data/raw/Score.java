package data.raw;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "score")
public class Score {

    @PrimaryKey
    @NonNull
    private String id;
    private String homeName;
    private String awayName;
    private String score;
    private String htScore;
    private String ftScore;
    private String etScore;
    private String time;
    private String leagueId;
    private String leagueName;
    private String added;
    private String lastChanged;
    private String status;
    private String homeId;
    private String awayId;
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

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getHtScore() {
        return htScore;
    }

    public void setHtScore(String htScore) {
        this.htScore = htScore;
    }

    public String getFtScore() {
        return ftScore;
    }

    public void setFtScore(String ftScore) {
        this.ftScore = ftScore;
    }

    public String getEtScore() {
        return etScore;
    }

    public void setEtScore(String etScore) {
        this.etScore = etScore;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(String lastChanged) {
        this.lastChanged = lastChanged;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
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
                ", homeName='" + homeName + '\'' +
                ", awayName='" + awayName + '\'' +
                ", score='" + score + '\'' +
                ", htScore='" + htScore + '\'' +
                ", ftScore='" + ftScore + '\'' +
                ", etScore='" + etScore + '\'' +
                ", time='" + time + '\'' +
                ", leagueId='" + leagueId + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", added='" + added + '\'' +
                ", lastChanged='" + lastChanged + '\'' +
                ", status='" + status + '\'' +
                ", homeId='" + homeId + '\'' +
                ", awayId='" + awayId + '\'' +
                ", events='" + events + '\'' +
                '}';
    }
}
