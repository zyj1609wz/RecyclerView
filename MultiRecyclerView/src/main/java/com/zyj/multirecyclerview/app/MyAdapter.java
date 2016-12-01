package com.zyj.multirecyclerview.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zyj} on 2016/11/29.
 */

public class MyAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> list;
    private LayoutInflater mLayoutInflater ;

    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        mLayoutInflater = LayoutInflater.from( context ) ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            View view1 = mLayoutInflater.inflate(R.layout.item_image, parent, false) ;
            return new ImageViewHolder( view1 );
        } else {
            View view2 = mLayoutInflater.inflate(R.layout.item , parent, false) ;
            return new MyViewHolder( view2 );
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder  holder,
                                 final int position) {

        if (holder instanceof MyViewHolder) {
            String info = list.get(position);
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.appName.setText( info );
        }else if ( holder instanceof  ImageViewHolder ){
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            imageViewHolder.imageView.setImageResource( R.mipmap.ic_launcher);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int pos = holder.getLayoutPosition() ;
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if ( position % 2 == 0 ){
            return ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() ;
        }else {
            return ITEM_TYPE.ITEM_TYPE_TEXT.ordinal() ;
        }
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

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView  imageView ;

        public ImageViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.item_image );
        }
    }

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
}
