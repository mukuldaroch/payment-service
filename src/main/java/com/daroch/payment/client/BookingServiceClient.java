package com.daroch.payment.client;

import com.daroch.payment.dto.ErrorResponse;
import com.daroch.payment.dto.bookingService.GetBookingResponse;
import com.daroch.payment.exceptions.BusinessException;
import com.daroch.payment.exceptions.ServiceFailedException;
import com.daroch.payment.exceptions.TicketServiceUnavailableException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class BookingServiceClient {

  private final WebClient webClient;

  public GetBookingResponse getTicketTypes(Jwt jwt, UUID bookingId) {

    try {
      return webClient
          .get()
          .uri("http://booking-service:8080/booking/{bookingId}", bookingId)
          .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getTokenValue())
          .retrieve()

          // 4xx — client & business errors
          .onStatus(
              HttpStatusCode::is4xxClientError,
              response ->
                  response
                      .bodyToMono(ErrorResponse.class)
                      .flatMap(
                          error ->
                              Mono.error(
                                  new BusinessException(
                                      error.getErrorCode(),
                                      error.getMessage(),
                                      HttpStatus.valueOf(error.getStatus())))))
          // 5xx — Event Service failed
          .onStatus(
              HttpStatusCode::is5xxServerError,
              response ->
                  response
                      .bodyToMono(ErrorResponse.class)
                      .flatMap(
                          error ->
                              Mono.error(
                                  new ServiceFailedException(
                                      error.getErrorCode(), error.getMessage()))))
          .bodyToMono(GetBookingResponse.class)
          .block();

    } catch (WebClientRequestException ex) {

      throw new TicketServiceUnavailableException();
    }
  }
}
