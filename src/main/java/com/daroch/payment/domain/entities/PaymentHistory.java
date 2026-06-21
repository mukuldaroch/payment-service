package com.daroch.payment.domain.entities;

import com.daroch.payment.domain.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment_status_history")
public class PaymentHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "history_id", nullable = false, updatable = false)
  private UUID historyId;

  @Column(name = "payment_id", nullable = false, updatable = false)
  private UUID paymentId;

  @Column(name = "old_status", nullable = false, updatable = false)
  @Enumerated(EnumType.STRING)
  private PaymentStatus oldStatus;

  @Column(name = "new_status", nullable = false, updatable = false)
  @Enumerated(EnumType.STRING)
  private PaymentStatus newStatus;

  @CreatedDate
  @Column(name = "changed_at", nullable = false, updatable = false)
  private LocalDateTime changedAt;

  // -------------------------------------------------equals & hash
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PaymentHistory)) return false;
    return historyId != null && historyId.equals(((PaymentHistory) o).historyId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
