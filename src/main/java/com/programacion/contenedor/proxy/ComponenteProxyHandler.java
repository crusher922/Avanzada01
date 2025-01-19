package com.programacion.contenedor.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ComponenteProxyHandler implements InvocationHandler {
    private Object target;

    public ComponenteProxyHandler(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return this.target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long inicio = System.nanoTime();

        Object rst = method.invoke(this.target, args);

        long tiempo = (System.nanoTime() - inicio) ;

        System.out.printf("******metodo '%s', tiempo=%d\n", method.getName(), tiempo);


        return rst;
    }
}
