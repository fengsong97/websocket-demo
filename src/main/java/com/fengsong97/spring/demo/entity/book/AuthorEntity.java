package com.fengsong97.spring.demo.entity.book;

import com.fengsong97.spring.demo.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/26 17:33
 **/
@Data
@Entity
@Table(name = "author")
public class AuthorEntity extends BaseEntity{
    private String name;
    private String sex;
}
