package com.example.footballmanager.exception;

import com.example.footballmanager.persistence.model.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class ServiceException extends RuntimeException {
  private ErrorType errorType;

  public ServiceException(String message) {
    super(message);
  }

  public ErrorType getErrorType() {
    return ErrorType.FATAL_ERROR_TYPE;
  }
}
