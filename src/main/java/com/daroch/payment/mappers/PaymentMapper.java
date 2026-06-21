package com.daroch.payment.mappers;

import com.daroch.payment.domain.entities.Payment;
import com.daroch.payment.dto.response.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

  PaymentResponse toPaymentResponse(Payment payment);
}
