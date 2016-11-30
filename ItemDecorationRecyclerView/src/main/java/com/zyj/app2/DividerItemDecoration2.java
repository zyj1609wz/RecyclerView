package com.zyj.app2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by ${zyj} on 2016/11/30.
 */

public class DividerItemDecoration2 extends RecyclerView.ItemDecoration {

    private int divider_bottom = 0 ;
    private int divider_top = 0 ;
    private int divider_left = 0 ;
    private int divider_right = 0 ;

    private Paint dividerPaint;

    public DividerItemDecoration2(Context context ){
        divider_bottom = context.getResources().getDimensionPixelSize(R.dimen.divider_bottom);
        divider_top = context.getResources().getDimensionPixelSize(R.dimen.divider_top);
        divider_left = context.getResources().getDimensionPixelSize(R.dimen.divider_left);
        divider_right = context.getResources().getDimensionPixelSize(R.dimen.divider_right);

        dividerPaint = new Paint() ;
        dividerPaint.setColor(context.getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        Log.d( "cccccccc" , "" + childCount ) ;
        int left = parent.getPaddingLeft();  //获取父控件的左边距
        int right = parent.getWidth() - parent.getPaddingRight();  //获取父控件的右边距

        for (int i = 0; i < childCount ; i++) {
            View childView = parent.getChildAt(i);
            float childView_top = childView.getTop() - divider_top /2  ;  ;
            float childView_bottom = childView.getBottom() + divider_bottom /2 ;
            float childView_right = childView.getRight() + divider_right /2  ;
            float childView_left = childView.getLeft() - divider_left /2  ;
            c.drawRect( childView_left , childView_top , childView_right  , childView_bottom,  dividerPaint );
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = divider_bottom  ;//类似加了一个bottom padding
        outRect.top = divider_top  ; //类似加了一个bottom top
        outRect.left = divider_left  ; //类似加了一个bottom left
        outRect.right = divider_right ; //类似加了一个bottom right
    }
}
