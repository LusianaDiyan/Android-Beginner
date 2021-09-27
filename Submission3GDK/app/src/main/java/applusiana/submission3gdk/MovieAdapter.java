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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    public MovieAdapter(Context context) {
        this.context = context;
    }

    private Context context;
    private ArrayList<MovieModel> mData = new ArrayList<>();
    public void setData(ArrayList<MovieModel> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movies, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, final int position) {
        final MovieModel movieItems = mData.get(position);
        holder.tvName.setText(movieItems.getName());
        holder.tvFrom.setText(movieItems.getOverview());
        Glide.with(context)
                .load(movieItems.getPoster())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, mData.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent moveWithDataIntent = new Intent(context, DetailMovieActivity.class);
                moveWithDataIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, mData.get(position));
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
