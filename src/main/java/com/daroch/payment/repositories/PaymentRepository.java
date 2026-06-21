package com.daroch.payment.repositories;

import com.daroch.payment.domain.entities.Payment;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
  Optional<Payment> findByBookingId(UUID bookingId);
}
