package com.fengsong97.spring.demo.entity.book;

import com.fengsong97.spring.demo.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 16:39
 **/
@Data
@Entity
@Table(name = "book")
public class BookEntity extends BaseEntity {


    private String name;
    private String description;

}
