package applusiana.subm4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class FavoriteFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public FavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        tabLayout= view.findViewById(R.id.tablayout);
        viewPager= view.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.AddFragment(new FavoriteMovieFragment(),getResources().getString(R.string.movie_title));
        viewPagerAdapter.AddFragment(new FavoriteTvFragment(), getResources().getString(R.string.tv_title));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
