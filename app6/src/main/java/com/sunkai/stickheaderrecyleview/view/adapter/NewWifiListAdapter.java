package com.sunkai.stickheaderrecyleview.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sunkai.stickheaderrecyleview.R;
import com.sunkai.stickheaderrecyleview.view.viewhodler.WifiTypeHolder;
import com.sunkai.stickheaderrecyleview.view.viewhodler.WifiViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunkai on 2016/12/1.
 */

public class NewWifiListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListItem> listItems = new ArrayList<>();

    private WifiItemOnClickListener wifiItemOnClickListener;

    public NewWifiListAdapter() {
        ListItem item = new ListItem();
        item.type = 1;

        listItems.add(item);

        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }


        item = new ListItem();
        item.type = 1;
        listItems.add(item);

        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }

        item = new ListItem();
        item.type = 1;
        listItems.add(item);

        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }

        item = new ListItem();
        item.type = 1;
        listItems.add(item);
        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }
        item = new ListItem();
        item.type = 1;
        listItems.add(item);
        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View view;
        switch (viewType) {
            case 1:// wifiType
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yiba_item_wifilist_section, parent, false);
                viewHolder = new WifiTypeHolder(view);
                break;
            case 2:// wifi
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yiba_item_wifi, parent, false);
                viewHolder = new WifiViewHolder(view);
                break;
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        if (holder instanceof WifiTypeHolder) {
        } else if (holder instanceof WifiViewHolder) {


        }
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        ListItem item = listItems.get(position);
        return item.type;
    }

    public View updateHeaderView(ViewGroup headerView, int position) {
        ListItem listItem = listItems.get(position);
        if (listItem.type != 1) {
            return null;
        }
        headerView.removeAllViews();
        View view = LayoutInflater.from(headerView.getContext()).inflate(R.layout.yiba_item_wifilist_section, headerView, false);
        headerView.addView(view);
        ((TextView) view.findViewById(R.id.section_title)).setText("header:" + position);
        return view;
    }

    private static class ListItem {
        int type;

    }

    public interface WifiItemOnClickListener {
        void onClick();
    }

    private static class AdViewParent extends FrameLayout {

        public AdViewParent(Context context) {
            super(context);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            Log.e("SK", "onInterceptTouchEvent");
            onTouchEvent(ev);
            super.onInterceptTouchEvent(ev);
            return false;

        }

    }

    public enum ListType {
        HEADER, CONTEXT
    }
}
