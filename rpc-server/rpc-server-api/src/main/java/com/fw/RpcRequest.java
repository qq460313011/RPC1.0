package com.fw;

import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author li
 * @Date 2019/6/7 9:03
 **/
public class RpcRequest implements Serializable {


    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    private String methodName;
    private Object[] parameters;

}
