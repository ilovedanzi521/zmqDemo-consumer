package com.yss.zmqDemoconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;

/**
 * @author wanglei
 * @version 1.0.0
 * @ClassName ZmqTransport.java
 * @Description TODO
 * @createTime 2019年05月16日 17:39:00
 */
@Slf4j
public class ZmqTransport {
    private String host;
    private int port;
    public ZmqTransport(String host, int port){
        this.port = port;
        this.host = host;
    }
    public byte[] run(byte[] object){
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket requester = context.socket(SocketType.REQ);
//        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        requester.connect("tcp://"+host+":"+port);
        log.debug("input object="+object);
//        String request = "Hello";
        //核心业务处理

//        zmqHandler.hanler(object);

        requester.send(object, 0);

        byte[] reply = requester.recv(0);

        requester.close();
        context.term();
        return reply;
    }
}
