package com.daroch.payment.exceptions;

import lombok.Getter;

@Getter
public class ServiceUnavailableException extends RuntimeException {

  private final String errorCode;

  public ServiceUnavailableException(String errorCode, String message) {

    super(message);
    this.errorCode = errorCode;
  }
}
