package th.wc2018.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import data.raw.Fixtures;
import th.wc2018.R;
import th.wc2018.activity.MatchesActivity;
import th.wc2018.ulity.ImageConvert;

import java.util.List;

public class MatchAdapter extends ArrayAdapter {


    public MatchAdapter(Context context, int resource, Object[] items) {
        super(context, resource, items);
    }


    @Override
    public View getView(int position, View view, ViewGroup convertView) {

        View v = convertView;
        Object objectItem = getItem(position);

        if (objectItem != null) {
            if (objectItem instanceof String) {
                DateMatchViewHolder holder;
                if (v == null) {
                    LayoutInflater vi;
                    vi = LayoutInflater.from(getContext());
                    v = vi.inflate(R.layout.date_match_layout, null);
                    holder = new DateMatchViewHolder();
                    // map data
                    TextView lDateTextView = (TextView) v.findViewById(R.id.date_match);
                    holder.dateTextView = lDateTextView;
                    v.setTag(holder);

                } else {
                    holder = (DateMatchViewHolder) v.getTag();
                }
                // set data on View
                holder.dateTextView.setText((String) objectItem);

            } else if (objectItem instanceof Fixtures) {
                Fixtures match = (Fixtures) objectItem;
                SingleMatchViewHolder holder;
                if (v == null) {
                    LayoutInflater vi;
                    vi = LayoutInflater.from(getContext());
                    v = vi.inflate(R.layout.match_item_layout, null);
                    holder = new SingleMatchViewHolder();
                    // map data
                    ImageView homePicture = (ImageView) v.findViewById(R.id.home_pic);
                    ImageView awayPicture = (ImageView) v.findViewById(R.id.away_pic);

                    TextView homeName = (TextView) v.findViewById(R.id.home_name);
                    TextView awayName = (TextView) v.findViewById(R.id.away_name);
                    TextView matchGroup = (TextView) v.findViewById(R.id.match_group);
                    TextView matchId = (TextView) v.findViewById(R.id.match_id);
                    TextView matchTime = (TextView) v.findViewById(R.id.match_time);

                    holder.homePic = homePicture;
                    holder.awayPic = homePicture;
                    holder.homeName = homeName;
                    holder.awayName = awayName;
                    holder.matchGroup = matchGroup;
                    holder.matchId = matchId;
                    holder.matchTime = matchTime;
                    v.setTag(holder);

                } else {
                    holder = (SingleMatchViewHolder) v.getTag();
                }
                // set data on View
                holder.homePic.setImageResource(ImageConvert.convertString(Integer.valueOf(match.getHome_id())));
                holder.awayPic.setImageResource(ImageConvert.convertString(Integer.valueOf(match.getAway_id())));
                holder.homeName.setText(match.getHome_name());
                holder.awayName.setText(match.getAway_name());
                holder.matchGroup.setText(match.getGroup_id());
                holder.matchId.setText(match.getMatch_id());
                holder.matchTime.setText(match.getTime());
            }

        }
        return v;
    }

    class DateMatchViewHolder {
        TextView dateTextView;
    }

    class SingleMatchViewHolder {
        ImageView homePic, awayPic;
        TextView homeName, awayName, matchGroup, matchId, matchTime;
    }

}
