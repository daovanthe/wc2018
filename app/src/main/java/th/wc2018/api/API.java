package th.wc2018.api;

public abstract class API {
    private String key, secrete;
    private String[] leagueIDs;
    public static String TAG = "THE.DV";
    public String getKey() {
        return key;
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
}
