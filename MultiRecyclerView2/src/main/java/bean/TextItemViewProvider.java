package bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyj.multi.app.R;

/**
 * Created by ${zyj} on 2016/12/1.
 */

public class TextItemViewProvider extends BaseItemViewProvider< TextItem , TextItemViewProvider.TextHolder> {
    @NonNull
    @Override
    protected TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_text, parent, false);
        return new TextHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull TextItem textItem) {
        setOnClick( holder ) ;
        holder.text.setText( textItem.getName() );
        setOnClick( holder.text , holder.getAdapterPosition() ) ;
    }

    static class TextHolder extends RecyclerView.ViewHolder {
        @NonNull
        private TextView text;

        TextHolder(@NonNull final View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text );
        }
    }
}
