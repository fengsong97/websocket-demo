package com.fengsong97.spring.demo.services;

import com.fengsong97.spring.demo.entity.book.BookEntity;
import com.fengsong97.spring.demo.request.BookEntityRequest;

import java.util.List;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/26 18:47
 **/

public interface BookService {

    BookEntity findOne(Long id);

    List<BookEntity> findAll();

    BookEntity save(BookEntityRequest bookEntityRequest);

    BookEntity put(Long id, BookEntityRequest bookEntityRequest);

    void delete(Long id);
}
