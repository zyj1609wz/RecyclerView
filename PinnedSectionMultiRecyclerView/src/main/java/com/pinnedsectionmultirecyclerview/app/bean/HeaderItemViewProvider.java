package com.pinnedsectionmultirecyclerview.app.bean;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pinnedsectionmultirecyclerview.app.R;
import com.pinnedsectionmultirecyclerview.app.impl.BaseItemViewProvider;
import com.pinnedsectionmultirecyclerview.app.impl.HeaderUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${zyj} on 2016/12/1.
 */
public class HeaderItemViewProvider
        extends BaseItemViewProvider<HeaderItem, HeaderItemViewProvider.HeaderViewHolder> {

    public HeaderUtil headerUtil ;
    private Map<Integer , Integer > headerIdMap  ;
    private Context mContext ;

    public HeaderItemViewProvider(Context context ){
        this.mContext = context ;
        //创建Header视图
        headerUtil = new HeaderUtil((Activity) context) ;
        headerIdMap = new HashMap<>() ;
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
        headerIdMap.put( postion , 1  ) ;
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
       return ( headerIdMap.get( id) == null )? false : true ;
    }

    public void sticky( boolean sticky ){
        headerUtil.sticky( sticky );
    }

    public void update(int position ) {
        Log.d( "zhao", "update: " + position );
        Toast.makeText( mContext , "up " + position , Toast.LENGTH_SHORT).show();
    }

}