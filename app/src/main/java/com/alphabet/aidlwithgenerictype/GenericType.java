package com.alphabet.aidlwithgenerictype;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alphabet on 4/12/16.
 */
public class GenericType <T extends Parcelable> implements Parcelable {
    private String msg;
    private Class classType;
    private T value;

    public GenericType(String msg, Class classType, T value) {
        this.msg = msg;
        this.classType = classType;
        this.value = value;
    }

    public GenericType(Parcel in) {
        this.msg = in.readString();
//        this.classType = (Class) in.readValue(Class.class.getClassLoader());
        Class clazz = null;
        try {
            clazz = Class.forName(msg);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.value = (T) in.readValue(clazz.getClassLoader());
    }

    public String getMsg() {
        return msg;
    }

    public Class getClassType() {
        return classType;
    }

    public T getValue() {
        return value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
//        dest.writeValue(classType);
        dest.writeValue(value);
    }

    public static final Creator<GenericType> CREATOR = new Creator<GenericType>() {
        @Override
        public GenericType createFromParcel(Parcel source) {
            return new GenericType(source);
        }

        @Override
        public GenericType[] newArray(int size) {
            return new GenericType[0];
        }
    };
}
