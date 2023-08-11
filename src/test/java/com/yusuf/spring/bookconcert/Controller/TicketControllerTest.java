package com.yusuf.spring.bookconcert.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yusuf.spring.bookconcert.controller.TicketController;
import com.yusuf.spring.bookconcert.dto.TicketRequestDTO;
import com.yusuf.spring.bookconcert.dto.TicketResponseDTO;
import com.yusuf.spring.bookconcert.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@AutoConfigureMockMvc
@WebMvcTest(controllers = TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TicketService ticketService;

    @Test
    void testBookTicket_Success() throws Exception {
        Date purchaseDate = new Date();
        TicketRequestDTO requestDTO = new TicketRequestDTO(1L, "Test Name 1");
        TicketResponseDTO responseDTO = TicketResponseDTO.builder().id(1L).
                concertName("Test Concert 1").
                customerName("Test Name 1").
                purchaseDate(purchaseDate).
                errorMessage(null).build();

        Mockito.when(ticketService.bookTicket(Mockito.any(TicketRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/tickets/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("Test Name 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.concertName").value("Test Concert 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").isEmpty());
    }

    @Test
    void testBookTicket_ConcertNotFound() throws Exception {
        TicketRequestDTO requestDTO = new TicketRequestDTO(1L, "Test Name 1");

        Mockito.when(ticketService.bookTicket(Mockito.any(TicketRequestDTO.class)))
                .thenReturn(new TicketResponseDTO("Concert not found."));

        mockMvc.perform(MockMvcRequestBuilders.post("/tickets/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("Concert not found."));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
