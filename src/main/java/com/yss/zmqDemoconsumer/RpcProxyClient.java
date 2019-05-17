package com.yss.zmqDemoconsumer;

import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;

/**
 * @author wanglei
 * @version 1.0.0
 * @ClassName RpcProxyClient.java
 * @Description TODO
 * @createTime 2019年05月17日 10:48:00
 */
@Service(value = "rpcProxyClient")
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaceClz,final String host,int port){
        return (T) Proxy.newProxyInstance(interfaceClz.getClassLoader(),
                new Class<?>[]{interfaceClz},
                new RemoteInvocationHandler(host,port) );
    }
}
