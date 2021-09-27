package applusiana.playmovie;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by USER on 10/02/2018.
 */

public class MovieAdapter extends CursorAdapter {
    public MovieAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, viewGroup, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tampilTitle = (TextView) view.findViewById(R.id.txt_title);
        TextView tampilRating = (TextView) view.findViewById(R.id.txt_rating);

        tampilTitle.setText(
                cursor.getString(
                        cursor.getColumnIndex(
                                FilmContract.FilmEntry.COLUMN_TITLE)));

        tampilRating.setText(
                cursor.getString(
                        cursor.getColumnIndex(
                                FilmContract.FilmEntry.COLUMN_RATING)));
    }
}
