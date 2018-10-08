package com.itheima.ssm.domain;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

    //日期格式转字符串
    public static String dateToString(Date date,String pattn){
        SimpleDateFormat sdf = new SimpleDateFormat(pattn);
        String format = sdf.format(date);
        return format;
    }

    //字符串转日期
    public static Date stringToDate(String dateStr,String pattn) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattn);
        Date date = sdf.parse(dateStr);
        return date;
    }

}
