package com.yusuf.spring.bookconcert.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TicketResponseDTO {

    private Long id;
    private String concertName;
    private String customerName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    private Date purchaseDate;
    private String errorMessage;

    public TicketResponseDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
