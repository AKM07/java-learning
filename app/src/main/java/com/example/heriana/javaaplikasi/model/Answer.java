package com.example.heriana.javaaplikasi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by AKM on 7/2/2019.
 */
public class Answer implements Parcelable {

    private int order;
    private String answer;

    public Answer() {

    }

    public Answer(int order, String answer) {
        this.order = order;
        this.answer = answer;
    }

    protected Answer(Parcel in) {
        order = in.readInt();
        answer = in.readString();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public int getOrder() {
        return order;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.order);
        dest.writeString(this.answer);
    }
}
