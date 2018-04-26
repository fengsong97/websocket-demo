package com.fengsong97.spring.demo.entity.book;

import com.fengsong97.spring.demo.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/26 17:27
 **/
@Data
@Entity
@Table(name = "press")
public class PressEnity extends BaseEntity {

    private String name;
    private String address;
}
