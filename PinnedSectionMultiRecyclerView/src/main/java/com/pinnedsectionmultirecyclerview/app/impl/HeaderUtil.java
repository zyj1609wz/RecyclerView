package com.pinnedsectionmultirecyclerview.app.impl;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.pinnedsectionmultirecyclerview.app.R;

/**
 * Created by ${zyj} on 2016/12/2.
 */

public class HeaderUtil {

    public View headerView ;
    public TextView textView ;

    public HeaderUtil( Activity root ){
        headerView =  root.findViewById( R.id.headerView );
        textView = (TextView) root.findViewById( R.id.dog_name );
    }

    public void sticky( boolean sticky ){
        if ( sticky ){
            headerView.setVisibility( View.VISIBLE );
        }else {
            headerView.setVisibility( View.GONE );
        }
    }
}
