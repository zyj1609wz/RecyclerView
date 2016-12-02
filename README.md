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


# 项目介绍
## LayoutManagerRecyclerView
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


### ItemDecorationRecyclerView
- recyclerview的Item装饰器 ItemDecoration

  效果图
  
  ![](/pic/gif1.gif)
  
  ItemDecoration 常见的方法
  
  ![](/pic/pic1.png)


### SectionRecyclerView
- 标签功能

## PinnedSectionRecyclerView
- 黏性标签功能

## AnimatorRecyclerview item 动画效果
- 默认动画
```
  //设置默认的动画
  recyclerView.setItemAnimator( new DefaultItemAnimator()) ;
```

- 第三动画库 https://github.com/gabrielemariotti/RecyclerViewItemAnimators

```
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
  
```

- 其他动画开源库
https://github.com/wasabeef/recyclerview-animators


## MultiRecyclerView 
- recyclerview的复杂布局

效果图

![](/pic/multi_item.gif)

## MultiRecyclerView2 
   这个项目是复杂布局分发框架 MultiType的使用

- MultiType 是什么？
 > MultiType 就是一个多类型列表视图的中间分发框架，它能帮助你快速并且清晰地开发一些复杂的列表页面。它本是为聊天页面开发的，聊天页面的消息类型也是有大量不同种类，并且新增频繁，而 MultiType 能够轻松胜任，代码模块化，随时可拓展新的类型进入列表当中。它内建了 类型 - View 的复用池系统，支持 RecyclerView，使用简单灵活，令代码清晰、拥抱变化。  


- github:

 > [https://github.com/drakeet/MultiType](https://github.com/drakeet/MultiType)
 > 
 > [https://github.com/drakeet/MultiTypeTemplates](https://github.com/drakeet/MultiTypeTemplates)
  
- 相关博客：

 > [Android 复杂的多类型列表视图新写法：MultiType](https://drakeet.me/multitype)
 > 
 > [Android 复杂的列表试图新写法 MultiType](http://gank.io/post/5823bcf6421aa90e799ec2ad)
 >
 >[Android 复杂的列表视图新写法: MultiType 详解篇](https://drakeet.me/effective-multitype)
  
- Android 项目引用
 > `compile 'me.drakeet.multitype:multitype:2.2.2'`

效果图：

![](/pic/MultiType.gif)

## RefreshRecyclerView 
   这个项目是实现下拉刷新，滑到底部自动加载的

## 参考资料
- [明日之星-RecyclerView](http://www.imooc.com/learn/424)
- [RecyclerView使用详解（二） 复杂布局](http://frank-zhu.github.io/android/2015/02/25/android-recyclerview-part-2/)
- [Android 复杂的列表试图新写法 MultiType](http://gank.io/post/5823bcf6421aa90e799ec2ad)
- []()
- []()