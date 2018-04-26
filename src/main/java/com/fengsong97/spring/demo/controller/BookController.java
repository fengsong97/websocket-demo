package com.fengsong97.spring.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fengsong97.spring.demo.entity.book.AuthorEntity;
import com.fengsong97.spring.demo.entity.book.BookEntity;
import com.fengsong97.spring.demo.entity.book.PressEnity;
import com.fengsong97.spring.demo.repositories.BookRepository;
import com.fengsong97.spring.demo.request.BookEntityRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 15:17
 **/
@RestController
@RequestMapping("/book")
@Api(value = "book相关", description = "book相关接口")
public class BookController {

    @Autowired
    public BookRepository bookRepository;

    @ApiOperation(value = "查看一本书详情", notes = "")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String BookFindOne(@PathVariable("id") Long id,
                              @Context HttpServletRequest request) {

        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        return JSONObject.toJSONString(bookEntity);
    }

    @ApiOperation(value = "创建一本书", notes = "")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String BookCreate(@ApiParam(value = "创建一本书的data", required = true) @Valid @RequestBody BookEntityRequest bookEntityRequest,
                             @Context HttpServletRequest request) {

        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookEntityRequest.getName());
        bookEntity.setDescription(bookEntityRequest.getDesciption());

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(bookEntityRequest.getAuthorName());

        PressEnity pressEnity = new PressEnity();
        pressEnity.setName(bookEntityRequest.getPressName());

        List<PressEnity> pressEnityList = new ArrayList<PressEnity>();
        pressEnityList.add(pressEnity);

        bookEntity.setAuthorEntity(authorEntity);
        bookEntity.setPressEnity(pressEnityList);

        bookEntity = bookRepository.saveAndFlush(bookEntity);

        return JSONObject.toJSONString(bookEntity);
    }

    @ApiOperation(value = "更新一本书", notes = "")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String BookPut(@PathVariable("id") Long id, @ApiParam(value = "更新一本书的data", required = true) @Valid @RequestBody BookEntityRequest bookEntityRequest,
                          @Context HttpServletRequest request) {

        BookEntity old = bookRepository.findById(id).orElse(null);

        old.setName(bookEntityRequest.getName());
        old.setDescription(bookEntityRequest.getDesciption());
        old = bookRepository.save(old);

        return JSONObject.toJSONString(old);
    }

    @ApiOperation(value = "删除一本书", notes = "")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String BookDelete(@PathVariable("id") Long id, @Context HttpServletRequest request) {

        bookRepository.deleteById(id);
        return "已删除";
    }


}
