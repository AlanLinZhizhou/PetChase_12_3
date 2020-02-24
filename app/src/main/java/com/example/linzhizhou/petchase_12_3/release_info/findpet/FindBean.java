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


//    public static List<FindBean> getList()
//    {
//        List<FindBean> list = new ArrayList<>();
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "上海嘉定区天祝路555弄丢失橘猫一只", "毛先生", "10秒钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "江苏省南京市江宁区胜太西路地铁站走失黑白色大型边牧", "郭小姐", "1分钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "上海静安区常德路昌平路附近丢失一只白色中型牛头梗", "王女士", "9分钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "上海闵行区顾戴路附近走丢一只棕色泰迪", "李女士", "17分钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "一只名为JELLY的布偶猫咪在书香公寓20号401室于傍晚时分不慎走失", "胡老师", "20分钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "上海闵行龙吴路紫龙路楚的嘉怡水岸附近走失比熊宠物狗", "陈女士", "21分钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "上海浦东张江药谷附近丢失长毛小型狗，黑色毛发带银灰色和黄色", "陈先生", "37分钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "山西省长治市路州区辛水巷18号丢失一只棕色短毛柯基", "吴女士", "43分钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "江苏省南京市浦口区丢失宠物", "郑先生", "58分钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "江苏省南京市鼓楼区丢失宠物", "何小姐", "1小时以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "湖南省长沙市岳麓区丢失宠物", "邓小姐", "1小时以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "山西省太原市迎泽区丢失宠物", "林先生", "2小时以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "辽宁省鞍山市千山区丢失宠物", "李女士", "4小时以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "江苏省泰州市姜堰区丢失宠物", "管小姐", "6小时以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "重庆市南川区丢失宠物", "程小姐", "9小时以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "重庆市合川区丢失宠物", "喻女士", "11小时以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "江苏省邳州市城区丢失宠物", "田小姐", "1天以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "江苏省徐州市鼓楼区丢失宠物", "孙先生", "2天钟以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "北京市朝阳区丢失宠物", "张女士", "2天以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "上海市长宁区丢失宠物", "白小姐", "4天以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "江苏省南京市六合区丢失宠物", "吴女士", "一周以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "浙江省杭州市丢失宠物", "张先生", "一周以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "山西省太原市小店区丢失宠物", "潘小姐", "一周以前"));
//        list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "江苏省无锡市丢失宠物", "连女士", "一周以前"));
//
//        return list;
//    }
}
