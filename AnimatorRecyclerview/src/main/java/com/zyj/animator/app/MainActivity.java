package com.zyj.animator.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutRightItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutTopItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideScaleInOutRightItemAnimator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private StaggeredAdapter myAdapter ;
    private List<Bean> list = new ArrayList<>();

    private RecyclerView recyclerView ;
    private GridLayoutManager layoutManager ;

    private RecyclerView.ItemDecoration itemDecoration1 ;
    private Button add_bt , remove_bt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();

        add_bt = (Button) findViewById( R.id.add);
        remove_bt = (Button) findViewById( R.id.remove );
        add_bt.setOnClickListener( this ) ;
        remove_bt.setOnClickListener( this );

        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
        layoutManager = new GridLayoutManager( this , 4 , GridLayoutManager.VERTICAL , false ) ;

        myAdapter = new StaggeredAdapter( this , list ) ;
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( myAdapter );

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Bean bean = list.get(position);
                return bean.getType() ;
            }
        });

        myAdapter.setOnItemClickListener(new StaggeredAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText( MainActivity.this , "click :" + list.get( position ) , Toast.LENGTH_SHORT).show();
            }
        });

        itemDecoration1 = new DividerItemDecoration( this ) ;
        recyclerView.addItemDecoration( itemDecoration1 );

        //设置默认的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator()) ;

        //从屏幕底部飞进来的动画
        recyclerView.setItemAnimator( new SlideInOutBottomItemAnimator( recyclerView ));

        //从屏幕左侧飞进来的动画
        recyclerView.setItemAnimator( new SlideInOutLeftItemAnimator( recyclerView ));

        //从屏幕右侧飞进来的动画
        recyclerView.setItemAnimator( new SlideInOutRightItemAnimator( recyclerView ));

        //从屏幕顶部飞进来的动画
        recyclerView.setItemAnimator( new SlideInOutTopItemAnimator( recyclerView ));

        //缩放进入屏幕, (备注：测试的时候有bug )
        recyclerView.setItemAnimator( new ScaleInOutItemAnimator( recyclerView )) ;

        //缩放的同时从屏幕右侧飞进来  (备注：测试的时候有bug )
        recyclerView.setItemAnimator( new SlideScaleInOutRightItemAnimator( recyclerView ));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                myAdapter.addItem(  getBean( 2 )  );
                break;
            case R.id.remove :
                myAdapter.removeItem( list.size() -1 );
                break;
        }
    }

    void getData(){
        list.add( getBean( 1 ) ) ;
        list.add( getBean( 1 ) ) ;
        list.add( getBean( 1 ) ) ;
        list.add( getBean( 1 ) ) ;

        list.add( getBean( 1 ) ) ;
        list.add( getBean( 1 ) ) ;
        list.add( getBean( 2 ) ) ;

        list.add( getBean( 2 ) ) ;
        list.add( getBean( 2 ) ) ;

        list.add( getBean( 3 ) ) ;
        list.add( getBean( 1 ) ) ;

        list.add( getBean( 1 ) ) ;
        list.add( getBean( 3 ) ) ;

        list.add( getBean( 4 ) ) ;

        list.add( getBean( 1 ) ) ;
        list.add( getBean( 1 ) ) ;
        list.add( getBean( 1 ) ) ;
        list.add( getBean( 1 ) ) ;

        list.add( getBean( 2 ) ) ;
        list.add( getBean( 2 ) ) ;

        list.add( getBean( 3 ) ) ;
        list.add( getBean( 1 ) ) ;

        list.add( getBean( 1 ) ) ;
        list.add( getBean( 1 ) ) ;
        list.add( getBean( 2 ) ) ;

        list.add( getBean( 1 ) ) ;
        list.add( getBean( 3 ) ) ;

        list.add( getBean( 3 ) ) ;
        list.add( getBean( 1 ) ) ;

        list.add( getBean( 2 ) ) ;
        list.add( getBean( 1 ) ) ;
        list.add( getBean( 1 ) ) ;
    }

    int po = 0 ;

    private Bean getBean( int type ){
        Bean bean = new Bean() ;
        bean.setName(  "数据   " + po  );
        bean.setType( type );
        po ++ ;
        return bean  ;
    }
}
