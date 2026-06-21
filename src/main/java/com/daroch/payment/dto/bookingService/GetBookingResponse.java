package com.daroch.payment.dto.bookingService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class GetBookingResponse {

  private UUID bookingId;
  private UUID eventId;
  private BigDecimal totalPrice;
  private BookingStatusEnum bookingStatus;
  private List<BookingItemResponse> items;

  // -------------------------------------------------dates
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime expiresAt;
}
