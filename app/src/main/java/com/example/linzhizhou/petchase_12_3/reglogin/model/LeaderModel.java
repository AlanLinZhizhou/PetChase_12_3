package com.example.linzhizhou.petchase_12_3.reglogin.model;



public class LeaderModel
{
    private int title;
    private int descript;
    private int rescourceId;

    public LeaderModel(int mTitle, int mDescript, int mRescourceId)
    {
        title = mTitle;
        descript = mDescript;
        rescourceId = mRescourceId;
    }

    public int getTitle()
    {
        return title;
    }

    public void setTitle(int mTitle)
    {
        title = mTitle;
    }

    public int getDescript()
    {
        return descript;
    }

    public void setDescript(int mDescript)
    {
        descript = mDescript;
    }

    public int getRescourceId()
    {
        return rescourceId;
    }

    public void setRescourceId(int mRescourceId)
    {
        rescourceId = mRescourceId;
    }
}
