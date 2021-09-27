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

public class TvFragment extends Fragment {
    private TvAdapter adapter;
    private MainViewModel mainViewModel;
    private ProgressBar progressBar;


    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tv, container, false);

        progressBar = rootView.findViewById(R.id.progressBar);
        adapter = new TvAdapter(getActivity());
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_tv_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getTv().observe(this,getTv);
        mainViewModel.setTv("tv");
        showLoading(true);

        return  rootView;
    }

    private Observer<ArrayList<TvModel>> getTv = new Observer<ArrayList<TvModel>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvModel> items) {
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
