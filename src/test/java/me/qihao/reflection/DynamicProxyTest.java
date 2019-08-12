package me.qihao.reflection;

import me.qihao.reflection.dynamicproxy.IUserDao;
import me.qihao.reflection.dynamicproxy.ProxyFactory;
import me.qihao.reflection.dynamicproxy.UserDao;
import org.junit.Test;

public class DynamicProxyTest {

    @Test
    public void testProxy() {
        IUserDao target = new UserDao();
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println("proxy class in test: " + proxy.getClass());
        proxy.save();
    }
}
