package com.fengsong97.spring.demo.entity.book;

import com.fengsong97.spring.demo.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 16:39
 **/
@Data
@Entity
@Table(name = "book")
public class BookEntity extends BaseEntity {

    /**
     * 书名
     */
    @ApiModelProperty(value = "书名")
    private String name;

    private String description;

    /**
     * 作者
     */
    @JoinColumn(name = "author_id",unique=true)
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private AuthorEntity authorEntity;

    @JoinColumn(name = "book_id",unique=true)
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<PressEnity> pressEnity;
}
