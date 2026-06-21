package com.daroch.payment.repositories;

import com.daroch.payment.domain.entities.PaymentHistory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, UUID> {}
