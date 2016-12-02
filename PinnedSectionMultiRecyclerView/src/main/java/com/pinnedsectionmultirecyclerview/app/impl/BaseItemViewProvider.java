package com.pinnedsectionmultirecyclerview.app.impl;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by ${zyj} on 2016/12/1.
 */

public abstract class BaseItemViewProvider<C, V extends RecyclerView.ViewHolder> extends ItemViewProvider<C, V > {

    private OnItemClickListener mOnItemClickListener ;
    private OnItemLongClickListener mOnItemLongClickListener ;

    public void setOnItemClickListener( OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener = onItemClickListener ;
    }

    public void setOnItemLongClickListener( OnItemLongClickListener onItemLongClickListener ){
        this.mOnItemLongClickListener = onItemLongClickListener ;
    }

    protected void setOnClick(final View view , final int position ){
        if ( mOnItemClickListener != null ){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick( v , position );
                }
            });
        }

        if ( mOnItemLongClickListener != null ){
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.onLongClick( v, position );
                }
            });
        }
    }

}
