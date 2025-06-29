package com.sta4l0rd.lms.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void logMethodInfo(String str) {
        logger.info("{} - {}",
                Thread.currentThread().getStackTrace()[2].getMethodName(), str);
    }

    public static void logMethodError(String e) {
        logger.error("{} - {}",
                Thread.currentThread().getStackTrace()[2].getMethodName(), e);
    }

    public static void logMethodDebug(String e) {
        logger.debug("{} - {}",
                Thread.currentThread().getStackTrace()[2].getMethodName(), e);
    }
}
