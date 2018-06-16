package th.wc2018.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import data.raw.Events;
import data.raw.History;
import data.raw.LiveScore;
import th.wc2018.R;
import th.wc2018.ulity.UtilConvertor;

public class HistoryScoreAdapter extends ArrayAdapter {
    public HistoryScoreAdapter(@NonNull Context context, int resource, List<Object> object) {
        super(context, resource, object);
    }


    class ViewHolder {
        ImageView homeImg, awayImg, refreshIcon;
        TextView homeName, awayName, updateTime, allTime, halfTime, fullTime, exTime, header_live_score, status_txt;
        RelativeLayout eventList;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        History liveScore = (History) getItem(position);

        if (v == null) {
            LayoutInflater inplatter = LayoutInflater.from(getContext());
            v = inplatter.inflate(R.layout.item_score_layout, null);
        }
        ViewHolder holder = new ViewHolder();
        holder.header_live_score = (TextView) v.findViewById(R.id.header_live_score);

        holder.homeImg = (ImageView) v.findViewById(R.id.home_img);
        holder.awayImg = (ImageView) v.findViewById(R.id.away_img);

        holder.homeName = (TextView) v.findViewById(R.id.home_name_txt);
        holder.awayName = (TextView) v.findViewById(R.id.away_name_txt);

        holder.updateTime = (TextView) v.findViewById(R.id.date_update_txt);
        holder.status_txt = (TextView)  v.findViewById(R.id.status_txt);

        holder.allTime = (TextView) v.findViewById(R.id.all_time_txt);
        holder.halfTime = (TextView) v.findViewById(R.id.half_time);
        holder.fullTime = (TextView) v.findViewById(R.id.full_time);
        holder.exTime = (TextView) v.findViewById(R.id.ex_time);

        holder.header_live_score.setText(liveScore.getLeague_name());
        holder.homeImg.setImageResource(UtilConvertor.convertFlagCountry(liveScore.getHome_name()));
        holder.awayImg.setImageResource(UtilConvertor.convertFlagCountry(liveScore.getAway_name()));
        holder.homeName.setText(liveScore.getHome_name());
        holder.awayName.setText(liveScore.getAway_name());
        // need convert time

        holder.updateTime.setText(liveScore.getLast_changed());
        holder.status_txt.setText(liveScore.getStatus());
        holder.allTime.setText(liveScore.getScore());
        holder.halfTime.setText(liveScore.getHt_score());
        holder.fullTime.setText(liveScore.getFt_score());
        holder.exTime.setText(liveScore.getEt_score());

//        ArrayAdapter adapter = (ArrayAdapter) holder.eventList.getTag();
//        if (adapter == null) {
//            adapter = new ArrayAdapter(getContext(), 0, liveScore.getListEvents()) {
//                @NonNull
//                @Override
//                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                    View v = convertView;
//                    Object object = getItem(position);
//                    if (v == null) {
//                        LayoutInflater inflater = LayoutInflater.from(getContext());
//                        v = inflater.inflate(R.layout.item_event_layout, null);
//                    }
//                    ImageView country = (ImageView) v.findViewById(R.id.home_away);
//                    ImageView eventImg = (ImageView) v.findViewById(R.id.event_img);
//                    TextView time = (TextView) v.findViewById(R.id.time);
//                    TextView player = (TextView) v.findViewById(R.id.player);
//                    // set data
//                    if (object instanceof Events) {
//                        Events event = (Events) object;
//                        country.setImageResource(UtilConvertor.convertFlagCountry(event.getHome_away_name()));
//                        eventImg.setImageResource(UtilConvertor.convertEventStringToImg(event.getEvent()));
//                        time.setText(event.getTime());
//                        player.setText(event.getPlayer());
//                    }
//                    return v;
//                }
//            };
//            holder.eventList.setTag(adapter);
//        }
//        holder.eventList.setAdapter(adapter);
//        liveScore.setTag(adapter);

//        holder.eventList.removeAllViews();
//        View tempView = null;
//        int ID_temp = 0;
//        if (liveScore.getListEvents().size() > 0) {
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) getContext().getResources().getDimension(R.dimen.event_height));
//            Events event = liveScore.getListEvents().get(0);
//            View eventViewLayout = LayoutInflater.from(getContext()).inflate(R.layout.item_event_layout, null);
//            eventViewLayout.setId(View.generateViewId());
//            ImageView country = eventViewLayout.findViewById(R.id.home_away);
//            ImageView eventImg = eventViewLayout.findViewById(R.id.event_img);
//            TextView time = eventViewLayout.findViewById(R.id.time);
//            TextView player = eventViewLayout.findViewById(R.id.player);
//            country.setImageResource(UtilConvertor.convertFlagCountry(event.getHome_away_name()));
//            eventImg.setImageResource(UtilConvertor.convertEventStringToImg(event.getEvent()));
//            time.setText(event.getTime());
//            player.setText(event.getPlayer());
//            ID_temp = eventViewLayout.getId();
//            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, ID_temp);
//            eventViewLayout.setLayoutParams(params);
//        }
//        for (int i = 1; i < liveScore.getListEvents().size(); i++) {
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) getContext().getResources().getDimension(R.dimen.event_height));
//            Events event = liveScore.getListEvents().get(i);
//            View eventViewLayout = LayoutInflater.from(getContext()).inflate(R.layout.item_event_layout, null);
//            eventViewLayout.setId(View.generateViewId());
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                eventViewLayout.setId(View.generateViewId());
//            }
//            ImageView country = eventViewLayout.findViewById(R.id.home_away);
//            ImageView eventImg = eventViewLayout.findViewById(R.id.event_img);
//            TextView time = eventViewLayout.findViewById(R.id.time);
//            TextView player = eventViewLayout.findViewById(R.id.player);
//            country.setImageResource(UtilConvertor.convertFlagCountry(event.getHome_away_name()));
//            eventImg.setImageResource(UtilConvertor.convertEventStringToImg(event.getEvent()));
//            time.setText(event.getTime() + "'");
//            player.setText(event.getPlayer());
//            holder.eventList.addView(eventViewLayout);
//            params.addRule(RelativeLayout.BELOW, ID_temp);
//            eventViewLayout.setLayoutParams(params);
//            ID_temp = eventViewLayout.getId();
//            eventViewLayout.setLayoutParams(params);
//        }

//        holder.eventList.setLayoutParams(params);


//        LiveScoreEventAdapter liveScoreEventAdapter = (LiveScoreEventAdapter) liveScore.getTag();
//        if (liveScoreEventAdapter == null) {
//            List<Events> events = liveScore.getListEvents();
//            liveScoreEventAdapter = new LiveScoreEventAdapter(getContext(), 0, events);
//            holder.eventList.setAdapter(liveScoreEventAdapter);
//            liveScore.setTag(liveScoreEventAdapter);
//        }

        return v;
    }


    //    public View getView1(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        Object objectItem = getItem(position);
//        ViewHolder holder;
//        View v = convertView;
//        if (v == null) {
//            LayoutInflater li = LayoutInflater.from(getContext());
//            v = li.inflate(R.layout.item_score_layout, null);
//
//            holder = new ViewHolder();
//            holder.homeImg = (ImageView) v.findViewById(R.id.home_img);
//            holder.awayImg = (ImageView) v.findViewById(R.id.away_img);
//
//            holder.homeName = (TextView) v.findViewById(R.id.home_name_txt);
//            holder.awayName = (TextView) v.findViewById(R.id.away_name_txt);
//
//            holder.updateTime = (TextView) v.findViewById(R.id.date_update_txt);
//
//            holder.allTime = (TextView) v.findViewById(R.id.all_time_txt);
//            holder.halfTime = (TextView) v.findViewById(R.id.half_time);
//            holder.fullTime = (TextView) v.findViewById(R.id.full_time);
//            holder.exTime = (TextView) v.findViewById(R.id.ex_time);
//
//            holder.eventList = (ListView) v.findViewById(R.id.event_list_score);
//
//            holder.exTime = (TextView) v.findViewById(R.id.ex_time);
//            // set event
//            List<Object> listOfObject = new ArrayList<>();
//
//            LiveScoreEventAdapter listScoreEventAdapter = new LiveScoreEventAdapter(getContext(), 0, listOfObject);
//
//            new AsyncTask<Object, Void, List<Object>>() {
//                private LiveScoreEventAdapter listScoreEventAdapter;
//
//                @Override
//                protected List<Object> doInBackground(Object... allmatchesInfos) {
//                    listOfObject.removeAll(listOfObject);
//                    LoadData loadData = null;
//                    try {
//                        loadData = new LoadData(getContext(), App.DATABASE);
//                    } catch (IllegalArgumentException w) {
//                        w.printStackTrace();
//                    }
//                    LiveScore eventObject = (LiveScore) objectItem;
//                    List<Events> listMatchesPerDay = loadData.getEventLiveScoreDao().getEventBy(eventObject.getLeague_id(), eventObject.getId());
//
//                    for (Events event : listMatchesPerDay) {
//                        listOfObject.add(event);
//                    }
//                    onProgressUpdate();
//                    loadData.closeConnect();
//                    return listOfObject;
//                }
//
//                @Override
//                protected void onPostExecute(List<Object> list) {
//                    super.onPostExecute(list);
//                    listScoreEventAdapter.notifyDataSetChanged();
//                }
//            }.execute();
//
//            holder.eventList.setAdapter(listScoreEventAdapter);
//            holder.eventList.setTag(listOfObject);
//
//            v.setTag(holder);
//        } else {
//            holder = (ViewHolder) v.getTag();
//        }
//
//        // get data
//        if (objectItem instanceof LiveScore) {
//            LiveScore liveScore = (LiveScore) objectItem;
//
//            holder.homeImg.setImageResource(UtilConvertor.convertFlagCountry(liveScore.getHome_name()));
//            holder.awayImg.setImageResource(UtilConvertor.convertFlagCountry(liveScore.getAway_name()));
//            holder.homeName.setText(liveScore.getHome_name());
//            holder.awayName.setText(liveScore.getAway_name());
//
//            holder.updateTime.setText(liveScore.getLast_changed());
//
//            holder.allTime.setText(liveScore.getScore());
//            holder.halfTime.setText(liveScore.getHt_score());
//            holder.fullTime.setText(liveScore.getFt_score());
//            holder.exTime.setText(liveScore.getEt_score());
//
//            List<Object> objects = (List<Object>) holder.eventList.getTag();
//            objects.removeAll(objects);
//
//
//            String apiLIve = liveScore.getEvents();
//        }
//        return v;
//    }


//    class UpdateEventLiveScore extends AsyncTask<List<Object>, Void, List<Object>> {
//        private String ApiLinkScore;
//
//        public UpdateEventLiveScore(String apiLink) {
//            ApiLinkScore = apiLink;
//        }
//
//        @Override
//        protected List<Object> doInBackground(List<Object>... voids) {
//            List<Object> objects = voids[0];
//
//            return objects;
//        }
//    }


}

