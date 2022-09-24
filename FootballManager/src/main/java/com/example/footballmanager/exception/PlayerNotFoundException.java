package com.example.footballmanager.exception;

import com.example.footballmanager.persistence.model.ErrorType;

public class PlayerNotFoundException extends ServiceException {

  public PlayerNotFoundException(String message) {
    super(message);
  }

  public PlayerNotFoundException() {
    super("Player was not found");
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.DB_ERROR_TYPE;
  }
}
