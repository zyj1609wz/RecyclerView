package com.sunkai.stickheaderrecyleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.sunkai.stickheaderrecyleview.view.adapter.NewWifiListAdapter;

public class MainActivity extends AppCompatActivity {
    private NewWifiListAdapter newWifiListAdapter = new NewWifiListAdapter();
    RecyclerView recyclerView;
    FrameLayout frameLayout;
    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(newWifiListAdapter);
        final ViewGroup viewGroup = new FrameLayout(getApplicationContext());
        headerView = newWifiListAdapter.updateHeaderView(viewGroup, 0);
        frameLayout.addView(viewGroup);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View stickyInfoView = recyclerView.findChildViewUnder(headerView.getMeasuredWidth() / 2, 5);
                int position = recyclerView.getChildAdapterPosition(stickyInfoView);
                //TODO 更新 悬浮view
//                NewWifiListAdapter.ListType stickyViewStatus = (NewWifiListAdapter.ListType) stickyInfoView.getTag();
//                if (stickyViewStatus == NewWifiListAdapter.ListType.HEADER) {
//                    headerView = newWifiListAdapter.updateHeaderView(viewGroup, position);
//                }

                if (headerView == null) {
                    return;
                }

                View transInfoView = recyclerView.findChildViewUnder(headerView.getMeasuredWidth() / 2, headerView.getMeasuredHeight() + 1);
                if (transInfoView != null && transInfoView.getTag() != null) {
                    NewWifiListAdapter.ListType transViewStatus = (NewWifiListAdapter.ListType) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - headerView.getMeasuredHeight();
                    if (transViewStatus == NewWifiListAdapter.ListType.HEADER) {
                        if (transInfoView.getTop() > 0) {
                            headerView.setTranslationY(dealtY);
                        } else {
                            headerView.setTranslationY(0);
                           Log.d( "zhao", "onScrolled: ");
                        }
                    } else if (transViewStatus == NewWifiListAdapter.ListType.CONTEXT) {
                        headerView.setTranslationY(0);
                    }
                }
            }
        });
    }

}
