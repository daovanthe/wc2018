package th.wc2018.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import data.raw.Fixtures;
import th.wc2018.R;
import th.wc2018.ulity.UtilConvertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MatchAdapter extends ArrayAdapter {


    public MatchAdapter(Context context, int resource, List<Object> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View view, ViewGroup convertView) {

        View viewGroup = view;
        Object objectItem = getItem(position);

        if (objectItem != null) {
            DateMatchViewHolder holder1;
            SingleMatchViewHolder holder2;

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            View viewDate = vi.inflate(R.layout.date_match_layout, null);
            holder1 = new DateMatchViewHolder();
            TextView lDateTextView = (TextView) viewDate.findViewById(R.id.date_match);

            View viewMatches = vi.inflate(R.layout.match_item_layout, null);
            ImageView homePicture = (ImageView) viewMatches.findViewById(R.id.home_pic);
            ImageView awayPicture = (ImageView) viewMatches.findViewById(R.id.away_pic);
            TextView homeName = (TextView) viewMatches.findViewById(R.id.home_name);
            TextView awayName = (TextView) viewMatches.findViewById(R.id.away_name);
            TextView matchGroup = (TextView) viewMatches.findViewById(R.id.match_group);
            TextView matchId = (TextView) viewMatches.findViewById(R.id.match_id);
            TextView matchTime = (TextView) viewMatches.findViewById(R.id.match_time);

            if (objectItem instanceof String) {
                lDateTextView.setText(objectItem.toString());
                viewGroup = viewDate;
            } else { // (objectItem instanceof Fixtures)
                viewGroup = viewMatches;
                // set data on View
                Fixtures match = (Fixtures) objectItem;
                homePicture.setImageResource(UtilConvertor.convertFlagCountry(match.getHome_name()));
                awayPicture.setImageResource(UtilConvertor.convertFlagCountry(match.getAway_name()));
                homeName.setText(match.getHome_name());
                awayName.setText(match.getAway_name());
                matchGroup.setText(UtilConvertor.convertLeadeToGroup(match.getLeague_id()));
                matchId.setText(match.getMatch_id());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
                try {
                    Date time = dateFormat.parse(match.getDate() + " " + match.getTime());
                    String timeConverted = new SimpleDateFormat("hh:mm:ss").format(time);
                    matchTime.setText(timeConverted);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }
        return viewGroup;
    }


    class DateMatchViewHolder {
        TextView dateTextView;
    }

    class SingleMatchViewHolder {
        ImageView homePic, awayPic;
        TextView homeName, awayName, matchGroup, matchId, matchTime;
    }

}
