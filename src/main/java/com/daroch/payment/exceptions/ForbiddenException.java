package com.daroch.payment.exceptions;

public class ForbiddenException extends RuntimeException {

  // No-arg constructor
  public ForbiddenException() {
    super();
  }

  // Constructor with message
  public ForbiddenException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public ForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public ForbiddenException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public ForbiddenException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
