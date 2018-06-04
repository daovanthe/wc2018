package th.wc2018.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

public class MatchAdapter extends BaseAdapter {
    private Context mContext;
    private List objects;

    public MatchAdapter(Context pContext, List objects) {
        this.mContext = pContext;
        this.objects = objects;
    }

    public Context getContext() {
        return mContext;
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup convertView) {
        Object object = getItem(position);
        ViewGroup view_item_layout = (ViewGroup) view;
        ViewHolder holder1 = null;
        ViewHolder holder2 = null;

        if (view_item_layout == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            view_item_layout = (ViewGroup) li.inflate(R.layout.content_matches_layout, null);

            holder1 = new ViewHolder();
            holder2 = new ViewHolder();

            holder1.view = li.inflate(R.layout.item_date_match_layout, null);
            holder2.view = li.inflate(R.layout.item_match_layout, null);

            view_item_layout.setTag(R.string.FIRST_TAG, holder1);
            view_item_layout.setTag(R.string.SECOND_TAG, holder2);

        } else {
            holder1 = (ViewHolder) view_item_layout.getTag(R.string.FIRST_TAG);
            holder2 = (ViewHolder) view_item_layout.getTag(R.string.SECOND_TAG);
        }

        view_item_layout.removeAllViews();
        if (object instanceof String) {
            TextView lDateTextView = (TextView) holder1.view.findViewById(R.id.date_match);
            lDateTextView.setText(object.toString());

            view_item_layout.addView(holder1.view);
        } else if (object instanceof Fixtures) {
            ImageView homePicture = (ImageView) holder2.view.findViewById(R.id.home_pic);
            ImageView awayPicture = (ImageView) holder2.view.findViewById(R.id.away_pic);
            TextView homeName = (TextView) holder2.view.findViewById(R.id.home_name);
            TextView awayName = (TextView) holder2.view.findViewById(R.id.away_name);
            TextView matchGroup = (TextView) holder2.view.findViewById(R.id.match_group);
            TextView matchId = (TextView) holder2.view.findViewById(R.id.match_id);
            TextView matchTime = (TextView) holder2.view.findViewById(R.id.match_time);

            // set data
            Fixtures match = (Fixtures) object;
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
                String timeConverted = new SimpleDateFormat("HH:mm:ss").format(time);
                matchTime.setText(timeConverted);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            view_item_layout.addView(holder2.view);
        }
        return view_item_layout;
    }

    class ViewHolder {
        View view;
    }
//
//    public View getView2(int position, View view, ViewGroup convertView) {
//
//        View viewGroup = view;
//        Object objectItem = getItem(position);
//
//        if (objectItem != null) {
//            DateMatchViewHolder holder1;
//            SingleMatchViewHolder holder2;
//
//            LayoutInflater vi;
//            vi = LayoutInflater.from(getContext());
//            View viewDate = vi.inflate(R.layout.item_date_match_layout, null);
//            holder1 = new DateMatchViewHolder();
//            TextView lDateTextView = (TextView) viewDate.findViewById(R.id.date_match);
//
//            View viewMatches = vi.inflate(R.layout.item_match_layout, null);
//            ImageView homePicture = (ImageView) viewMatches.findViewById(R.id.home_pic);
//            ImageView awayPicture = (ImageView) viewMatches.findViewById(R.id.away_pic);
//            TextView homeName = (TextView) viewMatches.findViewById(R.id.home_name);
//            TextView awayName = (TextView) viewMatches.findViewById(R.id.away_name);
//            TextView matchGroup = (TextView) viewMatches.findViewById(R.id.match_group);
//            TextView matchId = (TextView) viewMatches.findViewById(R.id.match_id);
//            TextView matchTime = (TextView) viewMatches.findViewById(R.id.match_time);
//
//            if (objectItem instanceof String) {
//                lDateTextView.setText(objectItem.toString());
//                viewGroup = viewDate;
//            } else { // (objectItem instanceof Fixtures)
//                viewGroup = viewMatches;
//                // set data on View
//                Fixtures match = (Fixtures) objectItem;
//                homePicture.setImageResource(UtilConvertor.convertFlagCountry(match.getHome_name()));
//                awayPicture.setImageResource(UtilConvertor.convertFlagCountry(match.getAway_name()));
//                homeName.setText(match.getHome_name());
//                awayName.setText(match.getAway_name());
//                matchGroup.setText(UtilConvertor.convertLeadeToGroup(match.getLeague_id()));
//                matchId.setText(match.getMatch_id());
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
//                try {
//                    Date time = dateFormat.parse(match.getDate() + " " + match.getTime());
//                    String timeConverted = new SimpleDateFormat("HH:mm:ss").format(time);
//                    matchTime.setText(timeConverted);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return viewGroup;
//    }
}
