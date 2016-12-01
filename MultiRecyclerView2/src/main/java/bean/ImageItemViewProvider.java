package bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zyj.multi.app.R;

/**
 * Created by ${zyj} on 2016/12/1.
 */

public class ImageItemViewProvider extends BaseItemViewProvider< ImageItem , ImageItemViewProvider.ImageHolder> {

    @NonNull @Override
    protected ImageHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_image, parent, false);
        return new ImageHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ImageHolder holder, @NonNull ImageItem textItem) {
        setOnClick( holder );
        setOnClick( holder.imageView , holder.getAdapterPosition() );
        holder.imageView.setImageResource( R.mipmap.ic_launcher );
    }

    static class ImageHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final ImageView imageView ;

        ImageHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.image );
        }
    }
}
