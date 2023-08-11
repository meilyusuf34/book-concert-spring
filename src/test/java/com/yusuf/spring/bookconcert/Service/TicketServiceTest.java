package com.yusuf.spring.bookconcert.Service;

import com.yusuf.spring.bookconcert.dto.ConcertCategory;
import com.yusuf.spring.bookconcert.dto.TicketRequestDTO;
import com.yusuf.spring.bookconcert.dto.TicketResponseDTO;
import com.yusuf.spring.bookconcert.entity.Concert;
import com.yusuf.spring.bookconcert.entity.Ticket;
import com.yusuf.spring.bookconcert.repository.ConcertRepository;
import com.yusuf.spring.bookconcert.repository.TicketRepository;
import com.yusuf.spring.bookconcert.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @MockBean
    private ConcertRepository concertRepository;

    @MockBean
    private TicketRepository ticketRepository;

    @Test
    void testBookTicket_Success() {

        // Mock data
        Concert concert = Concert.builder().
                id(1L).
                concertName("Test Concert Name 1").
                stock(200).
                artist("Artist Test 1").
                concertDate(new Date()).
                price(BigDecimal.valueOf(10000.00)).
                location("location test 1").
                category(ConcertCategory.CLASSICAL).build();
        TicketRequestDTO requestDTO = TicketRequestDTO.builder().
                concertId(1L).customerName("Test Name 1").build();
        requestDTO.setConcertId(1L);

        Mockito.when(concertRepository.findById(1L)).thenReturn(Optional.of(concert));
        Mockito.when(ticketRepository.save(Mockito.any(Ticket.class))).thenReturn(new Ticket());

        TicketResponseDTO responseDTO = ticketService.bookTicket(requestDTO);

        assertNotNull(responseDTO);
        assertNull(responseDTO.getErrorMessage());
    }

    @Test
    public void testBookTicket_ConcertNotFound() {
        TicketRequestDTO requestDTO = TicketRequestDTO.builder().
                concertId(1L).customerName("Test Name 1").build();
        requestDTO.setConcertId(1L);

        Mockito.when(concertRepository.findById(1L)).thenReturn(Optional.empty());

        TicketResponseDTO responseDTO = ticketService.bookTicket(requestDTO);

        assertNotNull(responseDTO);
        assertEquals("Concert Ticket Not Found or Concert out of Stock.", responseDTO.getErrorMessage());
    }

    @Test
    void testBookTicket_ConcertOutOfStock() {
        Concert concert = Concert.builder().
                id(1L).
                concertName("Test Concert Name 1").
                stock(0).
                artist("Artist Test 1").
                concertDate(new Date()).
                price(BigDecimal.valueOf(10000.00)).
                location("location test 1").
                category(ConcertCategory.CLASSICAL).build();

        TicketRequestDTO requestDTO = TicketRequestDTO.builder().
                concertId(1L).customerName("Test Name 1").build();
        requestDTO.setConcertId(1L);

        Mockito.when(concertRepository.findById(1L)).thenReturn(Optional.of(concert));

        TicketResponseDTO responseDTO = ticketService.bookTicket(requestDTO);

        assertNotNull(responseDTO);
        assertEquals("Concert Ticket Not Found or Concert out of Stock.", responseDTO.getErrorMessage());
    }
}
