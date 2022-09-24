package com.example.footballmanager.validation;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class PositiveDigitValidator implements ConstraintValidator<PositiveDigit, Integer> {

  @Override
  public void initialize(PositiveDigit constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Integer i, ConstraintValidatorContext constraintValidatorContext) {
    if (i == null || i < 0) {
      return false;
    }
    return true;
  }
}
