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

import java.util.List;

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
                matchTime.setText(match.getTime());
            }

        }
        return viewGroup;
//
//                viewGroup.setTag(R.string.FIRST_TAG, holder1);
//                viewGroup.setTag(R.string.SECOND_TAG, holder2);
//
//            } else {
//                holder1 = (DateMatchViewHolder) viewGroup.getTag(R.string.FIRST_TAG);
//            }
//
//            holder1.dateTextView.setText(objectItem.toString());
//
//        } else if (objectItem instanceof Fixtures) {
//            Fixtures match = (Fixtures) objectItem;
//            SingleMatchViewHolder holder2;
//            DateMatchViewHolder holder1;
//            if (viewGroup == null) {
//                LayoutInflater vi;
//                vi = LayoutInflater.from(getContext());
//                viewGroup = vi.inflate(R.layout.match_item_layout, null);
//                holder2 = new SingleMatchViewHolder();
//                holder1 = new DateMatchViewHolder();
//                // map data
//                ImageView homePicture = (ImageView) viewGroup.findViewById(R.id.home_pic);
//                ImageView awayPicture = (ImageView) viewGroup.findViewById(R.id.away_pic);
//
//                TextView homeName = (TextView) viewGroup.findViewById(R.id.home_name);
//                TextView awayName = (TextView) viewGroup.findViewById(R.id.away_name);
//                TextView matchGroup = (TextView) viewGroup.findViewById(R.id.match_group);
//                TextView matchId = (TextView) viewGroup.findViewById(R.id.match_id);
//                TextView matchTime = (TextView) viewGroup.findViewById(R.id.match_time);
//
//                holder2.homePic = homePicture;
//                holder2.awayPic = awayPicture;
//                holder2.homeName = homeName;
//                holder2.awayName = awayName;
//                holder2.matchGroup = matchGroup;
//                holder2.matchId = matchId;
//                holder2.matchTime = matchTime;
//                viewGroup.setTag(R.string.FIRST_TAG, holder1);
//                viewGroup.setTag(R.string.SECOND_TAG, holder2);
//
//            } else {
//                holder2 = (SingleMatchViewHolder) viewGroup.getTag(R.string.SECOND_TAG);
//            }
//            // set data on View
//            holder2.homePic.setImageResource(UtilConvertor.convertFlagCountry(match.getHome_name()));
//            holder2.awayPic.setImageResource(UtilConvertor.convertFlagCountry(match.getAway_name()));
//            holder2.homeName.setText(match.getHome_name());
//            holder2.awayName.setText(match.getAway_name());
//            holder2.matchGroup.setText(match.getGroup_id());
//            holder2.matchId.setText(match.getMatch_id());
//            holder2.matchTime.setText(match.getTime());
//        }
//
//
//    }
//        return viewGroup;
    }


//
//    @Override
//    public View getView(int position, View view, ViewGroup convertView) {
//
//        View viewGroup = view;
//        Object objectItem = getItem(position);
//
//        if (objectItem != null) {
////            if (objectItem instanceof String) {
//            DateMatchViewHolder holder1;
//            SingleMatchViewHolder holder2;
//            if (viewGroup == null) {
//                LayoutInflater vi;
//                vi = LayoutInflater.from(getContext());
//                View viewDate = vi.inflate(R.layout.date_match_layout, null);
//                holder1 = new DateMatchViewHolder();
//                // map data
//                TextView lDateTextView = (TextView) viewDate.findViewById(R.id.date_match);
//                holder1.dateTextView = lDateTextView;
//
//
//                View viewMatches = vi.inflate(R.layout.match_item_layout, null);
//                holder2 = new SingleMatchViewHolder();
//                ImageView homePicture = (ImageView) viewMatches.findViewById(R.id.home_pic);
//                ImageView awayPicture = (ImageView) viewMatches.findViewById(R.id.away_pic);
//
//                TextView homeName = (TextView) viewMatches.findViewById(R.id.home_name);
//                TextView awayName = (TextView) viewMatches.findViewById(R.id.away_name);
//                TextView matchGroup = (TextView) viewMatches.findViewById(R.id.match_group);
//                TextView matchId = (TextView) viewMatches.findViewById(R.id.match_id);
//                TextView matchTime = (TextView) viewMatches.findViewById(R.id.match_time);
//                holder2.homePic = homePicture;
//                holder2.awayPic = awayPicture;
//                holder2.homeName = homeName;
//                holder2.awayName = awayName;
//                holder2.matchGroup = matchGroup;
//                holder2.matchId = matchId;
//                holder2.matchTime = matchTime;
//
//                if (objectItem instanceof String) {
//                    viewGroup = viewDate;
//                } else { // (objectItem instanceof Fixtures)
//                    viewGroup = viewMatches;
//                }
//                viewGroup.setTag(R.string.FIRST_TAG, holder1);
//                viewGroup.setTag(R.string.SECOND_TAG, holder2);
//            } else {
//                holder1 = (DateMatchViewHolder) viewGroup.getTag(R.string.FIRST_TAG);
//                holder2 = (SingleMatchViewHolder) viewGroup.getTag(R.string.SECOND_TAG);
//
//                if (objectItem instanceof String) {
//                    holder1.dateTextView.setText(objectItem.toString());
//                } else { // (objectItem instanceof Fixtures)
//                    Fixtures match = (Fixtures) objectItem;
//                    holder2.homePic.setImageResource(UtilConvertor.convertFlagCountry(match.getHome_name()));
//                    holder2.awayPic.setImageResource(UtilConvertor.convertFlagCountry(match.getAway_name()));
//                    holder2.homeName.setText(match.getHome_name());
//                    holder2.awayName.setText(match.getAway_name());
//                    holder2.matchGroup.setText(match.getGroup_id());
//                    holder2.matchId.setText(match.getMatch_id());
//                    holder2.matchTime.setText(match.getTime());
//                }
//            }
//        }
//        return viewGroup;
////
////                viewGroup.setTag(R.string.FIRST_TAG, holder1);
////                viewGroup.setTag(R.string.SECOND_TAG, holder2);
////
////            } else {
////                holder1 = (DateMatchViewHolder) viewGroup.getTag(R.string.FIRST_TAG);
////            }
////
////            holder1.dateTextView.setText(objectItem.toString());
////
////        } else if (objectItem instanceof Fixtures) {
////            Fixtures match = (Fixtures) objectItem;
////            SingleMatchViewHolder holder2;
////            DateMatchViewHolder holder1;
////            if (viewGroup == null) {
////                LayoutInflater vi;
////                vi = LayoutInflater.from(getContext());
////                viewGroup = vi.inflate(R.layout.match_item_layout, null);
////                holder2 = new SingleMatchViewHolder();
////                holder1 = new DateMatchViewHolder();
////                // map data
////                ImageView homePicture = (ImageView) viewGroup.findViewById(R.id.home_pic);
////                ImageView awayPicture = (ImageView) viewGroup.findViewById(R.id.away_pic);
////
////                TextView homeName = (TextView) viewGroup.findViewById(R.id.home_name);
////                TextView awayName = (TextView) viewGroup.findViewById(R.id.away_name);
////                TextView matchGroup = (TextView) viewGroup.findViewById(R.id.match_group);
////                TextView matchId = (TextView) viewGroup.findViewById(R.id.match_id);
////                TextView matchTime = (TextView) viewGroup.findViewById(R.id.match_time);
////
////                holder2.homePic = homePicture;
////                holder2.awayPic = awayPicture;
////                holder2.homeName = homeName;
////                holder2.awayName = awayName;
////                holder2.matchGroup = matchGroup;
////                holder2.matchId = matchId;
////                holder2.matchTime = matchTime;
////                viewGroup.setTag(R.string.FIRST_TAG, holder1);
////                viewGroup.setTag(R.string.SECOND_TAG, holder2);
////
////            } else {
////                holder2 = (SingleMatchViewHolder) viewGroup.getTag(R.string.SECOND_TAG);
////            }
////            // set data on View
////            holder2.homePic.setImageResource(UtilConvertor.convertFlagCountry(match.getHome_name()));
////            holder2.awayPic.setImageResource(UtilConvertor.convertFlagCountry(match.getAway_name()));
////            holder2.homeName.setText(match.getHome_name());
////            holder2.awayName.setText(match.getAway_name());
////            holder2.matchGroup.setText(match.getGroup_id());
////            holder2.matchId.setText(match.getMatch_id());
////            holder2.matchTime.setText(match.getTime());
////        }
////
////
////    }
////        return viewGroup;
//    }

    class DateMatchViewHolder {
        TextView dateTextView;
    }

    class SingleMatchViewHolder {
        ImageView homePic, awayPic;
        TextView homeName, awayName, matchGroup, matchId, matchTime;
    }

}
