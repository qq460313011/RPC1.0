package com.fw;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8090);

       String result= helloService.sayHello("1234");

        System.out.println(result);
    }
}
