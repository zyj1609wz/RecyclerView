package com.zyj.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by ${zyj} on 2016/11/29.
 */

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private Context context;
        private List<String> list;
        private ViewGroup.LayoutParams params;

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
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(
                    R.layout.item , null));
            params = holder.itemView.getLayoutParams() ;
            Random ra =new Random();
            params.height = ra.nextInt( 100 ) + 30 ;
            holder.itemView.setLayoutParams( params );
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder,
                                     final int position) {

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
}
