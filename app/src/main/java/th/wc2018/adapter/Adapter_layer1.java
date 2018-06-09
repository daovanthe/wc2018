package th.wc2018.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import data.raw.Group;
import th.wc2018.R;

public class Adapter_layer1 extends ArrayAdapter {
    public Adapter_layer1(@NonNull Context context, int resource, List<Object> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView header;
        ListView content;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Object group = getItem(position);
        ViewHolder holder;
        View v = convertView;

        if (v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.group_item_layout, null);
            TextView content_header = (TextView) v.findViewById(R.id.content_header);
            ListView team_status_list = (ListView) v.findViewById(R.id.team_status_list);

            List teams = new ArrayList();
            Adapter_layer2 teamAdapter = new Adapter_layer2(getContext(), 0, teams);
            team_status_list.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });

            team_status_list.setTag(teams);

            team_status_list.setAdapter(teamAdapter);

            holder = new ViewHolder();
            holder.header = content_header;
            holder.content = team_status_list;
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        // load data
        if (group instanceof Group) {
//            Group group_item = (Group) group;
//            String content_header = group_item.getContent_header();
//
//            holder.header.setText(content_header);
//            // listView
//            List teams = (List) holder.content.getTag();
//            teams.removeAll(teams);
//            for (Group.TeamInfo teamInfo : group_item.getData()) {
//                teams.add(teamInfo);
//            }
//            ((Adapter_layer2) holder.content.getAdapter()).notifyDataSetChanged();
        }

        return v;
    }
}
