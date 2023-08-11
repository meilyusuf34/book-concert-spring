package com.yusuf.spring.bookconcert.exception;

import com.yusuf.spring.bookconcert.dto.TicketResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(TicketBookingException.class)
    public ResponseEntity<TicketResponseDTO> handleTicketBookingException(TicketBookingException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TicketResponseDTO(ex.getMessage()));
    }

}
