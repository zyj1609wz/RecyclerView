package bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyj.multi.app.R;

import impl.BaseItemViewProvider;

/**
 * Created by ${zyj} on 2016/12/1.
 */
public class DogItemViewProvider
        extends BaseItemViewProvider<DogItem, DogItemViewProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_dog, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull ViewHolder holder, @NonNull DogItem dog) {

        setOnClick( holder.itemView , holder.getAdapterPosition() );
        holder.dogName_tv.setText( dog.name );
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dogName_tv ;
        ViewHolder(View itemView) {
            super(itemView);
            dogName_tv = (TextView) itemView.findViewById( R.id.dog_name );
        }
    }
}