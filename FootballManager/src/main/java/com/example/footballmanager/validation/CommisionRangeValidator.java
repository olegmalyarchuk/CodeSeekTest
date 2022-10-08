package com.example.footballmanager.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class CommisionRangeValidator implements ConstraintValidator<CommisionRange, Integer> {

  @Override
  public void initialize(CommisionRange constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Integer i, ConstraintValidatorContext constraintValidatorContext) {
    if (i != null && (i >= 0 && i <= 10)) return true;
    return false;
  }
}
