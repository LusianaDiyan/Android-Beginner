package applusiana.submission3gdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class TvModel implements Parcelable {
    private int id;
    private String name;
    private String overview;
    private String poster;

    public TvModel(JSONObject object) {
        try {
            int id = object.getInt("id");
            String name = object.getString("name");
            String overview = object.getString("overview");
            String poster = object.getString("poster_path");
            this.id = id;
            this.name = name;
            this.overview = overview;
            this.poster = ("https://image.tmdb.org/t/p/w185" + poster);
            Log.d("Data Items name", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeString(this.poster);
    }

    protected TvModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
    }

    public static final Parcelable.Creator<TvModel> CREATOR = new Parcelable.Creator<TvModel>() {
        @Override
        public TvModel createFromParcel(Parcel source) {
            return new TvModel(source);
        }

        @Override
        public TvModel[] newArray(int size) {
            return new TvModel[size];
        }
    };
}
