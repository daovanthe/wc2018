package data.raw;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "leagues")
public class Leagues {

    @PrimaryKey
    @NonNull
    private String leagueId;

    @ColumnInfo(name = "league_name")
    private String league_name;

    @ColumnInfo(name = "country_id")
    private String country_id;

    @ColumnInfo(name = "country_name")
    private String country_name;

    @ColumnInfo(name = "fixures")
    private String fixures;

    @ColumnInfo(name = "scores")
    private String scores;

    public Leagues() {
    }

    @NonNull
    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(@NonNull String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getFixures() {
        return fixures;
    }

    public void setFixures(String fixures) {
        this.fixures = fixures;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Leagues{" +
                "leagueId='" + leagueId + '\'' +
                ", league_name='" + league_name + '\'' +
                ", country_id='" + country_id + '\'' +
                ", country_name='" + country_name + '\'' +
                ", fixures='" + fixures + '\'' +
                ", scores='" + scores + '\'' +
                '}';
    }
}
