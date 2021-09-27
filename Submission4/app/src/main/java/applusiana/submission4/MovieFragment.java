package applusiana.submission4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MovieFragment extends Fragment {
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private android.widget.ProgressBar ProgressBar;


    public MovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView =  view.findViewById(R.id.rv_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new MoviesAdapter(getActivity());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);
        model.getMovie().observe(this, getMovie);
        model.setListMovies("movie");
        ProgressBar = view.findViewById(R.id.progressBar);
        ProgressBar.setVisibility(View.VISIBLE);
        return view;
    }


    private Observer<ArrayList<MovieModel>> getMovie = new Observer<ArrayList<MovieModel>>() {
        @Override
        public void onChanged(ArrayList<MovieModel> movies) {
            if(movies != null) {
                adapter.setData(movies);
                ProgressBar.setVisibility(View.GONE);
            }
        }
    };

}
