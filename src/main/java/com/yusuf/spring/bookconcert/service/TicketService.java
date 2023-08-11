package com.yusuf.spring.bookconcert.service;

import com.yusuf.spring.bookconcert.dto.TicketRequestDTO;
import com.yusuf.spring.bookconcert.dto.TicketResponseDTO;
import org.springframework.transaction.annotation.Transactional;

public interface TicketService {

    TicketResponseDTO bookTicket(TicketRequestDTO ticketRequestDTO);
}
