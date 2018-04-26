package com.fengsong97.spring.demo.services.impl;

import com.fengsong97.spring.demo.entity.book.BookEntity;
import com.fengsong97.spring.demo.repositories.BookRepository;
import com.fengsong97.spring.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/26 18:47
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public  BookRepository bookRepository;

    @Override
    public BookEntity findOne(Long id) {
        BookEntity bookEntity =bookRepository.findById(id).orElse(null);
        return bookEntity;
    }

    @Override
    public List<BookEntity> findAll() {
        List<BookEntity> bookEntities =bookRepository.findAll();
        return bookEntities;
    }

    @Override
    public BookEntity save(BookEntity bookEntity) {
        bookEntity = bookRepository.save(bookEntity);
        return bookEntity;
    }

    @Override
    public BookEntity put(BookEntity bookEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);

    }
}
