package com.daroch.payment.controllers;

import com.daroch.payment.dto.ErrorResponse;
import com.daroch.payment.dto.response.PaymentResponse;
import com.daroch.payment.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService paymentService;

  @Operation(
      summary = "Simulate Payment",
      description =
          "Simulates a payment for the specified booking, stores the payment record, "
              + "and publishes a payment status update event to Kafka.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Payment processed successfully",
        content = @Content(schema = @Schema(implementation = PaymentResponse.class))),
    @ApiResponse(
        responseCode = "400",
        description = "Invalid booking ID or bad request",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(
        responseCode = "404",
        description = "Booking not found",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    @ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PostMapping("/{bookingId}")
  public ResponseEntity<PaymentResponse> randomPaymentStatus(
      @AuthenticationPrincipal Jwt jwt, @PathVariable UUID bookingId) {

    return ResponseEntity.ok(paymentService.randomPaymentStatus(jwt, bookingId));
  }
}
