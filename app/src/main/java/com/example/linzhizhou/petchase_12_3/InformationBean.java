package com.example.linzhizhou.petchase_12_3;

import java.util.ArrayList;
import java.util.List;

public class InformationBean {
    private String title;

    private String author;

    private String stars;

    private int resourceId;

    public InformationBean(String title, String author, String stars, int resourceId)
    {
        this.title = title;
        this.author = author;
        this.stars = stars;
        this.resourceId = resourceId;
    }


    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getStars()
    {
        return stars;
    }

    public void setStars(String stars)
    {
        this.stars = stars;
    }

    public int getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(int resourceId)
    {
        this.resourceId = resourceId;
    }
    public static List<InformationBean> getList()
    {
        List<InformationBean> list = new ArrayList<>();
        list.add(new InformationBean("拉布拉多和金毛的区别","Alice","已浏览5次",R.drawable.xuetang1));
        list.add(new InformationBean("不要再咬我的袜子","Bob","已浏览5次",R.drawable.xuetang2));
        list.add(new InformationBean("炎炎夏日，狂洗澡&剃光狗狗的毛就对了？","Sheldon","已浏览5次",R.drawable.xuetang4));
        list.add(new InformationBean("市面疫苗种类那么多，我们该怎么选择？","Kate","已浏览5次",R.drawable.xuetang5));
        list.add(new InformationBean("喜欢偷吃的喵星人","Kevin","已浏览5次",R.drawable.xuetang6));
        list.add(new InformationBean("与犬疫苗接种相关的常见问题","Bobo","已浏览5次",R.drawable.xuetang7));
        list.add(new InformationBean("母犬的假孕","Gabrielle","已浏览5次",R.drawable.xuetang8));
        list.add(new InformationBean("你的兔子有脚炎吗？","Sherlock","已浏览5次",R.drawable.xuetang9));
        list.add(new InformationBean("因肾衰竭而呕吐，抽搐的TA，在这里恢复了健康","Tom","已浏览5次",R.drawable.xuetang10));
        list.add(new InformationBean("夏天要格外小心体表寄生虫病！","Jerry","已浏览5次",R.drawable.xuetang11));
        return list;
    }

}
