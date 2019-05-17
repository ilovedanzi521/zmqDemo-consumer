package com.yss.zmqDemoconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yss.*;
@RestController
public class UserController {
    private static int i=100;
    @Autowired
    RpcProxyClient rpcProxyClient;
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "test")
    public Result welcome() {
        IUserService iUserService = rpcProxyClient.clientProxy(IUserService.class,"localhost",1234);
        return iUserService.update(11);
    }

}
