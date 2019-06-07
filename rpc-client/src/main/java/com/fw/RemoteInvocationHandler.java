package com.fw;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName
 * @Description TODO
 * @Author li
 * @Date 2019/6/7 9:36
 **/
public class RemoteInvocationHandler implements InvocationHandler {

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private String host;

    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        Object result = rpcNetTransport.send(rpcRequest);
        return result;
    }
}
