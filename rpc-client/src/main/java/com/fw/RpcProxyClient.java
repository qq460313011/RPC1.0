package com.fw;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName
 * @Description TODO
 * @Author li
 * @Date 2019/6/7 9:32
 **/
public class RpcProxyClient {

    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host,port));
    }

}
