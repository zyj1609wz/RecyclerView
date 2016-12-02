package com.pinnedsectionmultirecyclerview.app;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ${zyj} on 2016/12/2.
 */

public class HeaderUtil implements View.OnClickListener {

    public RelativeLayout headerView ;
    public TextView textView ;
    public HeaderViewTrans headerViewTrans ;

    public HeaderUtil( Activity root ){
        headerView = (RelativeLayout) root.findViewById( R.id.headerView );
        textView = (TextView) root.findViewById( R.id.dog_name );
        textView.setOnClickListener( this ) ;
    }

    private void updateHeaderUI(){

    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.dog_name :
                break;
        }
    }

    public interface OnClick {

    }

    public interface HeaderViewTrans{
        void trans( View view );
    }
}
