package com.yss.zmqDemoconsumer;

import com.yss.Result;
import com.yss.RpcRequest;
import com.yss.SerializationUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author wanglei
 * @version 1.0.0
 * @ClassName RemoteInvocationHandler.java
 * @Description TODO
 * @createTime 2019年05月17日 10:53:00
 */
public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host=host;
        this.port=port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setClazz(method.getDeclaringClass().getName());
        request.setMethod(method.getName());
        request.setParams(args);
        //序列化
        byte[] bytes = SerializationUtil.serialize(request);
        ZmqTransport zmqTransport = new ZmqTransport(host,port);
       byte[] result =  zmqTransport.run(bytes);
       //反序列化
        Result returnCode = SerializationUtil.deserialize(Result.class,result);
        return returnCode;
    }
}
