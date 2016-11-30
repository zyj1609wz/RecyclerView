# RecyclerView
RecyclerView不再负责显示工作,用法比ListView更解耦。和ListView不一样的是，RecyclerView不再负责Item的摆放等显示方面的功能。所有和布局、绘制等方面的工作Google都其拆分成不同的类进行管理。所以开发者可以自定义各种各样满足定制需求的的功能类。

这个是RecycleView的常见用法

| 类名        | 说明      |
| ------------- |:-------------:|
| RecyclerView.Adapter      | 托管数据集合，为每个Item创建视图 |
| RecyclerView.ViewHolder     | 承载Item视图的子视图      |
| RecyclerView.LayoutManager	| 负责Item视图的布局 |
| RecyclerView.ItemDecoration	| 为每个Item视图添加子视图，在Demo中被用来绘制Divider |
| RecyclerView.ItemAnimator  | 负责添加、删除数据时的动画效果 |


## 项目介绍
### app
- recyclerview的线性布局

>    ` recyclerView.setLayoutManager( new LinearLayoutManager( this ));`

- recyclerview的网格布局

>  `recyclerView.setLayoutManager( new GridLayoutManager( MainActivity.this , 3 ));`
    
- recyclerview的瀑布流布局

>  `recyclerView.setLayoutManager( new StaggeredGridLayoutManager( 2 , StaggeredGridLayoutManager.VERTICAL));`    

- recyclerview的增加数据

>    `myAdapter.notifyItemInserted( size );`

- recyclerview的删除数据

>    ` myAdapter.notifyItemRemoved( 0 );`

- recyclerview的刷新数据

>    ` myAdapter.notifyDataSetChanged();`

- 更新数据的方法

```
 /**
     * 添加数据
     * @param content
     * @param position
     */
    public void addItem( String content, int position) {
        list.add(position, content);
        notifyItemInserted(position);
    }

    /**
     * 增加数据
     * @param content
     */
    public void addItem( String content ){
        if ( list == null ) {
            list = new ArrayList<>() ;
        }
        list.add( list.size() , content );
        notifyItemInserted( list.size() );
    }

    /**
     * 删除数据
     * @param model
     */
    public void removeItem(String model) {
        int position = list.indexOf(model);
        list.remove(position);
        notifyItemRemoved(position);//Attention!
    }

    /**
     * 删除数据
     * @param position
     */
    public void removeItem( int position ){
        list.remove( position ) ;
        notifyItemRemoved( position );
    }

```


### app2
- recyclerview的Item装饰器 ItemDecoration
  
  ![](/pic/pic1.png)


### MultiRecyclerView 
- recyclerview的复杂布局
