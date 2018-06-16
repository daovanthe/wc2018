package th.wc2018.api;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import data.firebase.DataFireBase;

public abstract class API {
    private String key = "vA4yJ0A9MNe0bu5b", secrete = "xDeXXO0pK2rCmhDSSIqCI1LuO0vZuBb2";
    private String[] leagueIDs = {"793", "794", "795", "796", "797", "798", "799", "800"};
    public static String TAG = "THE.DV";

    public String getKey() {
        return key;
    }

    public API() {
        DataFireBase dataFireBase = DataFireBase.getInstance();
        this.key = dataFireBase.getKey();
        this.secrete = dataFireBase.getSecret();
        this.leagueIDs = dataFireBase.getLegue1();
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

    protected abstract void getObject();

    public void loadObjectFromIntenet() {
        new Thread(() -> {
            getObject();
        }).start();
    }

    public String getJsonStringFromLinkAPI(String httpLink) {
        Log.e("THE_DV", "api load data from internet on background: " + httpLink);
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
        mOnLoadApiComletedListener.loadApiCompleted(result);

        return result;
    }


    protected OnLoadApiCompletedListener mOnLoadApiComletedListener;

    public void AddOnLoadApiCOmpleteListener(OnLoadApiCompletedListener pOnLoadApiComletedListener) {
        mOnLoadApiComletedListener = pOnLoadApiComletedListener;
    }

    class GetObjectFromInternetThread extends Thread {
        public void run() {
            getObject();
        }
    }


}
