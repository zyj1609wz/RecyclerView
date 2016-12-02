package com.pinnedsectionmultirecyclerview.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.pinnedsectionmultirecyclerview.app.bean.HeaderItem;
import com.pinnedsectionmultirecyclerview.app.bean.HeaderItemViewProvider;
import com.pinnedsectionmultirecyclerview.app.bean.Person;
import com.pinnedsectionmultirecyclerview.app.bean.PersonViewProvider;
import com.pinnedsectionmultirecyclerview.app.impl.DividerItemDecoration;
import com.pinnedsectionmultirecyclerview.app.impl.HeaderScrollMoreListener;
import com.pinnedsectionmultirecyclerview.app.impl.OnItemClickListener;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView ;
    private MultiTypeAdapter multiTypeAdapter ;
    /* Items 等价于 ArrayList<Object> */
    private Items items;
    private HeaderItemViewProvider headerItemViewProvider ;
    private PersonViewProvider personViewProvider  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration( new DividerItemDecoration( this ));

        items = new Items();

        multiTypeAdapter = new MultiTypeAdapter( items ) ;
         /* 注册类型和 View 的对应关系 */
        multiTypeAdapter.register( HeaderItem.class, headerItemViewProvider = new HeaderItemViewProvider( this ));
        multiTypeAdapter.register( Person.class, personViewProvider = new PersonViewProvider());

        HeaderItem headerItem = new HeaderItem() ;
        headerItem.name = "head 1 : "  ;
        items.add( headerItem ) ;

        for (int i = 0; i < 10; i++) {
            Person imageItem = new Person() ;
            imageItem.name =  "image: " +  i  ;
            items.add( imageItem ) ;
        }

        HeaderItem headerItem2 = new HeaderItem() ;
        headerItem2.name = "head 2 : "  ;
        items.add( headerItem2) ;

        for (int i = 0; i < 10; i++) {
            Person imageItem = new Person() ;
            imageItem.name =  "image: " +  i  ;
            items.add( imageItem ) ;
        }

        HeaderItem headerItem3 = new HeaderItem() ;
        headerItem3.name = "head 3 : "  ;
        items.add( headerItem3) ;

        for (int i = 0; i < 20; i++) {
            Person imageItem = new Person() ;
            imageItem.name =  "image: " +  i  ;
            items.add( imageItem ) ;
        }

        HeaderItem headerItem4 = new HeaderItem() ;
        headerItem4.name = "head 4 : "  ;
        items.add( headerItem4) ;

        for (int i = 0; i < 20; i++) {
            Person imageItem = new Person() ;
            imageItem.name =  "image: " +  i  ;
            items.add( imageItem ) ;
        }

        headerItemViewProvider.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if ( view.getId() == R.id.text ){
                    Toast.makeText( MainActivity.this , "text " + ((HeaderItem)items.get(position )).name  , Toast.LENGTH_SHORT ).show(); ;
                }else {
                    Toast.makeText( MainActivity.this , "ddd " + ((HeaderItem)items.get(position )).getName()  , Toast.LENGTH_SHORT ).show(); ;
                }
            }
        });

        personViewProvider.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText( MainActivity.this , "image " + ((Person)items.get(position )).getName()   , Toast.LENGTH_SHORT ).show(); ;
            }
        });

        recyclerView.addOnScrollListener( new HeaderScrollMoreListener( headerItemViewProvider ));
        recyclerView.setAdapter( multiTypeAdapter );

    }

    @Override
    public void onClick(View v) {

    }
}
