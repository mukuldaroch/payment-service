package com.daroch.payment.exceptions;

public class TicketServiceUnavailableException extends ServiceUnavailableException {

  public TicketServiceUnavailableException() {
    super("EVENT_SERVICE_UNAVAILABLE", "Event Service is unavailable at the time");
  }
  ;
}
