package com.fengsong97.spring.demo.request;

import lombok.Data;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 17:01
 **/
@Data
public class BookEntityRequest {

    private String name;
    private String desciption;

    private String authorName;
    private String pressName;

}
