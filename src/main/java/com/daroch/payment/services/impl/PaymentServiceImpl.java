package com.daroch.payment.services.impl;

import com.daroch.payment.client.BookingServiceClient;
import com.daroch.payment.domain.entities.Payment;
import com.daroch.payment.domain.entities.PaymentHistory;
import com.daroch.payment.domain.enums.PaymentMethod;
import com.daroch.payment.domain.enums.PaymentStatus;
import com.daroch.payment.dto.bookingService.GetBookingResponse;
import com.daroch.payment.dto.response.PaymentResponse;
import com.daroch.payment.exceptions.BadRequestException;
import com.daroch.payment.kafka.producer.PaymentEventProducer;
import com.daroch.payment.mappers.PaymentMapper;
import com.daroch.payment.repositories.PaymentHistoryRepository;
import com.daroch.payment.repositories.PaymentRepository;
import com.daroch.payment.services.PaymentService;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentHistoryRepository paymentHistoryRepository;
  private final BookingServiceClient bookingServiceClient;
  private final PaymentEventProducer paymentEventProducer;
  private final PaymentMapper paymentMapper;

  @Override
  @Transactional
  public PaymentResponse randomPaymentStatus(Jwt jwt, UUID bookingId) {

    if (bookingId == null) {
      throw new BadRequestException("Booking id is required.");
    }

    GetBookingResponse bookingResponse = bookingServiceClient.getTicketTypes(jwt, bookingId);

    Optional<Payment> optionalPayment = paymentRepository.findByBookingId(bookingId);

    UUID userId = UUID.fromString(jwt.getSubject());

    // ---------------------------------------------------------
    // random Selection of paymentMethod & PaymentStatus
    int statusChance = ThreadLocalRandom.current().nextInt(100);

    PaymentStatus randomStatus;

    if (statusChance < 65) randomStatus = PaymentStatus.SUCCESS;
    else if (statusChance < 90) randomStatus = PaymentStatus.FAILED;
    else randomStatus = PaymentStatus.PENDING;

    int methodChance = ThreadLocalRandom.current().nextInt(100);

    PaymentMethod randomMethod;

    if (methodChance < 50) randomMethod = PaymentMethod.CARD;
    else if (methodChance < 60) randomMethod = PaymentMethod.UPI;
    else if (methodChance < 70) randomMethod = PaymentMethod.NET_BANKING;
    else randomMethod = PaymentMethod.WALLET;
    // ---------------------------------------------------------

    if (optionalPayment.isPresent()) {
      // Update existing payment
      Payment oldPayment = optionalPayment.get();

      Payment updatedPayment = paymentRepository.save(oldPayment);

      updatedPayment.setStatus(randomStatus);
      updatedPayment.setPaymentMethod(randomMethod);
      updatedPayment.setGatewayPaymentId(UUID.randomUUID().toString());

      PaymentHistory newHistory =
          PaymentHistory.builder()
              .paymentId(oldPayment.getPaymentId())
              .oldStatus(oldPayment.getStatus())
              .newStatus(updatedPayment.getStatus())
              .build();

      paymentHistoryRepository.save(newHistory);

      paymentEventProducer.publishPaymentUpdate(updatedPayment);

      return paymentMapper.toPaymentResponse(updatedPayment);

    } else {
      // Create new payment
      Payment newPayment =
          Payment.builder()
              .bookingId(bookingId)
              .userId(userId)
              .amount(bookingResponse.getTotalPrice())
              .status(randomStatus)
              .paymentMethod(randomMethod)
              .gatewayPaymentId(UUID.randomUUID().toString())
              .build();

      Payment savedPayment = paymentRepository.save(newPayment);

      paymentEventProducer.publishPaymentUpdate(savedPayment);

      return paymentMapper.toPaymentResponse(savedPayment);
    }
  }
}
