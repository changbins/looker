package com.wenhua.community.annotation;

/*
 * @Author:ChangBins
 * @Data:2022-09-13  13:13
 * @Description:community-com.wenhua.community.annotation
 * @Version：1.0
 * @Detail：
 * */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @LoginRequired 注解只起到标识的作用
 */
@Target(ElementType.METHOD)//规定这个注解应该放到什么位置
@Retention(RetentionPolicy.RUNTIME) //规定这个注解只在程序运行的时候才有效
public @interface LoginRequired {
}
