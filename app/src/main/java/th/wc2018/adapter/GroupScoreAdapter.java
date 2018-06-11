package th.wc2018.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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


        int ID_temp = 0;
        if (countryScore.size() > 0) {
            CountryScore currentCountryScore1 = countryScore.get(0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout line1 = new LinearLayout(getContext());
            line1.setId(View.generateViewId());
            line1.setPadding((int) getContext().getResources().getDimension(R.dimen.sixteen),
                    (int) getContext().getResources().getDimension(R.dimen.eight),
                    (int) getContext().getResources().getDimension(R.dimen.sixteen),
                    (int) getContext().getResources().getDimension(R.dimen.eight));
            line1.setOrientation(LinearLayout.HORIZONTAL);
            line1.setLayoutParams(params);
            relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, line1.getId());

                LinearLayout.LayoutParams params_image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                LinearLayout country_linear_image = new LinearLayout(getContext());
                country_linear_image.setOrientation(LinearLayout.VERTICAL);
                country_linear_image.setLayoutParams(params_image);
                line1.addView(country_linear_image);


                    ImageView image = new ImageView(getContext());
                    image.setImageResource(UtilConvertor.convertFlagCountry(currentCountryScore1.get));


            ID_temp = line1.getId();
        }
        //


        return v;
    }
}
