package com.hzlx.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//注解的生效范围：只能生效于类上面
public @interface Controller {
}
