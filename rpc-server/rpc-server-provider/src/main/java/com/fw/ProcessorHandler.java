package com.fw;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @ClassName
 * @Description TODO
 * @Author li
 * @Date 2019/6/7 8:56
 **/
public class ProcessorHandler implements Runnable {

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    private Socket socket;

    private Object service;


    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            //输入流中请求信息，客户端相关信息
            //请求的方法名，参数
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();

            //通过传入的类名方法参数反射调用
            Object result = invoke(rpcRequest);


            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        finally {
            if (objectInputStream!=null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if (objectOutputStream!=null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * @param rpcRequest
     */
    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        //参数
        Object[] args = rpcRequest.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        //类信息
        Class clazz = Class.forName(rpcRequest.getClassName());
        //根据方法名和参数获取
        Method method = clazz.getMethod(rpcRequest.getMethodName(), types);

        Object result = method.invoke(service, args);

        return result;
    }
}
