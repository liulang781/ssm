package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.LogAopDao;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.LogAopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogAopServiceImpl implements LogAopService {
    @Autowired
    private LogAopDao logAopDao;

    @Override
    public void saveSysLog(SysLog sysLog) {
        logAopDao.save(sysLog);
    }
}
