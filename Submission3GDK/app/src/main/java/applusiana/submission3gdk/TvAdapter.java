package applusiana.submission3gdk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.MyViewHolder> {
    public TvAdapter(Context context) {
        this.context = context;
    }

    private Context context;
    private ArrayList<TvModel> mData = new ArrayList<>();
    public void setData(ArrayList<TvModel> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movies, parent, false);
        return new TvAdapter.MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.MyViewHolder holder, final int position) {
        final TvModel tvItems = mData.get(position);
        holder.tvName.setText(tvItems.getName());
        holder.tvFrom.setText(tvItems.getOverview());
        Glide.with(context)
                .load(tvItems.getPoster())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, mData.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent moveWithDataIntent = new Intent(context, DetailTvActivity.class);
                moveWithDataIntent.putExtra(DetailTvActivity.EXTRA_TV, mData.get(position));
                context.startActivity(moveWithDataIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvFrom;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
        }
    }
}
