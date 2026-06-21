package com.daroch.payment.dto.response;

import com.daroch.payment.domain.enums.PaymentStatus;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {

  UUID paymentId;
  UUID bookingId;
  BigDecimal amount;
  PaymentStatus status;
  String gatewayPaymentId;
  String failureReason;
}
