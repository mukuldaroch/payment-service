package com.daroch.payment.services;

import com.daroch.payment.dto.response.PaymentResponse;
import java.util.UUID;
import org.springframework.security.oauth2.jwt.Jwt;

public interface PaymentService {

  PaymentResponse randomPaymentStatus(Jwt jwt, UUID bookingId);
}
