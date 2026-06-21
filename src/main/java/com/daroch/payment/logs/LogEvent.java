package com.daroch.payment.logs;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LogEvent {

  private Instant timestamp;
  private LogLevel level;
  private String errorCode;
  private String message;
  private String exception;
  private String path;
}
