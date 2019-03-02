package com.example.linzhizhou.petchase_12_3.pet.model;

public class DepositModel {
    private int image;
    private String names;
    private String times;

    public DepositModel(int image, String names, String times)
    {
        this.image = image;
        this.names = names;
        this.times = times;
    }

    public int getImage()
    {
        return image;
    }

    public void setImage(int image)
    {
        this.image = image;
    }

    public String getNames()
    {
        return names;
    }

    public void setNames(String names)
    {
        this.names = names;
    }

    public String getTimes()
    {
        return times;
    }

    public void setTimes(String times)
    {
        this.times = times;
    }
}
