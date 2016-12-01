package bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zyj.multi.app.R;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by ${zyj} on 2016/12/1.
 */
public class DogItemViewProvider
        extends ItemViewProvider<DogItem, DogItemViewProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_dog_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull ViewHolder holder, @NonNull DogItem dogItem) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById( R.id.dog_image );
        }
    }
}