package com.rau.friendships;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    private ArrayList<Item> listData;
    private LayoutInflater layoutInflater;

    public ItemAdapter(Context aContext, ArrayList<Item> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.TVrowTitle);
            holder.recipientView = (TextView) convertView.findViewById(R.id.TVrowRecipient);
            holder.dateView = (TextView) convertView.findViewById(R.id.TVrowDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleView.setText(listData.get(position).getTitle());
        holder.recipientView.setText("By, " + listData.get(position).getRecipient());
        holder.dateView.setText(listData.get(position).getDate());
        return convertView;
    }

    static class ViewHolder {
        TextView titleView;
        TextView recipientView;
        TextView dateView;
    }
}