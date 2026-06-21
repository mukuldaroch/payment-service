package com.daroch.payment.domain.entities;

import com.daroch.payment.domain.enums.PaymentMethod;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID paymentId;

  @Column(nullable = false)
  private UUID bookingId;

  @Column(nullable = false)
  private UUID userId;

  @Column(nullable = false)
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentStatus status;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentMethod paymentMethod;

  private String gatewayPaymentId;

  private String failureReason;

  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  // -------------------------------------------------equals & hash
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Payment)) return false;
    return paymentId != null && paymentId.equals(((Payment) o).paymentId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
