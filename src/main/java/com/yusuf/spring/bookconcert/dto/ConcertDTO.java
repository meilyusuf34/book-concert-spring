package com.yusuf.spring.bookconcert.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertDTO {

    private Long id;
    private String concertName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    private Date concertDate;
    private ConcertCategory category;
    private int stock;
    private BigDecimal price;
    private String artist;
    private String location;

}
