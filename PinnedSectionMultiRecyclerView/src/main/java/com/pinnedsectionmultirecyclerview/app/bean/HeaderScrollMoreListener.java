package com.pinnedsectionmultirecyclerview.app.bean;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pinnedsectionmultirecyclerview.app.HeaderUtil;

/**
 * Created by ${zyj} on 2016/12/1.
 */

public class HeaderScrollMoreListener extends RecyclerView.OnScrollListener implements HeaderUtil.HeaderViewTrans{

    private boolean isLoadingMore = false ;
    private HeaderItemViewProvider headerItemViewProvider  ;
    private View headerView ;

    public HeaderScrollMoreListener( HeaderItemViewProvider headerItemViewProvider ){
        this.headerItemViewProvider = headerItemViewProvider ;
        this.headerView = headerItemViewProvider.headerUtil.headerView ;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
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


      //  Log.e( "zhao", "onScrolled: isHeader: " + isHeader );

       View view =  recyclerView.findChildViewUnder( headerView.getMeasuredWidth() / 2, headerView.getMeasuredHeight() + 1 ) ;
       Log.e( "zhao", "onScrolled: " + headerView.getTop() + " bo: " + headerView.getBottom()  + "  width:"
               + headerView.getMeasuredWidth() / 2 + "  h: " + headerView.getMeasuredHeight() + 1 + "  y: "+ dy );
        int childViewPosition = recyclerView.getChildAdapterPosition( view ) ;
        boolean isHeader = headerItemViewProvider.isHeader( childViewPosition ) ;
        if ( isHeader ){
            if ( dy > 0 ){
                //向上滑动
                headerView.setTranslationY( 100 );
                Log.e( "zhao", "onScrolled: 向上滑动" );
            }else {
                //向下滑动
                headerView.setTranslationY( 200 );
                Log.e( "zhao", "onScrolled: 向下滑动" );
            }
        }

      //  View childView = recyclerView.getChildAt( firstVisibleItemPosition  + 1 ) ;
    //    RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder( childView ) ;
      //  if ( holder instanceof HeaderItemViewProvider.HeaderViewHolder ){
            //肯定是
           // int top = childView.getTop() ;
            //int headerBottom = headerView.getBottom() ;
           // Log.e( "zhao", "onScrolled: top: " + top + "  bot: " + headerBottom + "  " + ( headerBottom - top ) );
        /*    if ( headerBottom - top <= 0 && dy < 0 ){
                headerView.setTranslationY( dy );
            }*/
     //   }
    }

    @Override
    public void trans(View view) {

    }
}
