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
@RequestMapping(path = "/payment")
@RequiredArgsConstructor
public class PaymentCantroller {

  private final PaymentService paymentService;

  @Operation(summary = "Create Booking")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Booking Created"),
    @ApiResponse(
        responseCode = "400",
        description = "Invalid request",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PostMapping(path = "/{bookingId}")
  public ResponseEntity<PaymentResponse> randomPaymentStatus(
      @AuthenticationPrincipal Jwt jwt, @PathVariable UUID bookingId) {

    return ResponseEntity.ok(paymentService.randomPaymentStatus(jwt, bookingId));
  }
}
