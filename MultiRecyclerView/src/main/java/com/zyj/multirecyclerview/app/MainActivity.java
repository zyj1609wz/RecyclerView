package com.zyj.multirecyclerview.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyAdapter myAdapter ;
    private List<String> list = new ArrayList<>();

    private RecyclerView recyclerView ;
    private Button button1 , button2  , button_add , button_remove ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for ( int i = 0 ; i < 60 ; i ++ ) {
            list.add( "数据   " + i ) ;
        }
        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
        button1 = (Button) findViewById( R.id.bt1 );
        button2 = (Button) findViewById( R.id.bt2 );
        button_add = (Button) findViewById( R.id.add );
        button_remove = (Button) findViewById( R.id.remove );
        button1.setOnClickListener( this );
        button2.setOnClickListener( this );
        button_add.setOnClickListener( this );
        button_remove.setOnClickListener( this );

        myAdapter = new MyAdapter( this , list ) ;
        recyclerView.setLayoutManager( new LinearLayoutManager( this ));
        recyclerView.addItemDecoration( new DividerItemDecoration( this ));
        recyclerView.setAdapter( myAdapter );

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText( MainActivity.this , "click :" + list.get( position ) , Toast.LENGTH_SHORT).show();
            }
        });
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
            case R.id.add:
                myAdapter.addItem( "add date"  );
                break;
            case R.id.remove :
                myAdapter.removeItem( list.size() -1  ) ;
                break;
        }
    }
}
