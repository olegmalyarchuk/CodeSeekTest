package com.example.footballmanager.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CommisionRangeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommisionRange {
    String message() default "Invalid commision range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
