package applusiana.moviecatalogue2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class MovieFragment extends Fragment implements MovieAdapter.MovieListener{
    private MovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    private ArrayList<Movie> listMovie;
    private ListMovieViewModel listMovieViewModel;
    private ProgressBar progressBar;

    public static Fragment newInstance() {
        return new MovieFragment();
    }

    private void showLoading(boolean loading){
        if (loading){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rvMovie = view.findViewById(R.id.rvMovie);
        progressBar = view.findViewById(R.id.pbList);
        movieAdapter = new MovieAdapter(getContext(), this);

        listMovieViewModel = ViewModelProvider.of(this).get(ListMovieViewModel.class);
        listMovieViewModel.getListMovie().observe(this, getListMovie);

        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(movieAdapter);

        showLoading(true);
        listMovieViewModel.setListMovies();
    }

    private Observer<ArrayList<Movie>> getListMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null){
                listMovie = movies;
                movieAdapter.setListMovie(movies);
            }
            showLoading(false);
        }
    };

    @Override
    public void onMovieItemClick(int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);

        intent.putExtra(DetailActivity.EXTRA_MOVIE, listMovie.get(position));
        startActivity(intent);

    }
}
