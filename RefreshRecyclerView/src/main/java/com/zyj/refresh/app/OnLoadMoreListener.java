package com.zyj.refresh.app;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ${zyj} on 2016/12/1.
 */

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener{

    private boolean isLoadingMore = false ;

    public abstract void onLoadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager() ;
        int lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        int totalItemCount = layoutManager.getItemCount();
        //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
        // dy>0 表示向下滑动
        if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
            if(isLoadingMore){

            } else{
                isLoadingMore = true ;
                onLoadMore(); //这里多线程也要手动控制isLoadingMore
                isLoadingMore = false;
            }
        }
    }
}
