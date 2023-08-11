package com.yusuf.spring.bookconcert.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequestDTO {

    private Long concertId;
    private String customerName;

}
