package com.example.footballmanager.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositiveDigitValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveDigit {
  String message() default "Invalid digit format!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
