package com.daroch.payment.kafka.producer;

import com.daroch.payment.domain.entities.Payment;
import com.daroch.payment.kafka.event.PaymentStatusUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public void publishPaymentUpdate(Payment payment) {

    kafkaTemplate.send("payment-status-updated", PaymentStatusUpdateEvent.from(payment));
  }
}
