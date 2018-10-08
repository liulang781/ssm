package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.LogAopService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogAopService logAopService;
    private Date visitTime;//访问时间
    private Class clazz;//访问类
    private Method method;//访问方法

    /**
     * 前置通知在方法执行前执行的内容,通过前置通知可以获取访问的时间,以及访问的方法对象,和方法所在的类
     * @param joinPoint
     * @throws NoSuchMethodException
     */
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {

        //前置通知:获取访问时间
        visitTime = new Date();
        //获取访问的类
        clazz = joinPoint.getTarget().getClass();
        //获取访问的方法名称
        String methodName = joinPoint.getSignature().getName();
        //先获取方法的参数列表
        Object[] args = joinPoint.getArgs();

        //获取具体执行的method方法对象
        if(args==null||args.length==0){
            //获取方法的对象,该方法对象获取到的是无参数的
            method=clazz.getMethod(methodName);
        }else {
            Class[] classArgs=new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                //将参数的Class存储到Class数组中
                classArgs[i]=args[i].getClass();
            }
                //该方法参数1:方法名称 2:Class[]中的元素即为参数
                method=clazz.getMethod(methodName,classArgs);
        }
    }

    /**
     * 后置通知是目标方法执行完成后完成的方法,可以获取访问结束的时长,整个访问的url,获取当前操作的用户信息,获取访问的ip
     * @param joinPoint
     */
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint){
        //获取访问的时长:时间毫秒值
       long time= new Date().getTime()-visitTime.getTime();

       String url="";
       if(clazz!=null&&clazz!=LogAop.class&&method!=null) {
           //获取访问的url,即类上的@RequestMapping+方法上的@RequestMapping
           //采用反射机制,已经获取到当前访问的类了,所以通过注解反射可以获取到当前访问的类上的注解值
           RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
           //判断类注解是否为空
           if (classAnnotation != null) {
               //获取到了@RequestMapping的类对象则就可以获取该注解的值
               String classUrl = classAnnotation.value()[0];
               //同理
               RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
               if (methodAnnotation != null) {
                   //获取值是一个数组,因为方法可能是被重载
                   String methodUrl = methodAnnotation.value()[0];
                   //获取访问的方法
                   url = classUrl + methodUrl;

                   //当全部满足条件时,说明由用户访问,即可获得当前用户访问ip
                   //获取ip需要request对象,所以需要在Web.xml文件中配置一个HttpServletRequest的监听器
                   //获取访问ip
                   String ip = request.getRemoteAddr();
                   //获取当前操作的用户
                   SecurityContext context = SecurityContextHolder.getContext();//从上下文获取当前操作室用户
                   //从spring-security框架中得到User对象
                   User user = (User) context.getAuthentication().getPrincipal();
                   //获取当前操作的用户名
                   String username = user.getUsername();

                   //以上全部获取到的信息封装到日志类中再存入数据库中
                   SysLog sysLog= new SysLog();
                   sysLog.setExecutionTime(time);
                   sysLog.setVisitTime(visitTime);
                   sysLog.setIp(ip);
                   sysLog.setUrl(url);
                   sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                   sysLog.setUsername(username);
                   //调用Service保存日志信息
                   logAopService.saveSysLog(sysLog);
               }
           }
       }
    }
}
