package com.blog.constant;

/**
 * 有关session的一些配置
 */
public class SessionConstant {
    public static final Integer SESSION_TIME_SET = 60;//设置session的最大待操作时间，单位秒
    public static final Long SESSION_EXIST_TIME_SET = 60000L;//设置session最大测存在时间，单位毫秒
    //一些需要设置的常量key名称
    public static final String UUID_KEY = "uuid";
    public static final String START_KEY = "start";
    public static final String CODE_KEY = "code";
}
