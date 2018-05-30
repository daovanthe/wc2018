package data.raw;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "legues")
public class Legues {

    @PrimaryKey
    @NonNull
    private String leagueId;
    private String leagueName;
    private String countryId;
    private String countryName;
    private String fixures;
    private String scores;

    public Legues() {
    }

    @NonNull
    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(@NonNull String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
        return "Legues{" +
                "leagueId='" + leagueId + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", countryId='" + countryId + '\'' +
                ", countryName='" + countryName + '\'' +
                ", fixures='" + fixures + '\'' +
                ", scores='" + scores + '\'' +
                '}';
    }
}
