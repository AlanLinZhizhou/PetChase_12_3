package com.example.linzhizhou.petchase_12_3;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;


public class BindBean
{

    private String mTitle;
    private String mDescript;
    private boolean mEditor;

    public BindBean()
    {
    }

    public BindBean(String title, String descript, boolean editor)
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


    public static List<BindBean> getList(Context context)
    {
        List<BindBean> list = new ArrayList<>();
        list.add(new BindBean("设备绑定状态", "绑定成功", false));
        list.add(new BindBean("设备Sn号", "001221A003E6", false));
        list.add(new BindBean("AppID", "001", false));
        list.add(new BindBean("Product Key", "47d71eceec62", false));
        list.add(new BindBean("设备烧录情况", "已烧录成功", false));
        list.add(new BindBean("合作伙伴ID", "ec4f64cab2c94a891d08acf86", false));
        list.add(new BindBean("设备芯片", "MTK2503芯片组", false));
        list.add(new BindBean("技术支持平台", "智云服平台", false));

        return list;
    }
}
