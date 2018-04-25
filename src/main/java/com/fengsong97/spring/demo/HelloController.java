package com.fengsong97.spring.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 15:17
 **/
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String greeting() {
        return "Hello World";
    }
}
