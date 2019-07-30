package me.qihao.reflection.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("ddd: " + proxy.getClass());
                String methodName = method.getName();
                Object result;
                if ("save".equals(methodName)) {
                    System.out.println("do sth before save");
                    result = method.invoke(target, args);
                    System.out.println("do sth after save");
                } else {
                    result = method.invoke(target, args);
                }
                return result;
            }
        });
    }
}
