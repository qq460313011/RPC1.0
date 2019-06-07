package com.fw;

/**
 * Hello world!
 *
 */
public class Server
{
    public static void main( String[] args )
    {

        RpcProxyServer proxyServer=new RpcProxyServer();

        proxyServer.pubulisher(8090);

    }
}
