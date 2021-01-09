package com.jnu.ljmcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnu.ljmcalendar.dataprocessor.Record;

import java.util.ArrayList;

public class RecordAdapter extends ArrayAdapter<Record> {
    private int resourceId;

    public RecordAdapter(Context context, int resource, ArrayList<Record> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Record record = getItem(position);//获取当前项的实例
        View view;
        if(convertView==null)
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        else
            view=convertView;

        ((TextView) view.findViewById(R.id.text_type)).setText(record.getType());
        ((TextView) view.findViewById(R.id.text_name)).setText(record.getName());
        ((TextView) view.findViewById(R.id.text_money)).setText(record.getMoney());
        ((TextView) view.findViewById(R.id.text_reason)).setText(record.getReason());
        ((TextView) view.findViewById(R.id.text_date)).setText(record.getDate());
        return view;
    }
}
