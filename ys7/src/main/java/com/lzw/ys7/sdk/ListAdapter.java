package com.lzw.ys7.sdk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lzw.ys7.R;
import com.videogo.openapi.bean.EZDeviceInfo;

import java.util.List;

/**
 * @author: lzw
 * @date: 14/12/2017 11:10 AM
 * @desc:
 */

public class ListAdapter extends BaseAdapter {
    private List<EZDeviceInfo> data;

    private Context context;

    private Start start;

    private Stop stop;

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public ListAdapter(List<EZDeviceInfo> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(data.get(position).getDeviceName());
        viewHolder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.startPlayer(data.get(position));
            }
        });
        viewHolder.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.stopPlayer(data.get(position));
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView text;
        TextView start, stop;

        ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.text);
            start = (TextView) view.findViewById(R.id.start);
            stop = (TextView) view.findViewById(R.id.stop);
        }
    }

    public interface Start {
        void startPlayer(EZDeviceInfo deviceInfo);
    }

    public interface Stop {
        void stopPlayer(EZDeviceInfo ezDeviceInfo);
    }
}
