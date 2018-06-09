package th.wc2018.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import data.raw.Events;
import th.wc2018.R;
import th.wc2018.ulity.UtilConvertor;

public class LiveScoreEventAdapter extends ArrayAdapter {
    public LiveScoreEventAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        ImageView country, eventImg;
        TextView time, player;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        Object object = getItem(position);

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            holder = new ViewHolder();
            v = inflater.inflate(R.layout.item_event_layout, null);
            holder.country = (ImageView) v.findViewById(R.id.home_away);
            holder.eventImg = (ImageView) v.findViewById(R.id.event_img);
            holder.time = (TextView) v.findViewById(R.id.time);
            holder.player = (TextView) v.findViewById(R.id.player);
            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        // set data
        if (object instanceof Events) {
            Events event = (Events) object;
            holder.country.setImageResource(UtilConvertor.convertFlagCountry(event.getHome_away_name()));
            holder.eventImg.setImageResource(UtilConvertor.convertEventStringToImg(event.getEvent()));
            holder.time.setText(event.getTime());
            holder.player.setText(event.getPlayer());
        }
        return v;
    }
}
