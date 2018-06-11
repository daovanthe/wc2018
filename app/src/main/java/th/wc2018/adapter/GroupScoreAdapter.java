package th.wc2018.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import data.raw.GroupScore;
import th.wc2018.R;

public class GroupScoreAdapter extends ArrayAdapter {
    public GroupScoreAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        GroupScore countryScore = (GroupScore) getItem(position);

        if (v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.group_item_layout_list_item, null);
        }

        TextView groupName = (TextView) v.findViewById(R.id.group_name);
        groupName.setText(countryScore.getNameGroup());

        RelativeLayout relativeLayout = v.findViewById(R.id.detail_data);
        /// generate the score data
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      // start line 1
       LinearLayout line1 = new LinearLayout(getContext());
        line1.setPadding((int) getContext().getResources().getDimension(R.dimen.sixteen),
                (int) getContext().getResources().getDimension(R.dimen.eight),
                (int) getContext().getResources().getDimension(R.dimen.sixteen),
                (int) getContext().getResources().getDimension(R.dimen.eight));
        line1.setOrientation(LinearLayout.HORIZONTAL);
        line1.setLayoutParams(params);



        //


        return v;
    }
}
