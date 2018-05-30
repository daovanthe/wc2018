package th.wc2018.api.apiImp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import th.wc2018.api.API;
import wc2018.data.raw.Fixtures;

public class FixturesAPI extends API {

    private static final String LINK = "http://livescore-api.com/api-client/fixtures/matches.json?";


    @Override
    public void getObject() {
        Log.d(TAG, "FixturesAPI -> getObject()");
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
        //TO-DO
        String json = ClientBuilder.newClient().target(command).request().accept(MediaType.APPLICATION_JSON).get(String.class);
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(json).getAsJsonObject();
        JsonArray subArgs = obj.get("data").getAsJsonObject()
                .get("fixtures").getAsJsonArray();

        String jsonArrayFixtures = subArgs.getAsString();
        Fixtures[] fixtures = gson.fromJson(jsonArrayFixtures, Fixtures[].class);
        // add to database

    }
}
