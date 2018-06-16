package th.wc2018.api.apiImp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import data.raw.Events;
import data.raw.History;
import th.wc2018.api.API;

public class HistoryAPI extends API {

    private static final String LINK = "http://livescore-api.com/api-client/scores/history.json?";


    public HistoryAPI() {
        super();
    }


    @Override
    protected void getObject() {
        Log.d(TAG, "LiveScoreApi -> getObject()");
        for (String legueID : getLeagueIDs()) {
            // command to load OBject from API
            StringBuilder apiCommand = new StringBuilder(LINK);
            apiCommand.append("key=" + getKey());
            apiCommand.append("&secret=" + getSecrete());
            apiCommand.append("&league=" + legueID);
            // extract Object
            //TO-DO
            loadApiToObject(apiCommand.toString());
        }

        // loadApiToObject("");
    }

    private Gson gson = new Gson();

    private void loadApiToObject(String link) {
        String scoreData = getJsonStringFromLinkAPI(link);
//        String scoreData = "{ \"success\": true, \"data\": { \"match\": [ " +
//                "{ \"id\": \"14\", \"home_name\": \"Russia\", \"away_name\": \"Japan\", \"score\": \"0 - 0\", \"ht_score\": \"0 - 0\", \"ft_score\": \"\", \"et_score\": \"\", \"time\": \"36\", \"league_id\": \"793\", \"status\": \"IN PLAY\", \"added\": \"2017-11-16 00:40:21\", \"last_changed\": \"2017-11-16 00:40:21\", \"home_id\": \"0\", \"away_id\": \"188\", \"events\": true, \"league_name\": \"Liga Nacional:: apertura\" }, " +
//                "{ \"id\": \"15\", \"home_name\": \"Germany\", \"away_name\": \"Japan\", \"score\": \"1 - 0\", \"ht_score\": \"0 - 0\", \"ft_score\": \"\", \"et_score\": \"\", \"time\": \"36\", \"league_id\": \"793\", \"status\": \"IN PLAY\", \"added\": \"2017-11-16 00:40:21\", \"last_changed\": \"2017-11-16 00:40:21\", \"home_id\": \"0\", \"away_id\": \"188\", \"events\": true, \"league_name\": \"Liga Nacional:: apertura\" }, " +
//                "{ \"id\": \"16\", \"home_name\": \"Russia\", \"away_name\": \"Japan\", \"score\": \"0 - 0\", \"ht_score\": \"0 - 0\", \"ft_score\": \"\", \"et_score\": \"\", \"time\": \"36\", \"league_id\": \"793\", \"status\": \"IN PLAY\", \"added\": \"2017-11-16 00:40:21\", \"last_changed\": \"2017-11-16 00:40:21\", \"home_id\": \"0\", \"away_id\": \"188\", \"events\": true, \"league_name\": \"Liga Nacional:: apertura\" }, " +
//                "{ \"id\": \"17\", \"home_name\": \"Spain\", \"away_name\": \"England\", \"score\": \"1 - 0\", \"ht_score\": \"0 - 0\", \"ft_score\": \"\", \"et_score\": \"\", \"time\": \"36\", \"league_id\": \"793\", \"status\": \"IN PLAY\", \"added\": \"2017-11-16 00:40:21\", \"last_changed\": \"2017-11-16 00:40:21\", \"home_id\": \"0\", \"away_id\": \"188\", \"events\": true, \"league_name\": \"Liga Nacional:: apertura\" }, " +
//                "{ \"id\": \"18\", \"home_name\": \"Spain\", \"away_name\": \"Australia\", \"score\": \"2 - 1\", \"ht_score\": \"\", \"ft_score\": \"\", \"et_score\": \"\", \"time\": \"23:45\", \"league_id\": \"793\", \"status\": \"NOT STARTED\", \"added\": \"2017-11-16 00:40:21\", \"last_changed\": \"2017-11-16 00:40:21\", \"home_id\": \"265\", \"away_id\": \"269\", \"events\": \"http://livescore-api.com/api-client/scores/events.json?key=vA4yJ0A9MNe0bu5b&secret=xDeXXO0pK2rCmhDSSIqCI1LuO0vZuBb2&id=9\", \"league_name\": \"Serie A\" " +
//                "} ] } }";

        //TO-DO
//  String json = ClientBuilder.newClient().target(command).request().accept(MediaType.APPLICATION_JSON).get(String.class);
//        String jsonArrayFixtures = subArgs.getAsString();
        try {
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(scoreData).getAsJsonObject();
            JsonArray subArgs = obj.get("data").getAsJsonObject()
                    .get("match").getAsJsonArray();
            History[] liveLiveScore = gson.fromJson(subArgs.toString(), History[].class);
            // add to database
//            for (History match : liveLiveScore) {
//                    String Event = match.getEvents();
//                    String eventDataString = getJsonStringFromLinkAPI(Event);
//                    // we will replace the json object to testing
////                    String eventDataString = "{ \"success\": true, \"data\": { \"event\": [ { \"id\": \"1\", \"match_id\": \"14\", \"player\": \"SAAVEDRA MATIAS\", \"time\": \"26\", \"event\": \"GOAL\", \"sort\": \"0\", \"home_away\": \"a\" }, { \"id\": \"2\", \"match_id\": \"14\", \"player\": \"MARTINEZ WILLIAMS\", \"time\": \"29\", \"event\": \"GOAL\", \"sort\": \"1\", \"home_away\": \"a\" }, { \"id\": \"3\", \"match_id\": \"14\", \"player\": \"SAAVEDRA MATIAS\", \"time\": \"59\", \"event\": \"GOAL\", \"sort\": \"2\", \"home_away\": \"a\" }, { \"id\": \"4\", \"match_id\": \"14\", \"player\": \"LOPEZ RENZO\", \"time\": \"91\", \"event\": \"GOAL\", \"sort\": \"3\", \"home_away\": \"h\" }, { \"id\": \"5\", \"match_id\": \"14\", \"player\": \"LOPEZ RENZO\", \"time\": \"94\", \"event\": \"GOAL_PENALTY\", \"sort\": \"4\", \"home_away\": \"h\" } ] } }";
//
//                    JsonObject objEvent = parser.parse(scoreData).getAsJsonObject();
//                    obj = parser.parse(eventDataString).getAsJsonObject();
//                    JsonArray subArgsEvent = obj.get("data").getAsJsonObject()
//                            .get("event").getAsJsonArray();
//                    Events[] liveScoreEvents = gson.fromJson(subArgsEvent.toString(), Events[].class);
//                    // update league id for each event
//                    for (Events eventLiveScore : liveScoreEvents) {
//                        eventLiveScore.setLeague_id(match.getLeague_id());
//                        if (eventLiveScore.getHome_away().equals("a")) {
//                            eventLiveScore.setHome_away_name(match.getAway_name());
//                        } else if (eventLiveScore.getHome_away().equals("h")) {
//                            eventLiveScore.setHome_away_name(match.getHome_name());
//                        }
//                    }
//                    if (mILoadLiveScoreEvent != null)
//                        mILoadLiveScoreEvent.loadEventCompleted(liveScoreEvents);
//            }
            if (mOnLoadApiComletedListener != null) {
                mOnLoadApiComletedListener.loadApiCompleted(liveLiveScore);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
