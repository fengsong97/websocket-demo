package com.fengsong97.spring.demo.services;

import com.fengsong97.spring.demo.entity.book.BookEntity;

import java.util.List;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/26 18:47
 **/

public interface BookService {

    BookEntity findOne(Long id);

    List<BookEntity> findAll();

    BookEntity save(BookEntity bookEntity);

    BookEntity put(BookEntity bookEntity);

    void delete(Long id);
}
