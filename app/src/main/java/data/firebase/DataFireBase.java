package data.firebase;

public class DataFireBase {
    private String key, secret;
    private String[] legue1;

    public DataFireBase(String key, String secret, String[] legue1) {
        this.key = key;
        this.secret = secret;
        this.legue1 = legue1;
    }

    public String getKey() {
        return key;

    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String[] getLegue1() {
        return legue1;
    }

    public void setLegue1(String[] legue1) {
        this.legue1 = legue1;
    }
}
