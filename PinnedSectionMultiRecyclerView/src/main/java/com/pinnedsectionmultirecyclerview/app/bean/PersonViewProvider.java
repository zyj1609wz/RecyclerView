package com.pinnedsectionmultirecyclerview.app.bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinnedsectionmultirecyclerview.app.R;
import com.pinnedsectionmultirecyclerview.app.impl.BaseItemViewProvider;

/**
 * Created by ${zyj} on 2016/12/2.
 */
public class PersonViewProvider
        extends BaseItemViewProvider<Person, PersonViewProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_person, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull ViewHolder holder, @NonNull Person person) {
      holder.name.setText( person.name );
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name ;
        ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById( R.id.person_name );
        }
    }
}