package com.zyj.animator.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zyj} on 2016/11/29.
 * 瀑布流效果
 */

public class StaggeredAdapter extends  RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {
    private Context context;
    private List<String> list;
    private List<Integer> itemHeight ;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public StaggeredAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

        //随机生成item高度
        itemHeight = new ArrayList<>() ;
        for ( int i = 0 ; i < list.size() ; i++ ){
            itemHeight.add((int) (100 + Math.random() * 300)) ;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(
                R.layout.item , null));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,
                                 final int position) {

        //给item 设置高度
        ViewGroup.LayoutParams params =  holder.itemView.getLayoutParams() ;
        if ( params != null ){
            params.height = itemHeight.get( position ) ;
            holder.itemView.setLayoutParams( params ) ;
        }

        String info = list.get(position);

        holder.appName.setText( info );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView appName;

        public MyViewHolder(View view) {
            super(view);
            appName = (TextView) view.findViewById(R.id.item_name );
        }
    }

    /**
     * 添加数据
     * @param content
     * @param position
     */
    public void addItem( String content, int position) {
        list.add(position, content);
        itemHeight.add( position , (int) (100 + Math.random() * 300)) ;
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
        itemHeight.add((int) (100 + Math.random() * 300)) ;
        notifyItemInserted( list.size() );
    }

    /**
     * 删除数据
     * @param model
     */
    public void removeItem(String model) {
        int position = list.indexOf(model);
        list.remove(position);
        itemHeight.remove( position ) ;
        notifyItemRemoved(position);//Attention!
    }

    /**
     * 删除数据
     * @param position
     */
    public void removeItem( int position ){
        list.remove( position ) ;
        itemHeight.remove( position ) ;
        notifyItemRemoved( position );
    }
}
