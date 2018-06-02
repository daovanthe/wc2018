package th.wc2018.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public abstract class API {
    private String key = "vA4yJ0A9MNe0bu5b", secrete = "xDeXXO0pK2rCmhDSSIqCI1LuO0vZuBb2";
    private String[] leagueIDs = {"793", "794", "795", "796", "797", "798", "799", "800"};
    public static String TAG = "THE.DV";

    public String getKey() {
        return key;
    }

    public API() {

    }

    public API(String key, String secret, String[] legue1) {
        this.key = key;
        this.secrete = secrete;
        this.leagueIDs = leagueIDs;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecrete() {
        return secrete;
    }

    public void setSecrete(String secrete) {
        this.secrete = secrete;
    }

    public String[] getLeagueIDs() {
        return leagueIDs;
    }

    public void setLeagueIDs(String[] leagueIDs) {
        this.leagueIDs = leagueIDs;
    }

    public abstract void getObject();

    public String getJsonString(String httpLink) {
        URL oracle = null;
        try {
            oracle = new URL(httpLink);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection yc = null;
        try {
            yc = oracle.openConnection();
            yc.setConnectTimeout(1000);
        } catch (IOException e) {
            e.printStackTrace();

        }
        StringBuffer bufferReader = new StringBuffer();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
                bufferReader.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = bufferReader.toString();
        bufferReader.setLength(0);
        return result;
    }

    protected OnLoadApiCompletedListener mOnLoadApiComletedListener;

    public void AddOnLoadApiCOmpleteListener(OnLoadApiCompletedListener pOnLoadApiComletedListener) {
        mOnLoadApiComletedListener = pOnLoadApiComletedListener;
    }
}
