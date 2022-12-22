package org.example;
import java.lang.annotation.*;

/**
 * An interface that highlights the fields required for implementation
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AutoInjectable {}