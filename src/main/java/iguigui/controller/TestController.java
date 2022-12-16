package iguigui.controller;


import cn.hutool.http.server.HttpServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public Entity toString(@RequestParam String name) {
        System.out.println(name);
        Entity entity1 = new Entity();
        entity1.setName("yes");
        return entity1;
    }





    @RequestMapping("/test1")
    public DeferredResult<Void> test1(HttpServletResponse response) {
        DeferredResult<Void> objectDeferredResult = new DeferredResult<>(1000L);
        new Thread(() -> {
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            objectDeferredResult.setResult(null);
            response.setStatus(204);
        }).start();
        return objectDeferredResult;
    }


    @RequestMapping("/test2")
    public DeferredResult<Void> test2(HttpServerResponse response) {
            DeferredResult<Void> objectDeferredResult = new DeferredResult<>(1000L);
        return objectDeferredResult;
    }

}
