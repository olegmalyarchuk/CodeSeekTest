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
public class CommisionRangeValidatorTest {
  @InjectMocks private CommisionRangeValidator commisionRangeValidator;

  @Mock private ConstraintValidatorContext context;

  @Test
  void givenValidCommision() {
    // given
    int commision = 9;

    // when
    boolean isValidResult = commisionRangeValidator.isValid(commision, context);

    // then
    assertTrue(isValidResult);
  }

  @Test
  void givenNotValidCommision() {
    // given
    int commision = 11;

    // when
    boolean isValidResult = commisionRangeValidator.isValid(commision, context);

    // then
    assertFalse(isValidResult);
  }

  @Test
  void givenEmptyCommision() {
    // given
    Integer commision = null;

    // when
    boolean isValidResult = commisionRangeValidator.isValid(commision, context);

    // then
    assertFalse(isValidResult);
  }
}
