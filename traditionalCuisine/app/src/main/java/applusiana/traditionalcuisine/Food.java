package applusiana.traditionalcuisine;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private int picture;
    private String name;
    private String description;

    public Food() {
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    protected Food(Parcel in) {
        this.picture = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.picture);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
    }
}
