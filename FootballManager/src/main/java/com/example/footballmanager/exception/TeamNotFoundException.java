package com.example.footballmanager.exception;

import com.example.footballmanager.persistence.model.ErrorType;

public class TeamNotFoundException extends ServiceException {

  public TeamNotFoundException(String message) {
    super(message);
  }

  public TeamNotFoundException() {
    super("Team was not found");
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.DB_ERROR_TYPE;
  }
}
