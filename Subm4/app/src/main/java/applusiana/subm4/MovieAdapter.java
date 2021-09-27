package applusiana.subm4;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MovieModel> movieModels=new ArrayList<>();

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieModel> items) {
        movieModels.clear();
        movieModels.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.ViewHolder holder, final int position) {
        final MovieModel movieItems = movieModels.get(position);
        holder.movieName.setText(movieItems.getTitle());
        holder.rating.setText(Double.toString(movieItems.getRate()));
        Glide.with(context)
                .load(movieItems.getPoster())
                .apply(new RequestOptions().override(350,550))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBarItemMovie.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imgPhoto);

        holder.Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveWithDataIntent = new Intent(context, MovieDetailActivity.class);
                moveWithDataIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movieModels.get(position));
                context.startActivity(moveWithDataIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView movieName;
        TextView rating;
        ConstraintLayout Layout;
        ProgressBar progressBarItemMovie;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto=itemView.findViewById(R.id.img_movie);
            movieName=itemView.findViewById(R.id.movie_name);
            Layout=itemView.findViewById(R.id.linear);
            rating=itemView.findViewById(R.id.item_scoreAngkaHome);
            progressBarItemMovie = itemView.findViewById(R.id.progressBar_itemMovie);
        }
    }
}
