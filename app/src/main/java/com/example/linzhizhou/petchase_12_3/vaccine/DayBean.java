package com.example.linzhizhou.petchase_12_3.vaccine;

import android.os.Parcel;
import android.os.Parcelable;


public class DayBean implements Parcelable
{
    private String mTitle;
    private String mTime;


    public DayBean()
    {
    }


    public DayBean(String time)
    {
        mTitle = "疫苗注射";
        mTime = time;
    }


    protected DayBean(Parcel in)
    {
        mTitle = in.readString();
        mTime = in.readString();
    }

    public static final Creator<DayBean> CREATOR = new Creator<DayBean>()
    {
        @Override
        public DayBean createFromParcel(Parcel in)
        {
            return new DayBean(in);
        }

        @Override
        public DayBean[] newArray(int size)
        {
            return new DayBean[size];
        }
    };

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String title)
    {
        mTitle = title;
    }

    public String getTime()
    {
        return mTime;
    }

    public void setTime(String time)
    {
        mTime = time;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mTitle);
        parcel.writeString(mTime);
    }
}