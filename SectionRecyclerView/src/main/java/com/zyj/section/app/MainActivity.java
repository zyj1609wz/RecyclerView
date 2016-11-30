package com.zyj.section.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyAdapter myAdapter ;
    private List<String> list = new ArrayList<>();

    private RecyclerView recyclerView ;
    private Button button1 , button2 , button3 ;
    private Button itemDecoration1_bt , itemDecoration2_bt , itemDecoration3_bt;

    private RecyclerView.ItemDecoration itemDecoration1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for ( int i = 0 ; i < 5 ; i ++ ) {
            list.add( "aaaa   " + i ) ;
        }

        for ( int i = 0 ; i < 8 ; i ++ ) {
            list.add( "bbb   " + i ) ;
        }

        for ( int i = 0 ; i < 6 ; i ++ ) {
            list.add( "cccc   " + i ) ;
        }

        for ( int i = 0 ; i < 10 ; i ++ ) {
            list.add( "ddd   " + i ) ;
        }
        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
        button1 = (Button) findViewById( R.id.bt1 );
        button2 = (Button) findViewById( R.id.bt2 );
        button3 = (Button) findViewById( R.id.bt3 );
        itemDecoration1_bt = (Button) findViewById( R.id.itemDecoration1 );
        itemDecoration2_bt = (Button) findViewById( R.id.itemDecoration2 );
        itemDecoration3_bt = (Button) findViewById( R.id.itemDecoration3 );
        button1.setOnClickListener( this );
        button2.setOnClickListener( this );
        button3.setOnClickListener( this );
        itemDecoration1_bt.setOnClickListener( this );
        itemDecoration2_bt.setOnClickListener( this );
        itemDecoration3_bt.setOnClickListener( this );

        myAdapter = new MyAdapter( this , list ) ;
        recyclerView.setLayoutManager( new LinearLayoutManager( this ));
        recyclerView.setAdapter( myAdapter );

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText( MainActivity.this , "click :" + list.get( position ) , Toast.LENGTH_SHORT).show();
            }
        });

        itemDecoration1 = new SectionItemDecoration(this, new DecorationCallback() {
            @Override
            public long getGroupId(int position) {
                return Character.toUpperCase( list.get( position ).charAt(0));
            }

            @Override
            public String getGroupFirstLine(int position) {
                return list.get(position).substring(0, 1).toUpperCase();
            }
        }) ;
        recyclerView.addItemDecoration( itemDecoration1 );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //线性列表
            case R.id.bt1 :
                recyclerView.setLayoutManager( new LinearLayoutManager( this ));
                myAdapter.notifyDataSetChanged();
                break;

            //网格布局
            case R.id.bt2 :
                recyclerView.setLayoutManager( new GridLayoutManager( MainActivity.this , 3 ));
                myAdapter.notifyDataSetChanged();
                break;

            //瀑布流
            case R.id.bt3:
                // new StaggeredGridLayoutManager( 2 , StaggeredGridLayoutManager.VERTICAL) 显示2列
                // new StaggeredGridLayoutManager( 2 , StaggeredGridLayoutManager.HORIZONTAL) 显示2行
                recyclerView.setLayoutManager( new StaggeredGridLayoutManager( 2 , StaggeredGridLayoutManager.VERTICAL));
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.itemDecoration1:
                removeAllItemDecoration();
                recyclerView.addItemDecoration( itemDecoration1 );
                break;
        }
    }

    /**
     * 清除所有的装饰器
     */
    void removeAllItemDecoration(){
        recyclerView.removeItemDecoration( itemDecoration1 );
    }

}
