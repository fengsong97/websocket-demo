package com.fengsong97.spring.demo.services.impl;

import com.fengsong97.spring.demo.entity.book.AuthorEntity;
import com.fengsong97.spring.demo.entity.book.BookEntity;
import com.fengsong97.spring.demo.entity.book.PressEnity;
import com.fengsong97.spring.demo.repositories.BookRepository;
import com.fengsong97.spring.demo.request.BookEntityRequest;
import com.fengsong97.spring.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/26 18:47
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public BookRepository bookRepository;

    @Override
    public BookEntity findOne(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        return bookEntity;
    }

    @Override
    public List<BookEntity> findAll() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return bookEntities;
    }

    @Override
    public BookEntity save(BookEntityRequest bookEntityRequest) {
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

        bookEntity = bookRepository.save(bookEntity);
        return bookEntity;
    }

    @Override
    public BookEntity put(Long id, BookEntityRequest bookEntityRequest) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);

        bookEntity.setName(bookEntityRequest.getName());
        bookEntity.setDescription(bookEntityRequest.getDesciption());
        bookEntity = bookRepository.save(bookEntity);
        return bookEntity;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);

    }
}
