package com.zyj.refresh.app;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private SwipeRefreshLayout refreshLayout ;
    private MyAdapter myAdapter ;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById( R.id.recyclerView );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration( new DividerItemDecoration( this ));

        refreshLayout = (SwipeRefreshLayout) findViewById( R.id.refreshLayout );
        refreshLayout.setColorSchemeResources(android.R.color.holo_red_dark, android.R.color.holo_orange_dark, android.R.color.holo_green_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //模拟延时
                            Thread.sleep( 2000 );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //完成刷新
                                refreshLayout.setRefreshing( false );
                                Toast.makeText(MainActivity.this, "刷新完成了", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).start();
            }
        });

        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                myAdapter.addItem( "add ");
                myAdapter.addItem( "add ");
                myAdapter.addItem( "add ");
                myAdapter.addItem( "add ");
                myAdapter.addItem( "add ");
                Toast.makeText( MainActivity.this , "没有数据了" , Toast.LENGTH_SHORT).show();
            }
        });

        getData();
        myAdapter = new MyAdapter( this , list ) ;
        recyclerView.setAdapter( myAdapter );
    }

    void getData(){
        for (int i = 0; i < 40 ; i++) {
            list.add( "数据 " + i ) ;
        }
    }
}
