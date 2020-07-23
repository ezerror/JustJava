package me.ezerror.util.sqlbuilder;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Entity {
    String[] not_null_field() default {};

    String[] ignore_update_field() default {};

    String[] stream_field() default {};

    String[] ignore_insert_field() default {};

    String[] id();

    String table();

    String[] ignore_field() default {};

    String[] basic_field() default {};
}