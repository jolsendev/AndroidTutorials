package com.example.root.personalnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 8/16/16.
 */
public class NavigationDrawerAdapter extends BaseAdapter {
    private List<NavigationDrawerItem> mDrawerItems;
    private LayoutInflater mLayoutInflater;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> mDrawerItems) {
        super();
        this.mDrawerItems = mDrawerItems;
        mLayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayoutInflater.inflate(R.layout.custom_navigation_draw, null);
        NavigationDrawerItem navigationDrawerItem = mDrawerItems.get(position);
        TextView textView = (TextView) convertView.findViewById(R.id.navigation_item_title);
        textView.setText(navigationDrawerItem.getTitle());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.navigation_item_icon);

        imageView.setImageResource(navigationDrawerItem.getIconId());

        return convertView;
    }
}
