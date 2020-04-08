package com.example.linzhizhou.petchase_12_3.release_info.findpet;

import java.util.ArrayList;
import java.util.List;


public class FindBean
{
    private String mUrls;
    private String mTitle;
    private String mDescript;
    private String mTime;

    public FindBean()
    {
    }

    public FindBean(String urls, String title, String descript, String time)
    {
        mUrls = urls;
        mTitle = title;
        mDescript = descript;
        mTime = time;
    }


    public String getUrls()
    {
        return mUrls;
    }

    public void setUrls(String urls)
    {
        mUrls = urls;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String title)
    {
        mTitle = title;
    }

    public String getDescript()
    {
        return mDescript;
    }

    public void setDescript(String descript)
    {
        mDescript = descript;
    }

    public String getTime()
    {
        return mTime;
    }

    public void setTime(String time)
    {
        mTime = time;
    }



}
