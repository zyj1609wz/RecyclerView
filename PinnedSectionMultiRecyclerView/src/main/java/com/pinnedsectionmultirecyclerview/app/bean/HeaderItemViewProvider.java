package com.pinnedsectionmultirecyclerview.app.bean;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinnedsectionmultirecyclerview.app.HeaderUtil;
import com.pinnedsectionmultirecyclerview.app.R;
import com.pinnedsectionmultirecyclerview.app.impl.BaseItemViewProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${zyj} on 2016/12/1.
 */
public class HeaderItemViewProvider
        extends BaseItemViewProvider<HeaderItem, HeaderItemViewProvider.HeaderViewHolder> {

    public HeaderUtil headerUtil ;
    public Map<Integer , Integer > mapID  ;

    public HeaderItemViewProvider(Activity activity ){
        headerUtil = new HeaderUtil( activity ) ;
        mapID = new HashMap<>() ;
    }

    @NonNull
    @Override
    protected HeaderViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_header , parent, false);
        return new HeaderViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull HeaderViewHolder holder, @NonNull HeaderItem dog) {
        int postion = holder.getAdapterPosition() ;
        setOnClick( holder.itemView ,  postion );
        mapID.put( postion , 1  ) ;
        Log.d( "zd", "onCreateViewHolder: " + holder.getAdapterPosition() );
        holder.dogName_tv.setText( dog.name );
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView dogName_tv ;
        HeaderViewHolder(View itemView) {
            super(itemView);
            dogName_tv = (TextView) itemView.findViewById( R.id.dog_name );
        }
    }

    public boolean isHeader(int id ){
       return ( mapID.get( id) == null )? false : true ;
    }
}