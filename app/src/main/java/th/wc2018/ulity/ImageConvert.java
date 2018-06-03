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


    public static int convertString(String country_id) {

        if (hashFlag.get(country_id) == null) {
            return 0;
        }
        return hashFlag.get(country_id);
    }
}
