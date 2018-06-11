package th.wc2018.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import data.raw.CountryScore;
import data.raw.GroupScore;
import th.wc2018.R;
import th.wc2018.ulity.UtilConvertor;

public class GroupScoreAdapter extends ArrayAdapter {
    public GroupScoreAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        GroupScore groupScore = (GroupScore) getItem(position);
        List<CountryScore> countryScore = groupScore.getCountryScoreList();


        if (v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.group_item_layout_list_item, null);
        }

        TextView groupName = (TextView) v.findViewById(R.id.group_name);
        groupName.setText(groupScore.getNameGroup());

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout relativeLayout = v.findViewById(R.id.detail_data);
        /// generate the score data

        relativeLayout.removeAllViews();

        int ID_temp = 0;
        if (countryScore.size() > 0) {
            CountryScore currentCountryScore1 = countryScore.get(0);
            // region update line 1
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout line1 = new LinearLayout(getContext());
            line1.setId(View.generateViewId());
            line1.setPadding((int) getContext().getResources().getDimension(R.dimen.sixteen),
                    (int) getContext().getResources().getDimension(R.dimen.eight),
                    (int) getContext().getResources().getDimension(R.dimen.sixteen),
                    (int) getContext().getResources().getDimension(R.dimen.eight));
            line1.setOrientation(LinearLayout.HORIZONTAL);
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, line1.getId());
            line1.setLayoutParams(params);


                LinearLayout.LayoutParams params_image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.09f);
                LinearLayout country_linear_image = new LinearLayout(getContext());
                country_linear_image.setOrientation(LinearLayout.VERTICAL);
                country_linear_image.setLayoutParams(params_image);
                line1.addView(country_linear_image);

                    LinearLayout.LayoutParams imageLayourParams = new LinearLayout.LayoutParams((int) getContext().getResources().getDimension(R.dimen.twentyfour), (int) getContext().getResources().getDimension(R.dimen.twentyfour));
                    ImageView image = new ImageView(getContext());
                    image.setImageResource(UtilConvertor.convertFlagCountry(currentCountryScore1.getCountry()));
                    image.setLayoutParams(imageLayourParams);
                    country_linear_image.addView(image);


                LinearLayout.LayoutParams param_match = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
                LinearLayout country_linear_match = new LinearLayout(getContext());
                country_linear_match.setOrientation(LinearLayout.VERTICAL);
                country_linear_match.setLayoutParams(param_match);
                line1.addView(country_linear_match);

                    LinearLayout.LayoutParams matchLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    AppCompatTextView match_txt = new AppCompatTextView(getContext());
                    match_txt.setText(currentCountryScore1.getST());
                    match_txt.setLayoutParams(matchLayourParams);
                    country_linear_match.addView(match_txt);


                LinearLayout.LayoutParams param_win = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
                LinearLayout country_linear_win = new LinearLayout(getContext());
                country_linear_win.setOrientation(LinearLayout.VERTICAL);
                country_linear_win.setLayoutParams(param_win);
                line1.addView(country_linear_win);

                    LinearLayout.LayoutParams wihLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    AppCompatTextView win_txt = new AppCompatTextView(getContext());
                    win_txt.setText(currentCountryScore1.getWin());
                    win_txt.setLayoutParams(wihLayourParams);
                    country_linear_win.addView(win_txt);


                LinearLayout.LayoutParams param_draw = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
                LinearLayout country_linear_draw = new LinearLayout(getContext());
                country_linear_draw.setOrientation(LinearLayout.VERTICAL);
                country_linear_draw.setLayoutParams(param_draw);
                line1.addView(country_linear_draw);

                    LinearLayout.LayoutParams drawLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    AppCompatTextView draw_txt = new AppCompatTextView(getContext());
                    draw_txt.setText(currentCountryScore1.getDraw());
                    draw_txt.setLayoutParams(drawLayourParams);
                    country_linear_draw.addView(draw_txt);


                LinearLayout.LayoutParams param_lose = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
                LinearLayout country_linear_lose = new LinearLayout(getContext());
                country_linear_lose.setOrientation(LinearLayout.VERTICAL);
                country_linear_lose.setLayoutParams(param_lose);
                line1.addView(country_linear_lose);

                    LinearLayout.LayoutParams loseLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    AppCompatTextView lose_txt = new AppCompatTextView(getContext());
                    lose_txt.setText(currentCountryScore1.getLost());
                    lose_txt.setLayoutParams(loseLayourParams);
                    country_linear_lose.addView(lose_txt);


                LinearLayout.LayoutParams param_bt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
                LinearLayout country_linear_bt = new LinearLayout(getContext());
                country_linear_bt.setOrientation(LinearLayout.VERTICAL);
                country_linear_bt.setLayoutParams(param_bt);
                line1.addView(country_linear_bt);

                    LinearLayout.LayoutParams btLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    AppCompatTextView bt_txt = new AppCompatTextView(getContext());
                    bt_txt.setText(currentCountryScore1.getBT());
                    bt_txt.setLayoutParams(btLayourParams);
                    country_linear_bt.addView(bt_txt);

                LinearLayout.LayoutParams param_bb = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
                LinearLayout country_linear_bb = new LinearLayout(getContext());
                country_linear_bb.setOrientation(LinearLayout.VERTICAL);
                country_linear_bb.setLayoutParams(param_bb);
                line1.addView(country_linear_bb);

                    LinearLayout.LayoutParams bbLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    AppCompatTextView bb_txt = new AppCompatTextView(getContext());
                    bb_txt.setText(currentCountryScore1.getBB());
                    bb_txt.setLayoutParams(bbLayourParams);
                    country_linear_bt.addView(bb_txt);

                LinearLayout.LayoutParams param_hs = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
                LinearLayout country_linear_hs = new LinearLayout(getContext());
                country_linear_hs.setOrientation(LinearLayout.VERTICAL);
                country_linear_hs.setLayoutParams(param_hs);
                line1.addView(country_linear_bb);

                    LinearLayout.LayoutParams hsLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    AppCompatTextView hs_txt = new AppCompatTextView(getContext());
                    hs_txt.setText(currentCountryScore1.getHIEU_SO());
                    hs_txt.setLayoutParams(hsLayourParams);
                    country_linear_bt.addView(hs_txt);


                LinearLayout.LayoutParams param_p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
                LinearLayout country_linear_p = new LinearLayout(getContext());
                country_linear_p.setOrientation(LinearLayout.VERTICAL);
                country_linear_p.setLayoutParams(param_p);
                line1.addView(country_linear_bb);

                    LinearLayout.LayoutParams pLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    AppCompatTextView p_txt = new AppCompatTextView(getContext());
                    p_txt.setText(currentCountryScore1.getHIEU_SO());
                    p_txt.setLayoutParams(pLayourParams);
                    country_linear_bt.addView(p_txt);
            // endregion
            ID_temp = line1.getId();
            relativeLayout.addView(line1);
        }
        //

        for (int i = 1; i < countryScore.size(); i++) {
            CountryScore currentCountryScore1 = countryScore.get(i);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            // region update line 1
            LinearLayout line1 = new LinearLayout(getContext());
            line1.setId(View.generateViewId());
            line1.setPadding((int) getContext().getResources().getDimension(R.dimen.sixteen),
                    (int) getContext().getResources().getDimension(R.dimen.eight),
                    (int) getContext().getResources().getDimension(R.dimen.sixteen),
                    (int) getContext().getResources().getDimension(R.dimen.eight));
            line1.setOrientation(LinearLayout.HORIZONTAL);
            params.addRule(RelativeLayout.BELOW, ID_temp);
            line1.setLayoutParams(params);
            relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, line1.getId());

            LinearLayout.LayoutParams params_image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.09f);
            LinearLayout country_linear_image = new LinearLayout(getContext());
            country_linear_image.setOrientation(LinearLayout.VERTICAL);
            country_linear_image.setLayoutParams(params_image);
            line1.addView(country_linear_image);

            LinearLayout.LayoutParams imageLayourParams = new LinearLayout.LayoutParams((int) getContext().getResources().getDimension(R.dimen.twentyfour), (int) getContext().getResources().getDimension(R.dimen.twentyfour));
            ImageView image = new ImageView(getContext());
            image.setImageResource(UtilConvertor.convertFlagCountry(currentCountryScore1.getCountry()));
            image.setLayoutParams(imageLayourParams);
            country_linear_image.addView(image);


            LinearLayout.LayoutParams param_match = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
            LinearLayout country_linear_match = new LinearLayout(getContext());
            country_linear_match.setOrientation(LinearLayout.VERTICAL);
            country_linear_match.setLayoutParams(param_match);
            line1.addView(country_linear_match);

            LinearLayout.LayoutParams matchLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            AppCompatTextView match_txt = new AppCompatTextView(getContext());
            match_txt.setText(currentCountryScore1.getST());
            match_txt.setLayoutParams(matchLayourParams);
            country_linear_match.addView(match_txt);


            LinearLayout.LayoutParams param_win = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
            LinearLayout country_linear_win = new LinearLayout(getContext());
            country_linear_win.setOrientation(LinearLayout.VERTICAL);
            country_linear_win.setLayoutParams(param_win);
            line1.addView(country_linear_win);

            LinearLayout.LayoutParams wihLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            AppCompatTextView win_txt = new AppCompatTextView(getContext());
            win_txt.setText(currentCountryScore1.getWin());
            win_txt.setLayoutParams(wihLayourParams);
            country_linear_win.addView(win_txt);


            LinearLayout.LayoutParams param_draw = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
            LinearLayout country_linear_draw = new LinearLayout(getContext());
            country_linear_draw.setOrientation(LinearLayout.VERTICAL);
            country_linear_draw.setLayoutParams(param_draw);
            line1.addView(country_linear_draw);

            LinearLayout.LayoutParams drawLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            AppCompatTextView draw_txt = new AppCompatTextView(getContext());
            draw_txt.setText(currentCountryScore1.getDraw());
            draw_txt.setLayoutParams(drawLayourParams);
            country_linear_draw.addView(draw_txt);


            LinearLayout.LayoutParams param_lose = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
            LinearLayout country_linear_lose = new LinearLayout(getContext());
            country_linear_lose.setOrientation(LinearLayout.VERTICAL);
            country_linear_lose.setLayoutParams(param_lose);
            line1.addView(country_linear_lose);

            LinearLayout.LayoutParams loseLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            AppCompatTextView lose_txt = new AppCompatTextView(getContext());
            lose_txt.setText(currentCountryScore1.getLost());
            lose_txt.setLayoutParams(loseLayourParams);
            country_linear_lose.addView(lose_txt);


            LinearLayout.LayoutParams param_bt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
            LinearLayout country_linear_bt = new LinearLayout(getContext());
            country_linear_bt.setOrientation(LinearLayout.VERTICAL);
            country_linear_bt.setLayoutParams(param_bt);
            line1.addView(country_linear_bt);

            LinearLayout.LayoutParams btLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            AppCompatTextView bt_txt = new AppCompatTextView(getContext());
            bt_txt.setText(currentCountryScore1.getBT());
            bt_txt.setLayoutParams(btLayourParams);
            country_linear_bt.addView(bt_txt);

            LinearLayout.LayoutParams param_bb = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
            LinearLayout country_linear_bb = new LinearLayout(getContext());
            country_linear_bb.setOrientation(LinearLayout.VERTICAL);
            country_linear_bb.setLayoutParams(param_bb);
            line1.addView(country_linear_bb);

            LinearLayout.LayoutParams bbLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            AppCompatTextView bb_txt = new AppCompatTextView(getContext());
            bb_txt.setText(currentCountryScore1.getBB());
            bb_txt.setLayoutParams(bbLayourParams);
            country_linear_bt.addView(bb_txt);

            LinearLayout.LayoutParams param_hs = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
            LinearLayout country_linear_hs = new LinearLayout(getContext());
            country_linear_hs.setOrientation(LinearLayout.VERTICAL);
            country_linear_hs.setLayoutParams(param_hs);
            line1.addView(country_linear_bb);

            LinearLayout.LayoutParams hsLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            AppCompatTextView hs_txt = new AppCompatTextView(getContext());
            hs_txt.setText(currentCountryScore1.getHIEU_SO());
            hs_txt.setLayoutParams(hsLayourParams);
            country_linear_bt.addView(hs_txt);


            LinearLayout.LayoutParams param_p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);
            LinearLayout country_linear_p = new LinearLayout(getContext());
            country_linear_p.setOrientation(LinearLayout.VERTICAL);
            country_linear_p.setLayoutParams(param_p);
            line1.addView(country_linear_bb);

            LinearLayout.LayoutParams pLayourParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            AppCompatTextView p_txt = new AppCompatTextView(getContext());
            p_txt.setText(currentCountryScore1.getHIEU_SO());
            p_txt.setLayoutParams(pLayourParams);
            country_linear_bt.addView(p_txt);
            // endregion
            ID_temp = line1.getId();
            relativeLayout.addView(line1);
        }


        return v;
    }
}
