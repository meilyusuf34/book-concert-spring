package com.yusuf.spring.bookconcert.controller;

import com.yusuf.spring.bookconcert.dto.TicketRequestDTO;
import com.yusuf.spring.bookconcert.dto.TicketResponseDTO;
import com.yusuf.spring.bookconcert.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<TicketResponseDTO> bookTicket(@RequestBody TicketRequestDTO ticketRequestDTO) {
        TicketResponseDTO ticketResponseDTO = ticketService.bookTicket(ticketRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponseDTO);
    }
}
