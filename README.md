# RecyclerView
这个是RecycleView的常见用法

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
