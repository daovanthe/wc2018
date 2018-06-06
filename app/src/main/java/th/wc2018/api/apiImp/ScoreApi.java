package th.wc2018.api.apiImp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import data.raw.Score;
import th.wc2018.api.API;

public class ScoreApi extends API {

    private static final String LINK = "http://livescore-api.com/api-client/scores/live.json?";

    public ScoreApi(String key, String secret, String[] legue1) {
        super(key, secret, legue1);
    }

    public ScoreApi() {

    }


    @Override
    public void getObject() {
//        Log.d(TAG, "ScoreApi -> getObject()");
//        for (String legueID : getLeagueIDs()) {
//            // command to load OBject from API
//            StringBuilder apiCommand = new StringBuilder(LINK);
//            apiCommand.append("key=" + getKey());
//            apiCommand.append("&secret=" + getSecrete());
//            apiCommand.append("&league=" + legueID);
//            // extract Object
//            //TO-DO
//            loadApiToObject(apiCommand.toString());
//        }

           loadApiToObject("");
    }

    private Gson gson = new Gson();

    private void loadApiToObject(String link) {
//        String data = getJsonString(link);
        String data  = "{ \"success\": true, \"data\": { \"match\": [ { \"id\": \"14\", \"home_name\": \"CSD Comunicaciones\", \"away_name\": \"Coban Imperial\", \"score\": \"0 - 0\", \"ht_score\": \"0 - 0\", \"ft_score\": \"\", \"et_score\": \"\", \"time\": \"36\", \"league_id\": \"90\", \"status\": \"IN PLAY\", \"added\": \"2017-11-16 00:40:21\", \"last_changed\": \"2017-11-16 00:40:21\", \"home_id\": \"0\", \"away_id\": \"188\", \"events\": false, \"league_name\": \"Liga Nacional:: apertura\" }, { \"id\": \"9\", \"home_name\": \"Vasco da Gama\", \"away_name\": \"Atletico MG\", \"score\": \"? - ?\", \"ht_score\": \"\", \"ft_score\": \"\", \"et_score\": \"\", \"time\": \"23:45\", \"league_id\": \"21\", \"status\": \"NOT STARTED\", \"added\": \"2017-11-16 00:40:21\", \"last_changed\": \"2017-11-16 00:40:21\", \"home_id\": \"265\", \"away_id\": \"269\", \"events\": \"http://livescore-api.com/api-client/scores/events.json?key=vA4yJ0A9MNe0bu5b&secret=xDeXXO0pK2rCmhDSSIqCI1LuO0vZuBb2&id=9\", \"league_name\": \"Serie A\" } ] } }";


        //TO-DO
//  String json = ClientBuilder.newClient().target(command).request().accept(MediaType.APPLICATION_JSON).get(String.class);
//        String jsonArrayFixtures = subArgs.getAsString();
        try {
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(data).getAsJsonObject();
            JsonArray subArgs = obj.get("data").getAsJsonObject()
                    .get("match").getAsJsonArray();

            Score[] liveScore = gson.fromJson(subArgs.toString(), Score[].class);
            // add to database
            if (mOnLoadApiComletedListener != null) {
                mOnLoadApiComletedListener.loadApiCompleted(liveScore);
            }
        } catch (Exception e) {

        }
    }

}
