package com.fengsong97.spring.demo.repositories;

import com.fengsong97.spring.demo.entity.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 17:29
 **/

public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
