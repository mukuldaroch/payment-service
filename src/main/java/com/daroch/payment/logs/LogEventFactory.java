package com.daroch.payment.logs;

import com.daroch.payment.dto.ErrorResponse;
import org.springframework.stereotype.Component;

@Component
public class LogEventFactory {

  public LogEvent error(ErrorResponse response, Exception ex) {
    return build(response, ex, LogLevel.ERROR);
  }

  public LogEvent warn(ErrorResponse response, Exception ex) {
    return build(response, ex, LogLevel.WARN);
  }

  private LogEvent build(ErrorResponse response, Exception ex, LogLevel level) {

    return LogEvent.builder()
        .timestamp(response.getTimestamp())
        .level(level)
        .errorCode(response.getErrorCode())
        .message(response.getMessage())
        .exception(ex.getClass().getSimpleName())
        .path(response.getPath())
        .build();
  }
}
