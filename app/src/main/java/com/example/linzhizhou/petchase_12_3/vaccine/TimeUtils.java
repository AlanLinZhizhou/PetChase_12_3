package com.example.linzhizhou.petchase_12_3.vaccine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TimeUtils
{

    /*计算时间差值*/
    public static String getTimeDifference(String endTime)
    {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date parse1 = dateFormat.parse(endTime);
            long diff = parse1.getTime() - System.currentTimeMillis();
            long day = diff / (24 * 60 * 60 * 1000);
            timeString = String.valueOf(day);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return timeString;
    }

    public static String getDayofWeek(String dateTime)
    {
        String time = "";
        Calendar cal = Calendar.getInstance();
        if (dateTime.equals(""))
        {
            cal.setTime(new Date(System.currentTimeMillis()));
        } else
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date;
            try
            {
                date = sdf.parse(dateTime);
            } catch (ParseException e)
            {
                date = null;
                e.printStackTrace();
            }
            if (date != null)
            {
                cal.setTime(new Date(date.getTime()));
            }
        }
        int day = cal.get(Calendar.DAY_OF_WEEK);
        switch (day)
        {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
        }
        return time;
    }


}
