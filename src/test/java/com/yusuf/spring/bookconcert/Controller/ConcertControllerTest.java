package com.yusuf.spring.bookconcert.Controller;

import com.yusuf.spring.bookconcert.controller.ConcertController;
import com.yusuf.spring.bookconcert.dto.ConcertCategory;
import com.yusuf.spring.bookconcert.dto.ConcertDTO;
import com.yusuf.spring.bookconcert.service.ConcertService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AutoConfigureMockMvc
@WebMvcTest(controllers = ConcertController.class)
class ConcertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConcertService concertService;

    @Test
    void testGetAllConcerts() throws Exception {
        List<ConcertDTO> concerts = new ArrayList<>();
        concerts.add(ConcertDTO.builder().
                id(1L).
                concertName("Test Concert Name 1").
                stock(200).
                artist("Artist Test 1").
                concertDate(new Date()).
                price(BigDecimal.valueOf(10000.00)).
                location("location test 1").
                category(ConcertCategory.CLASSICAL).build());
        concerts.add(ConcertDTO.builder().
                id(2L).concertName("Test Concert Name 2").
                stock(200).
                artist("Artist Test 2").
                concertDate(new Date()).
                price(BigDecimal.valueOf(20000.00)).
                location("location test 2").
                category(ConcertCategory.POP).build());

        Mockito.when(concertService.getAllConcert()).thenReturn(concerts);

        mockMvc.perform(MockMvcRequestBuilders.get("/concerts"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].concertName", is("Test Concert Name 1")));;
    }


}
