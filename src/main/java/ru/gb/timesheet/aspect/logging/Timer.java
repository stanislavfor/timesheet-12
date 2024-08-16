package ru.gb.timesheet.aspect.logging;

import org.slf4j.event.Level;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Timer {
    boolean enabled() default true;
    org.slf4j.event.Level level() default Level.DEBUG;
}
