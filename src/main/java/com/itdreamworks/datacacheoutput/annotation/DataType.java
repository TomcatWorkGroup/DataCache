package com.itdreamworks.datacacheoutput.annotation;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataType {
    boolean required() default true;
}
