package com.alphabet.aidlwithgenerictype;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alphabet on 4/12/16.
 */
public class Type1 implements Parcelable {
    public String name;

    public Type1(String name) {
        this.name = name;
    }

    public Type1(Parcel in) {
        this.name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new Type1(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[0];
        }
    };
}
