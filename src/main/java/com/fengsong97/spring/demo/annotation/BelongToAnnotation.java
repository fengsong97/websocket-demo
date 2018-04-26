package com.fengsong97.spring.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by fengsong
 * 2018年01月30日16:40:35
 * 自定义注解验证属性 属于哪个金融公司
 */
@Target({ ElementType.FIELD })// 标注只能放在属性
@Retention(RetentionPolicy.RUNTIME)//在运行时有效
public @interface BelongToAnnotation {

    String value() default "all";
    String name() default "";
    String remark() default "";
}
