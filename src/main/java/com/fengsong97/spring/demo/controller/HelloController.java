package com.fengsong97.spring.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fengsong97.spring.demo.entity.Book;
import com.fengsong97.spring.demo.request.BookCreateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mapstruct.Context;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 15:17
 **/
@RestController
@RequestMapping("/hello")
@Api(value = "hello相关", description = "hello相关接口")
public class HelloController {

    @ApiOperation(value = "获得详情，返回JSON", notes = "")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String bookFindOne(@PathVariable("id") Long id) {

        Book book = new Book();
        book.setId(id);
        book.setName("Think in java");
        book.setDescription("这是一本关于Java思想的书。");

        return JSONObject.toJSONString(book);
    }

    @ApiOperation(value = "创建一本书", notes = "")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String bookCreate(@ApiParam(value = "创建一本书的postData", required = true) @Valid @RequestBody BookCreateRequest bookCreateRequest,
                             @Context HttpServletRequest request) {

        return JSONObject.toJSONString(bookCreateRequest);
    }

    @ApiOperation(value = "返回字符串", notes = "")
    @GetMapping(value = "/string", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting() {
        return "Hello World";
    }
}
