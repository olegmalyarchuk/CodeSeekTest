package com.example.footballmanager.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
public class PositiveDigitValidatorTest {
  @InjectMocks private PositiveDigitValidator positiveDigitValidator;

  @Mock private ConstraintValidatorContext context;

  @Test
  void givenValidDigit() {
    // given
    int digit = 20;

    // when
    boolean isValidResult = positiveDigitValidator.isValid(digit, context);

    // then
    assertTrue(isValidResult);
  }

  @Test
  void givenNotValidPhone() {
    // given
    int digit = -20;

    // when
    boolean isValidResult = positiveDigitValidator.isValid(digit, context);

    // then
    assertFalse(isValidResult);
  }

  @Test
  void givenEmptyPhone() {
    // given
    Integer digit = null;

    // when
    boolean isValidResult = positiveDigitValidator.isValid(digit, context);

    // then
    assertFalse(isValidResult);
  }
}
