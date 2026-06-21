package com.daroch.payment.exceptions;

public class BookingNotFoundException extends RuntimeException {

  // No-arg constructor
  public BookingNotFoundException() {
    super();
  }

  // Constructor with message
  public BookingNotFoundException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public BookingNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public BookingNotFoundException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public BookingNotFoundException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
