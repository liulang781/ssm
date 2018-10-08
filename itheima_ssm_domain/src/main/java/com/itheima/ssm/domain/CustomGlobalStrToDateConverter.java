package com.itheima.ssm.domain;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomGlobalStrToDateConverter implements Converter<String,Date> {


    /**
     * 必须要在springmvc.xml中配置
     * @param source
     * @return
     */
    @Override
    public Date convert(String source) {
        Date date=null;
       //判断传入的字符串是否为null
        if(source==null){
            throw new RuntimeException("日期不能为空");
        }
        try {
            DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
