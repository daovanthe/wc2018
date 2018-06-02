package th.wc2018.ulity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import java.util.HashMap;

import th.wc2018.R;

public class ImageConvert {

    public static HashMap<String, Integer> hashFlag = new HashMap<String, Integer>();

    static {
        hashFlag.put("74", R.drawable.egypt);
        hashFlag.put("81", R.drawable.morocco);
        hashFlag.put("108", R.drawable.nigeria);
        hashFlag.put("103", R.drawable.senegal);
        hashFlag.put("80", R.drawable.tunisia);
        hashFlag.put("71", R.drawable.australia);
        hashFlag.put("18", R.drawable.iran);
        hashFlag.put("72", R.drawable.japan);
        hashFlag.put("70", R.drawable.south_korea);
        hashFlag.put("75", R.drawable.saudi_arabia);
        hashFlag.put("2", R.drawable.belgium);
        hashFlag.put("54", R.drawable.croatia);
        hashFlag.put("5", R.drawable.denmark);
        hashFlag.put("19", R.drawable.england);
        hashFlag.put("21", R.drawable.france);
        hashFlag.put("1", R.drawable.germany);
        hashFlag.put("33", R.drawable.iceland);
        hashFlag.put("14", R.drawable.republic_of_poland);
        hashFlag.put("32", R.drawable.portugal);
        hashFlag.put("12", R.drawable.russia);
        hashFlag.put("50", R.drawable.serbia);
        hashFlag.put("43", R.drawable.spain);
        hashFlag.put("7", R.drawable.sweden);
        hashFlag.put("9", R.drawable.switzerland);
        hashFlag.put("58", R.drawable.costa_rica);
        hashFlag.put("57", R.drawable.mexico);
        hashFlag.put("95", R.drawable.panama);
        hashFlag.put("65", R.drawable.argentina);
        hashFlag.put("16", R.drawable.brazil);
        hashFlag.put("61", R.drawable.colombia);
        hashFlag.put("52", R.drawable.peru);
        hashFlag.put("66", R.drawable.uruguay);

    }


    public static int convertString(int country_id) {
        return hashFlag.get(country_id + "");
    }
}
