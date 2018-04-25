package com.fengsong97.spring.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 18:33
 **/
@RestController
@RequestMapping("/")
@Api(value = "hello", description = "hello相关接口")
public class HelloController {

    
    @ApiOperation(value = "欢迎接口", notes = "")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "客人") String name) {
        return "Hello " + name;
    }
}
