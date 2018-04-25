package com.fengsong97.spring.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 15:17
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping(value = "/string", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting() {
        return "Hello World";
    }
}
