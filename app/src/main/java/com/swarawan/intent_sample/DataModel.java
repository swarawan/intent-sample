package com.swarawan.intent_sample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rioswarawan on 3/28/18.
 */

public class DataModel implements Parcelable {
    String name;
    String email;
    String address;
    String umur;

    public DataModel() {
    }

    protected DataModel(Parcel in) {
        name = in.readString();
        email = in.readString();
        address = in.readString();
        umur = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(umur);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };
}
