package data.raw;

import android.support.annotation.NonNull;

public class CountryScore implements Comparable<CountryScore> {

    String Country;

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    String ST, Win, Draw, Lost, BT, BB, HIEU_SO, POINT;


    public String getST() {
        return ST;
    }

    public CountryScore(String CountryName, String ST, String win, String draw, String lost, String BT, String BB, String HIEU_SO, String POINT) {
        this.Country = CountryName;
        this.ST = ST;
        Win = win;
        Draw = draw;
        Lost = lost;
        this.BT = BT;
        this.BB = BB;
        this.HIEU_SO = HIEU_SO;
        this.POINT = POINT;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public String getWin() {
        return Win;
    }

    public void setWin(String win) {
        Win = win;
    }

    public String getDraw() {
        return Draw;
    }

    public void setDraw(String draw) {
        Draw = draw;
    }

    public String getLost() {
        return Lost;
    }

    public void setLost(String lost) {
        Lost = lost;
    }

    public String getBT() {
        return BT;
    }

    public void setBT(String BT) {
        this.BT = BT;
    }

    public String getBB() {
        return BB;
    }

    public void setBB(String BB) {
        this.BB = BB;
    }

    public String getHIEU_SO() {
        return HIEU_SO;
    }

    public void setHIEU_SO(String HIEU_SO) {
        this.HIEU_SO = HIEU_SO;
    }

    public String getPOINT() {
        return POINT;
    }

    public void setPOINT(String POINT) {
        this.POINT = POINT;
    }

    @Override
    public int compareTo(@NonNull CountryScore countryScore) {
        if (Integer.valueOf(this.getPOINT()) > Integer.valueOf(countryScore.getPOINT())) {
            return -1;
        } else if (Integer.valueOf(this.getPOINT()) < Integer.valueOf(countryScore.getPOINT())) {
            return 1;
        }
        else {
            if (Integer.valueOf(this.getHIEU_SO()) > Integer.valueOf(countryScore.getHIEU_SO())) {
                return -1;
            } else if (Integer.valueOf(this.getPOINT()) < Integer.valueOf(countryScore.getPOINT())) {
                return 1;
            }
        }
        return 0;
    }
}
