package com.pinnedsectionmultirecyclerview.app.impl;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.pinnedsectionmultirecyclerview.app.bean.HeaderItemViewProvider;

/**
 * Created by ${zyj} on 2016/12/1.
 */

public class HeaderScrollMoreListener extends RecyclerView.OnScrollListener {

    private HeaderItemViewProvider headerItemViewProvider  ;
    private View headerView ;
    private boolean isHeadTag = true ;

    public HeaderScrollMoreListener( HeaderItemViewProvider headerItemViewProvider ){
        this.headerItemViewProvider = headerItemViewProvider ;
        this.headerView = headerItemViewProvider.headerUtil.headerView ;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition() ;  //获取屏幕上可见第一个item的postion
        int lastVisibleItem =  layoutManager.findLastVisibleItemPosition(); //获取屏幕上可见最后一个item的postion
        int totalItemCount = layoutManager.getItemCount();      //获取recyclerView所有的item的个数
        int visibleItemCount = recyclerView.getChildCount();    //获取当前可见区域item的个数

        //获取布局方向
        int orientation  = layoutManager.getOrientation() ;
        if ( GridLayoutManager.HORIZONTAL ==  orientation ){
            //水平布局
        }else {
            //垂直布局
        }

        //dy > 0 代表向上滚动

        if ( headerView == null ) return;

        View childView = recyclerView.findChildViewUnder(headerView.getMeasuredWidth() / 2, headerView.getMeasuredHeight() + 1);
        int position = recyclerView.getChildAdapterPosition(childView);

        if ( childView == null ) return;

        //headerView.getMeasuredHeight() 获取的高度是固定的
        int dealtY = childView.getTop() - headerView.getMeasuredHeight();

        boolean isHeader = headerItemViewProvider.isHeader( position ) ;
        Log.d( "zhao", "onScrolled: getTop: " + childView.getTop() + "  Height(): " + headerView.getMeasuredHeight() +"  " +  dealtY + "  p: " + position + "  isHead: " + isHeader + "  dy: " + dy   );
        if ( isHeader ) {
            headerView.setTranslationY(dealtY);
        } else  {
           headerView.setTranslationY(0);
        }

        if ( isHeadTag != isHeader ){
            isHeadTag = isHeader ;
            headerItemViewProvider.update( position );
        }
    }
}
