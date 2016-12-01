package com.zyj.multi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import bean.DogItem;
import bean.DogItemViewProvider;
import bean.ImageItem;
import bean.ImageItemViewProvider;
import impl.OnItemClickListener;
import bean.TextItem;
import bean.TextItemViewProvider;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button1 , button2 , button3   ;
    private RecyclerView recyclerView ;
    private MultiTypeAdapter multiTypeAdapter ;
    /* Items 等价于 ArrayList<Object> */
    private Items items;
    private TextItemViewProvider textItemViewProvider ;
    private ImageItemViewProvider imageItemViewProvider ;
    private DogItemViewProvider dogItemViewProvider ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );

        button1 = (Button) findViewById( R.id.bt1 );
        button1.setOnClickListener( this );

        button2 = (Button) findViewById( R.id.bt2 );
        button2.setOnClickListener( this );

        button3 = (Button) findViewById( R.id.bt3 );
        button3.setOnClickListener( this );

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration( new DividerItemDecoration( this ));

        items = new Items();
        multiTypeAdapter = new MultiTypeAdapter( items ) ;

         /* 注册类型和 View 的对应关系 */
        multiTypeAdapter.register( TextItem.class, textItemViewProvider = new TextItemViewProvider());
        multiTypeAdapter.register( ImageItem.class, imageItemViewProvider = new ImageItemViewProvider());
        multiTypeAdapter.register( DogItem.class, dogItemViewProvider = new DogItemViewProvider());

         /* 模拟加载数据，也可以稍后再加载，然后使用
         * adapter.notifyDataSetChanged() 刷新列表 */
        for (int i = 0; i < 30; i++) {
            TextItem textItem = new TextItem() ;
            textItem.setName( "text: " + i ) ;
            items.add( textItem ) ;

            ImageItem imageItem = new ImageItem() ;
            imageItem.setName( "image: " + i );
            items.add( imageItem ) ;
        }

        recyclerView.setAdapter( multiTypeAdapter );

        textItemViewProvider.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if ( view.getId() == R.id.text ){
                    Toast.makeText( MainActivity.this , "text " + ((TextItem)items.get(position )).getName()  , Toast.LENGTH_SHORT ).show(); ;
                }else {
                    Toast.makeText( MainActivity.this , "ddd " + ((TextItem)items.get(position )).getName()  , Toast.LENGTH_SHORT ).show(); ;
                }
            }
        });

        imageItemViewProvider.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText( MainActivity.this , "image " + ((ImageItem)items.get(position )).getName()   , Toast.LENGTH_SHORT ).show(); ;
            }
        });

        dogItemViewProvider.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText( MainActivity.this , "image " + ((DogItem)items.get(position )).name   , Toast.LENGTH_SHORT ).show(); ;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {
            //线性列表
            case R.id.bt1:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                multiTypeAdapter.notifyDataSetChanged();
                break;

            //网格布局
            case R.id.bt2:
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                multiTypeAdapter.notifyDataSetChanged();
                break;

            //添加一种属性
            case R.id.bt3 :
                for (int i = 0; i < 10 ; i++) {
                    DogItem dogItem = new DogItem() ;
                    dogItem.name =  "dog: " + i   ;
                    items.add( 10+ i , dogItem ) ;
                }

                multiTypeAdapter.notifyItemRangeInserted( 10 ,10 );
                break;
        }
    }
}
