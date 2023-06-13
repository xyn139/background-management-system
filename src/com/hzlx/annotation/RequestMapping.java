package com.hzlx.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//声明注解的声明周期：RUNTIME表示运行时期有效
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    //允许该注解可以填String类型的参数，默认为空
    String value() default "";
}
