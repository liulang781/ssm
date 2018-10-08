package com.itheima.ssm.domain;


import java.text.ParseException;
import java.util.Date;

/**
 * 系统日志用于封装日志信息,存储到数据库
 */
public class SysLog {
    private String id;
    private Date visitTime;//访问的时间
    private String visitTimeStr; //日期转换成字符串
    private String username;//访问的用户名
    private String ip;//访问的IP地址
    private String url; //访问的路径
    private Long executionTime; //访问的时长
    private String method; //访问的方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        if(visitTimeStr!=null){
            try {
                visitTime=DateUtils.stringToDate(visitTimeStr,"yyyy-MM-dd HH:mm:ss");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        if(visitTime!=null) {
            visitTimeStr = DateUtils.dateToString(visitTime, "yyyy-MM-dd HH:mm:ss");
        }
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
