package com.example.linzhizhou.petchase_12_3;

import android.content.Context;
import android.icu.text.IDNA;

import java.util.ArrayList;
import java.util.List;

public class InfoBean {
    private String mTitle;
    private String mDescript;
    private boolean mEditor;

    public InfoBean()
    {
    }

    public InfoBean(String title, String descript, boolean editor)
    {
        mTitle = title;
        mDescript = descript;
        mEditor = editor;
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

    public boolean isEditor()
    {
        return mEditor;
    }

    public void setEditor(boolean editor)
    {
        mEditor = editor;
    }


    public static List<InfoBean> getList(Context context)
    {
        List<InfoBean> list = new ArrayList<>();
        list.add(new InfoBean("信号强度", "强", false));
        list.add(new InfoBean("电量百分比", "93%", false));
        list.add(new InfoBean("采集时间","2019-3-1",false));
        list.add(new InfoBean("设备电压", "待更改", false));

        list.add(new InfoBean("GPS（经度）", "<32.183602,118.699000>", false));
        list.add(new InfoBean("GPS（纬度）", "<32.185934,118.689972>", false));
        list.add(new InfoBean("LBS（经度）", "<待更改>", false));
        list.add(new InfoBean("LBS（纬度）", "<待更改>", false));

        return list;
    }
}
