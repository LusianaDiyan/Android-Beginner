package applusiana.moviecatalogue2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    final Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        switch (i){
            case 0:
                fragment = MovieFragment.newInstance();
                break;
            case 1:
                fragment = TvFragment.newInstance();
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getResources().getString(R.string.tab_movie);
            case 1:
                return context.getResources().getString(R.string.tab_tv);
        }

        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
