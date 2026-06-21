package com.daroch.payment.kafka.event;

import com.daroch.payment.domain.entities.Payment;
import com.daroch.payment.domain.enums.PaymentStatus;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PaymentStatusUpdateEvent(
    UUID paymentId, UUID bookingId, BigDecimal amount, PaymentStatus status) {

  public static PaymentStatusUpdateEvent from(Payment payment) {
    return PaymentStatusUpdateEvent.builder()
        .paymentId(payment.getPaymentId())
        .bookingId(payment.getBookingId())
        .amount(payment.getAmount())
        .status(payment.getStatus())
        .build();
  }
}
