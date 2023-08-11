package com.yusuf.spring.bookconcert.service;

import com.yusuf.spring.bookconcert.dto.TicketRequestDTO;
import com.yusuf.spring.bookconcert.dto.TicketResponseDTO;
import com.yusuf.spring.bookconcert.entity.Concert;
import com.yusuf.spring.bookconcert.entity.Ticket;
import com.yusuf.spring.bookconcert.exception.TicketBookingException;
import com.yusuf.spring.bookconcert.repository.ConcertRepository;
import com.yusuf.spring.bookconcert.repository.TicketRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ConcertRepository concertRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = TicketBookingException.class)
    public TicketResponseDTO bookTicket(TicketRequestDTO ticketRequestDTO) {
        Concert concert = concertRepository.findById(ticketRequestDTO.getConcertId()).orElse(null);
        if(concert != null && concert.getStock() > 0) {
            try {
                concert.setStock(concert.getStock() - 1);
                concertRepository.save(concert);
                Ticket ticket = Ticket.builder().
                        concert(concert).
                        customerName(ticketRequestDTO.getCustomerName()).
                        purchaseDate(new Date()).build();
                Ticket saveTicket = ticketRepository.save(ticket);
                return modelMapper.map(saveTicket, TicketResponseDTO.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw new TicketBookingException("Ticket booking failed due error Exception.");
            }
        } else {
            return new TicketResponseDTO("Concert Ticket Not Found or Concert out of Stock.");
        }

    }


}
