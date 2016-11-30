package com.zyj.pinnedsection.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

/**
 * Created by ${zyj} on 2016/11/30.
 */

public class SectionItemDecoration extends RecyclerView.ItemDecoration {

    private DecorationCallback mDecorationCallback ;
    private TextPaint textPaint ;
    private Paint.FontMetrics fontMetrics ;
    private int topGap;
    private Paint paint;

    public SectionItemDecoration(Context context , DecorationCallback mDecorationCallback){
        this.mDecorationCallback = mDecorationCallback ;

        fontMetrics = new Paint.FontMetrics();
        textPaint = new TextPaint();
        //字体加粗
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);
        //设置字体大小
        textPaint.setTextSize(30);
        //设置字体颜色
        textPaint.setColor(Color.BLACK);
        textPaint.getFontMetrics(fontMetrics);

        //设置绘制文字的对齐方向
        textPaint.setTextAlign(Paint.Align.LEFT);

        topGap = context.getResources().getDimensionPixelSize(R.dimen.sectioned_top);//32dp

        paint = new Paint();
        paint.setColor( context.getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount();     //获取RecyclerView中所有的item，包括：在屏幕中显示的+ 划出屏幕外的
        int childCount = parent.getChildCount();  //获取当前视野中,item的个数
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        float lineHeight = textPaint.getTextSize() + fontMetrics.descent;

        Log.d( "rrr" , "item:  " + itemCount  + "  cc： " + childCount ) ;
        long preGroupId, groupId = -1;
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childView);

            preGroupId = groupId;
            groupId = mDecorationCallback.getGroupId(position);
            Log.d( "ggg" , "groupId:  " + groupId  + "  preGroupId： " + preGroupId ) ;
           if (groupId < 0 || groupId == preGroupId) continue;

            String textLine = mDecorationCallback.getGroupFirstLine(position);
            if (TextUtils.isEmpty(textLine)) continue;

            int viewBottom = childView.getBottom();
            float textY = Math.max(topGap, childView.getTop());

            Log.d( "rrs" , "top:  " + topGap  + "  getTop： " + childView.getTop() ) ;

            if (position + 1 < itemCount) { //下一个和当前不一样移动当前
                long nextGroupId = mDecorationCallback.getGroupId(position + 1);
                if (nextGroupId != groupId && viewBottom < textY ) {//组内最后一个view进入了header
                    textY = viewBottom ;
                }
            }
            c.drawRect(left, textY - topGap, right, textY, paint);
            c.drawText(textLine, left, textY, textPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        long groupId = mDecorationCallback.getGroupId(pos);
        if (groupId < 0) return;
        if (pos == 0 || isFirstInGroup(pos)) {
            outRect.top = topGap;
        } else {
            outRect.top = 0;
        }
    }

    private boolean isFirstInGroup(int pos) {
        if (pos == 0) {
            return true;
        } else {
            long prevGroupId = mDecorationCallback.getGroupId(pos - 1);
            long groupId = mDecorationCallback.getGroupId(pos);
            return prevGroupId != groupId;
        }
    }

}
