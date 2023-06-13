package com.hzlx.component;

import java.lang.reflect.Method;

//封装成Handler
public class InvocationHandler {
    //比如找到了UserController下的info，那么代表UserController实例对象，反射调用info方法
private  Object calssObject;
private Method method;

    public InvocationHandler() {
    }

    public InvocationHandler(Object calssObject, Method method) {
        this.calssObject = calssObject;
        this.method = method;
    }


    public Object getCalssObject() {
        return calssObject;
    }


    public void setCalssObject(Object calssObject) {
        this.calssObject = calssObject;
    }


    public Method getMethod() {
        return method;
    }


    public void setMethod(Method method) {
        this.method = method;
    }

}
