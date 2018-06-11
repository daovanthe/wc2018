package th.wc2018.ulity;

import com.google.protobuf.Enum;

import java.util.HashMap;

import th.wc2018.R;

public class UtilConvertor {

    public static HashMap<String, Integer> hashFlag = new HashMap<String, Integer>();
    public static HashMap<String, String> hashGroup = new HashMap<String, String>();
    public static HashMap<String, Integer> hashEvent = new HashMap<String, Integer>();
    public static HashMap<String, COUNTRY[]> hashGroupCountry = new HashMap<String, COUNTRY[]>();

    static {
        hashFlag.put("Egypt", R.drawable.egypt);
        hashFlag.put("Morroco", R.drawable.morocco);
        hashFlag.put("Nigeria", R.drawable.nigeria);
        hashFlag.put("Senegal", R.drawable.senegal);
        hashFlag.put("Tunisia", R.drawable.tunisia);
        hashFlag.put("Australia", R.drawable.australia);
        hashFlag.put("Iran", R.drawable.iran);
        hashFlag.put("Japan", R.drawable.japan);
        hashFlag.put("South Korea", R.drawable.south_korea); //Korea Republic
        hashFlag.put("Saudi Arabia", R.drawable.saudi_arabia);
        hashFlag.put("Belgium", R.drawable.belgium);
        hashFlag.put("Croatia", R.drawable.croatia);
        hashFlag.put("Denmark", R.drawable.denmark);
        hashFlag.put("England", R.drawable.england);
        hashFlag.put("France", R.drawable.france);
        hashFlag.put("Germany", R.drawable.germany);
        hashFlag.put("Iceland", R.drawable.iceland);
        hashFlag.put("Poland", R.drawable.republic_of_poland);
        hashFlag.put("Portugal", R.drawable.portugal);
        hashFlag.put("Russia", R.drawable.russia);
        hashFlag.put("Serbia", R.drawable.serbia);
        hashFlag.put("Spain", R.drawable.spain);
        hashFlag.put("Sweden", R.drawable.sweden);
        hashFlag.put("Switzerland", R.drawable.switzerland);
        hashFlag.put("Costa Rica", R.drawable.costa_rica);
        hashFlag.put("Mexico", R.drawable.mexico);
        hashFlag.put("Panama", R.drawable.panama);
        hashFlag.put("Argentina", R.drawable.argentina);
        hashFlag.put("Brazil", R.drawable.brazil);
        hashFlag.put("Colombia", R.drawable.colombia);
        hashFlag.put("Peru", R.drawable.peru);
        hashFlag.put("Uruguay", R.drawable.uruguay);
    }

    static {
        hashGroup.put("793", "Group A");
        hashGroup.put("794", "Group B");
        hashGroup.put("795", "Group C");
        hashGroup.put("796", "Group D");
        hashGroup.put("797", "Group E");
        hashGroup.put("798", "Group F");
        hashGroup.put("799", "Group G");
        hashGroup.put("800", "Group H");
    }

    static {
        hashEvent.put("GOAL", R.drawable.ic_goal);
        hashEvent.put("GOAL_PENALTY", R.drawable.ic_penalty);
        hashEvent.put("OWN_GOAL", R.drawable.ic_own_goal);
        hashEvent.put("YELLOW_CARD", R.drawable.ic_yellow_card);
        hashEvent.put("RED_CARD", R.drawable.ic_red_card);
        hashEvent.put("YELLOW_RED_CARD", R.drawable.ic_yellow_red_card);
    }

    public static enum COUNTRY {
        RUSSIA, SAUDI_ARABIA, EGYPT, URUGUAY,
        PORTUGAL, SPAIN, MOROCCO, IR_IRAN,
        FRANCE, AUSTRALIA, PERU, DENMARK,
        ARGENTINA, ICELAND, CROATIA, NIGERIA,
        BRAZIL, SWITZERLAND, COSTA_RICA, SERBIA,
        GERMANY, MEXICO, SWEDEN, KOREA_REPUBLIC,
        BELGIUM, PANAMA, TUNISIA, ENGLAND,
        POLAND, SENEGAL, COLOMBIA, JAPAN
    }


    static {
        hashGroupCountry.put("793", new COUNTRY[]{COUNTRY.RUSSIA, COUNTRY.SAUDI_ARABIA, COUNTRY.EGYPT, COUNTRY.URUGUAY});
        hashGroupCountry.put("794", new COUNTRY[]{COUNTRY.PORTUGAL, COUNTRY.SPAIN, COUNTRY.MOROCCO, COUNTRY.IR_IRAN});
        hashGroupCountry.put("795", new COUNTRY[]{COUNTRY.FRANCE, COUNTRY.AUSTRALIA, COUNTRY.PERU, COUNTRY.DENMARK});
        hashGroupCountry.put("796", new COUNTRY[]{COUNTRY.ARGENTINA, COUNTRY.ICELAND, COUNTRY.CROATIA, COUNTRY.NIGERIA,});
        hashGroupCountry.put("797", new COUNTRY[]{COUNTRY.BRAZIL, COUNTRY.SWITZERLAND, COUNTRY.COSTA_RICA, COUNTRY.SERBIA,});
        hashGroupCountry.put("798", new COUNTRY[]{COUNTRY.GERMANY, COUNTRY.MEXICO, COUNTRY.SWEDEN, COUNTRY.KOREA_REPUBLIC,});
        hashGroupCountry.put("799", new COUNTRY[]{COUNTRY.BELGIUM, COUNTRY.PANAMA, COUNTRY.TUNISIA, COUNTRY.ENGLAND,});
        hashGroupCountry.put("800", new COUNTRY[]{COUNTRY.POLAND, COUNTRY.SENEGAL, COUNTRY.COLOMBIA, COUNTRY.JAPAN});
    }


    public static String convertLeadeToGroup(String country_id) {

        if (hashGroup.get(country_id) == null) {
            return "Group W";
        }
        return hashGroup.get(country_id);
    }

    public static int convertFlagCountry(String country_id) {

        if (hashFlag.get(country_id) == null) {
            return 0;
        }
        return hashFlag.get(country_id);
    }

    public static int convertEventStringToImg(String event) {
        if (hashEvent.get(event) == null) {
            return R.drawable.ic_own_goal;
        }
        return hashEvent.get(event);
    }
}
