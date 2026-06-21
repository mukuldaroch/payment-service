package com.daroch.payment.exceptions;

import lombok.Getter;

@Getter
public class ServiceFailedException extends RuntimeException {

  private final String errorCode;

  public ServiceFailedException(String errorCode, String message) {

    super(message);
    this.errorCode = errorCode;
  }
}
