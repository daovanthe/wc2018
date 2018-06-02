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
        Log.d(TAG, "ScoreApi -> getObject()");
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
    }

    private Gson gson = new Gson();

    private void loadApiToObject(String command) {
        String data = getJsonString(command);
        //TO-DO
//        String json = ClientBuilder.newClient().target(command).request().accept(MediaType.APPLICATION_JSON).get(String.class);
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(data).getAsJsonObject();
        JsonArray subArgs = obj.get("data").getAsJsonObject()
                .get("match").getAsJsonArray();

//        String jsonArrayFixtures = subArgs.getAsString();
        Score[] fixtures = gson.fromJson(subArgs.toString(), Score[].class);
        // add to database
        if (mOnLoadApiComletedListener != null) {
            mOnLoadApiComletedListener.loadApiCompleted(fixtures);
        }
    }

}
