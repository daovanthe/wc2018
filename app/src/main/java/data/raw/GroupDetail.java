package data.raw;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "groups")
public class GroupDetail {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "group_name")
    private String group_name;

    @ColumnInfo(name = "mp")
    private String MP;

    @ColumnInfo(name = "w")
    private String W;

    @ColumnInfo(name = "d")
    private String D;

    @ColumnInfo(name = "l")
    private String L;

    @ColumnInfo(name = "gp")
    private String GP;

    @ColumnInfo(name = "hieuso")
    private String hieuso;

    @ColumnInfo(name = "pts")
    private String PTS;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getMP() {
        return MP;
    }

    public void setMP(String MP) {
        this.MP = MP;
    }

    public String getW() {
        return W;
    }

    public void setW(String w) {
        W = w;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getL() {
        return L;
    }

    public void setL(String l) {
        L = l;
    }

    public String getGP() {
        return GP;
    }

    public void setGP(String GP) {
        this.GP = GP;
    }

    public String getHieuso() {
        return hieuso;
    }

    public void setHieuso(String hieuso) {
        this.hieuso = hieuso;
    }

    public String getPTS() {
        return PTS;
    }

    public void setPTS(String PTS) {
        this.PTS = PTS;
    }
}
