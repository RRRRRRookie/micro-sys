package com.broken.line.component;

import java.lang.annotation.*;

/**
 * @author: wanjia1
 * @date: 2023/6/12
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CustomizeMetric {

    String value() default "";
}
