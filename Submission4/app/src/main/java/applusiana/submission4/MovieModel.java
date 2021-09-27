package applusiana.submission4;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class MovieModel implements Parcelable {
    private int id;
    private String name;
    private String overview;
    private String poster;
    private Double rate;

    public MovieModel() {
    }

    public MovieModel(JSONObject object) {
        try {
            int id = object.getInt("id");
            String name = object.getString("title");
            String overview = object.getString("overview");
            Double rate= object.getDouble("vote_average");
            String poster = object.getString("poster_path");
            this.id = id;
            this.name = name;
            this.overview = overview;
            this.rate = rate;
            this.poster = ("https://image.tmdb.org/t/p/w500" + poster);
            Log.d("Data Item", name);
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    protected MovieModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
        this.rate = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster);
        parcel.writeValue(this.rate);
    }
}
