package th.wc2018.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import data.raw.Group;
import th.wc2018.R;

public class Adapter_layer2 extends ArrayAdapter {
    public Adapter_layer2(@NonNull Context context, int resource, List teams) {
        super(context, resource, teams);
    }


    class ViewHolder {
        ImageView viewImag;
        TextView textView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        Object obj = getItem(position);

        ViewHolder holder;

        if (v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.group_item_layout_list_item, null);
            holder = new ViewHolder();
            holder.viewImag = (ImageView) v.findViewById(R.id.image_icon);
            holder.textView = (TextView) v.findViewById(R.id.team_info_text);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }


        // set data
//        if (obj instanceof Group.TeamInfo) {
//            Group.TeamInfo teamInfo = (Group.TeamInfo) obj;
//            //holder.viewImag.setImageDrawable(teamInfo.getImage());
//            holder.textView.setText(teamInfo.getInfo());
//        }

        return v;
    }
}
