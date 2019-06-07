package com.fw;

/**
 * @ClassName
 * @Description TODO
 * @Author li
 * @Date 2019/6/7 8:47
 **/
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("请求sayHello方法:" + content);
        return "say hello :" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("请求saveuser:" + user);
        return "success";
    }
}
