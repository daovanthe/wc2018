package data.obtain;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import data.dao.AppDatabase;
import data.dao.EventsDao;
import data.dao.FixturesDao;
import data.dao.LeaguesDao;
import data.dao.ScoreDao;
import data.raw.CountryScore;
import data.raw.GroupScore;
import data.raw.LiveScore;
import th.wc2018.ulity.UtilConvertor;

public class LoadData {

    private AppDatabase db;
    private FixturesDao fixturesDao;
    private LeaguesDao leaguesDao;
    private ScoreDao scoreDao;
    private EventsDao eventLiveScoreDao;

    public LoadData(Context pContext, String nameDatabase) {
        db = Room.databaseBuilder(pContext,
                AppDatabase.class, nameDatabase).build();

        scoreDao = db.getScoreDao();
        leaguesDao = db.getLeaguesDao();
        fixturesDao = db.getFixtureDao();
        eventLiveScoreDao = db.getEventsDao();
    }


    public void closeConnect() {
        db.close();
    }


    public FixturesDao getFixturesDao() {
        return fixturesDao;
    }

    public LeaguesDao getLeaguesDao() {
        return leaguesDao;
    }

    public ScoreDao getLiveScoreDao() {
        return scoreDao;
    }

    public EventsDao getEventLiveScoreDao() {
        return eventLiveScoreDao;
    }


    public List<GroupScore> getGroupScoreData() {

        HashMap<String, CountryScore> mergeData = new HashMap<>();

        List<GroupScore> result = new ArrayList<>();

        Set<String> groups = UtilConvertor.hashGroup.keySet();
        for (String countryCode : groups) {
            List<LiveScore> scores = scoreDao.getLiveScoreByLeagueId(countryCode);
            for (LiveScore score : scores) {
                //tinh toan cac chi so
                String ti_so= score.getScore();
                int ket_qua_home = Integer.valueOf(ti_so.trim().split("-")[0].trim());
                int ket_qua_away = Integer.valueOf(ti_so.trim().split("-")[1].trim());
                int win = ket_qua_home>ket_qua_away?1:0;
                int lost = ket_qua_home<ket_qua_away?1:0;
                int draw = ket_qua_home==ket_qua_away?1:0;
                int point = 0;
                if (ket_qua_home > ket_qua_away){
                    point = 3;
                }else if (ket_qua_home == ket_qua_away) {
                    point = 1;
                }
                int hieu_so = ket_qua_home-ket_qua_away;

                // cap nhat cho home
                String home_name = score.getHome_name();

                if (mergeData.containsKey(home_name)) {
                    CountryScore home_country_score = mergeData.get(home_name);

                    home_country_score.setBT(home_country_score.getBT() + ket_qua_home);
                    home_country_score.setBB(home_country_score.getBB() + ket_qua_away);
                    home_country_score.setWin(home_country_score.getWin() + win);
                    home_country_score.setLost(home_country_score.getLost() + lost);
                    home_country_score.setDraw(home_country_score.getDraw() + lost);
                    home_country_score.setPOINT(home_country_score.getDraw() + point);
                    home_country_score.setHIEU_SO(home_country_score.getHIEU_SO() + hieu_so);

                } else {
//                    CountryScore home_country_score = new CountryScore();

//                    mergeData.put(home_name, home_country_score);
                }

                // cap nhat cho away


            }
        }
        return result;
    }

}
