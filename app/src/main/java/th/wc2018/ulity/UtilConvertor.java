package th.wc2018.ulity;

import com.google.protobuf.Enum;

import java.util.HashMap;

import th.wc2018.R;

public class UtilConvertor {

    public static HashMap<String, Integer> hashFlag = new HashMap<String, Integer>();
    public static HashMap<String, String> hashGroup = new HashMap<String, String>();
    public static HashMap<String, Integer> hashEvent = new HashMap<String, Integer>();
    public static HashMap<String, String[]> hashGroupCountry = new HashMap<String, String[]>();

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
        hashEvent.put("GOAL", R.drawable.ic_goal);
        hashEvent.put("GOAL_PENALTY", R.drawable.ic_penalty);
        hashEvent.put("OWN_GOAL", R.drawable.ic_own_goal);
        hashEvent.put("YELLOW_CARD", R.drawable.ic_yellow_card);
        hashEvent.put("RED_CARD", R.drawable.ic_red_card);
        hashEvent.put("YELLOW_RED_CARD", R.drawable.ic_yellow_red_card);
    }

    public static class COUNTRY {
        public  static String RUSSIA = "Russia";
        public  static String SAUDI_ARABIA = "Saudi Arabia";
        public  static String EGYPT = "Egypt";
        public  static String URUGUAY = "Uruguay";
        public  static String PORTUGAL = "Portugal";
        public  static String SPAIN = "Spain";
        public  static String MOROCCO = "Morroco";
        public  static String IR_IRAN = "Iran";
        public  static String FRANCE = "France";
        public  static String AUSTRALIA = "Australia";
        public  static String PERU = "Peru";
        public  static String DENMARK = "Denmark";
        public  static String ARGENTINA = "Argentina";
        public  static String ICELAND = "Iceland";
        public  static String CROATIA = "Croatia";
        public  static String NIGERIA = "Nigeria";
        public  static String BRAZIL = "Brazil";
        public  static String SWITZERLAND = "Switzerland";
        public  static String COSTA_RICA = "Costa Rica";
        public  static String SERBIA = "Serbia";
        public  static String GERMANY = "Germany";
        public  static String MEXICO = "Mexico";
        public  static String SWEDEN = "Sweden";
        public  static String KOREA_REPUBLIC = "South Korea";
        public  static String BELGIUM = "Belgium";
        public  static String PANAMA = "Panama";
        public  static String TUNISIA = "Tunisia";
        public  static String ENGLAND = "England";
        public  static String POLAND = "Poland";
        public  static String SENEGAL = "Senegal";
        public  static String COLOMBIA = "Colombia";
        public  static String JAPAN = "Japan";
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
        hashGroupCountry.put("793", new String[]{COUNTRY.RUSSIA, COUNTRY.SAUDI_ARABIA, COUNTRY.EGYPT, COUNTRY.URUGUAY});
        hashGroupCountry.put("794", new String[]{COUNTRY.PORTUGAL, COUNTRY.SPAIN, COUNTRY.MOROCCO, COUNTRY.IR_IRAN});
        hashGroupCountry.put("795", new String[]{COUNTRY.FRANCE, COUNTRY.AUSTRALIA, COUNTRY.PERU, COUNTRY.DENMARK});
        hashGroupCountry.put("796", new String[]{COUNTRY.ARGENTINA, COUNTRY.ICELAND, COUNTRY.CROATIA, COUNTRY.NIGERIA,});
        hashGroupCountry.put("797", new String[]{COUNTRY.BRAZIL, COUNTRY.SWITZERLAND, COUNTRY.COSTA_RICA, COUNTRY.SERBIA,});
        hashGroupCountry.put("798", new String[]{COUNTRY.GERMANY, COUNTRY.MEXICO, COUNTRY.SWEDEN, COUNTRY.KOREA_REPUBLIC,});
        hashGroupCountry.put("799", new String[]{COUNTRY.BELGIUM, COUNTRY.PANAMA, COUNTRY.TUNISIA, COUNTRY.ENGLAND,});
        hashGroupCountry.put("800", new String[]{COUNTRY.POLAND, COUNTRY.SENEGAL, COUNTRY.COLOMBIA, COUNTRY.JAPAN});
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
