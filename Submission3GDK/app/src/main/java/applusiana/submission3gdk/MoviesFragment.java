package applusiana.submission3gdk;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    private MovieAdapter adapter;
    private MainViewModel mainViewModel;
    private ProgressBar progressBar;


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        progressBar = rootView.findViewById(R.id.progressBar);
        adapter = new MovieAdapter(getActivity());
        adapter.notifyDataSetChanged();
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_movies_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getMovie().observe(this, getMovie);
        mainViewModel.setMovie("movie");
        showLoading(true);



        // Inflate the layout for this fragment
        return rootView;
    }

    private Observer<ArrayList<MovieModel>> getMovie = new Observer<ArrayList<MovieModel>>() {
        @Override
        public void onChanged(@Nullable ArrayList<MovieModel> items) {
            if(items != null) {
                adapter.setData(items);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
