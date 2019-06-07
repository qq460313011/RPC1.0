package com.fw;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName
 * @Description TODO
 * @Author li
 * @Date 2019/6/7 8:51
 **/
public class RpcProxyServer {

    //线程池
    ExecutorService executorService= Executors.newCachedThreadPool();

    public void pubulisher(int port) {
        System.out.println(port+"开启监听....");
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            while (true) {

                Socket socket = serverSocket.accept();
                //处理请求
                executorService.execute(new ProcessorHandler(socket,new HelloServiceImpl()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket!=null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
