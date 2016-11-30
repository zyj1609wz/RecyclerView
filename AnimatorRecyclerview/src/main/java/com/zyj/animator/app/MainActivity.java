package com.zyj.animator.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private StaggeredAdapter myAdapter ;
    private List<String> list = new ArrayList<>();

    private RecyclerView recyclerView ;

    private RecyclerView.ItemDecoration itemDecoration1 ;
    private Button add_bt , remove_bt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for ( int i = 0 ; i < 40 ; i ++ ) {
            list.add( "数据   " + i ) ;
        }

        add_bt = (Button) findViewById( R.id.add);
        remove_bt = (Button) findViewById( R.id.remove );
        add_bt.setOnClickListener( this ) ;
        remove_bt.setOnClickListener( this );

        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
        myAdapter = new StaggeredAdapter( this , list ) ;
        recyclerView.setLayoutManager( new StaggeredGridLayoutManager( 3 , StaggeredGridLayoutManager.VERTICAL  ));
        recyclerView.setAdapter( myAdapter );

        myAdapter.setOnItemClickListener(new StaggeredAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText( MainActivity.this , "click :" + list.get( position ) , Toast.LENGTH_SHORT).show();
            }
        });

        itemDecoration1 = new DividerItemDecoration( this ) ;
        recyclerView.addItemDecoration( itemDecoration1 );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                myAdapter.addItem( "dd");
                break;
            case R.id.remove :
                myAdapter.removeItem( list.size() );
                break;
        }
    }
}
