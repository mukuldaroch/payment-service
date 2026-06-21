package com.daroch.payment.dto.bookingService;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class BookingItemResponse {

  private UUID bookingItemId;
  private UUID ticketTypeId;
  private int quantity;
  private BigDecimal pricePerTicket;
}
