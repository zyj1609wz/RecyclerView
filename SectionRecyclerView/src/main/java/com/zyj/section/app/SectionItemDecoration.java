package com.zyj.section.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
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
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            long groupId = mDecorationCallback.getGroupId(position);
            if (groupId < 0) return;
            String textLine = mDecorationCallback.getGroupFirstLine(position) ;
            if (position == 0 || isFirstInGroup(position)) {
                float top = view.getTop() - topGap;
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);//绘制红色矩形
                c.drawText( textLine , left , bottom , textPaint);//绘制文本
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        long groupId = mDecorationCallback.getGroupId(pos);
        if (groupId < 0) return ;
        if (pos == 0 || isFirstInGroup(pos)) {//同组的第一个才添加padding
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
