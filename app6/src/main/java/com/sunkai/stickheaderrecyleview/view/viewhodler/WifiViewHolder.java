package com.sunkai.stickheaderrecyleview.view.viewhodler;

/**
 * Created by sunkai on 2016/12/1.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunkai.stickheaderrecyleview.R;
import com.sunkai.stickheaderrecyleview.view.adapter.NewWifiListAdapter;

/**
 * wifi 种类
 */
public class WifiViewHolder extends RecyclerView.ViewHolder {

    ImageView wifiSignal;
    TextView wifi_name;
    RelativeLayout yiba_wifi_item_content_rel;
    TextView wifiStatusDes;


    public WifiViewHolder(View itemView) {
        super(itemView);
        wifiSignal = (ImageView) itemView.findViewById(R.id.yiba_wifi_item_signal);
        wifi_name = (TextView) itemView.findViewById(R.id.yiba_wifi_item_name);
        yiba_wifi_item_content_rel = (RelativeLayout) itemView.findViewById(R.id.yiba_wifi_item_content_rel);
        wifiStatusDes = (TextView) itemView.findViewById(R.id.status);
        itemView.setTag(NewWifiListAdapter.ListType.CONTEXT);

    }

    public void initData() {

    }
}

