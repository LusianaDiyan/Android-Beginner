package applusiana.moviecatalogue2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import applusiana.moviecatalogue2.api.TheMovieDBAPI;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Movie> listMovie;
    private MovieListener movieListener;

    public MovieAdapter(Context context){
        this.context = context;
        this.movieListener = movieListener;
        listMovie = new ArrayList<>();
    }

    public ArrayList<Movie> getListMovie(){
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie){
        this.listMovie = listMovie;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new MovieViewHolder(view, movieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.bind(listMovie.get(i));
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul;
        ImageView ivPoster;
        public MovieViewHolder(@NonNull View itemView, MovieListener movieListener) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tvJudul);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            this.movieListener =  movieListener;

            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            tvJudul.setText(movie.getTitle());
            Picasso.get().load(TheMovieDBAPI.getPoster(movie.getPosterPath())).into(ivPoster);
        }

        @Override
        public void onClick(View view){
            movieListener.onMovieItemClick(getAdapterPosition());
        }
    }

    public interface MovieListener{
        void onMovieItemClick(int position);
    }
}
