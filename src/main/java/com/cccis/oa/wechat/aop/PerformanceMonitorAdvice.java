package com.cccis.oa.wechat.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by ccc on 2017/7/28.
 */
public class PerformanceMonitorAdvice {

    public Object log(ProceedingJoinPoint call) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return call.proceed();
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("Take time: " + duration);
            /*if (duration > 500) {
                ELKLog.warningCommonLogTiming(LOG, call.toString(), duration);
            }*/
        }
    }
}
