package com.daroch.payment.exceptions;

public class BadRequestException extends RuntimeException {

  // No-arg constructor
  public BadRequestException() {
    super();
  }

  // Constructor with message
  public BadRequestException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public BadRequestException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public BadRequestException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
