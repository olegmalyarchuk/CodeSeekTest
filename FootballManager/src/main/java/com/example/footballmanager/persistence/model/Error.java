package com.example.footballmanager.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {
  private String message;
  private ErrorType errorType;
  private LocalDateTime errorTime;
}
