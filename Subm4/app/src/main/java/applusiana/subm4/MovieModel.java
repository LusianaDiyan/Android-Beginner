package applusiana.subm4;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieModel implements Parcelable {
    private int id;
    private String title;
    private String overview;
    private String poster;
    private Double rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public static Creator<MovieModel> getCREATOR() {
        return CREATOR;
    }

    public MovieModel(){}

    public MovieModel(JSONObject jsonObject){
        try{
            int id = jsonObject.getInt("id");
            String title = jsonObject.getString("title");
            String overview = jsonObject.getString("overview");
            String poster = jsonObject.getString("poster_path");
            Double rate = jsonObject.getDouble("vote");

            this.id = id;
            this.title = title;
            this.overview = overview;
            this.poster = ("https://image.tmdb.org/t/p/w500" + poster);
            this.rate = rate;
            Log.d("Data Item", title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected MovieModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
        this.rate = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
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
        parcel.writeString(this.title);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster);
        parcel.writeValue(this.rate);
    }
}
