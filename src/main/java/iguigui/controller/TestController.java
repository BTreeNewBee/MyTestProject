package iguigui.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
