# RecyclerView
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
`  recyclerView.setLayoutManager( new LinearLayoutManager( this ));`

- recyclerview的网格布局
`recyclerView.setLayoutManager( new GridLayoutManager( MainActivity.this , 3 ));`

- recyclerview的增加数据
`  myAdapter.notifyItemChanged( position );`

- recyclerview的删除数据
` myAdapter.notifyItemRemoved( 0 );`

- recyclerview的刷新数据
`myAdapter.notifyDataSetChanged();`

### app2
- recyclerview的瀑布流布局


### MultiRecyclerView 
- recyclerview的复杂布局
