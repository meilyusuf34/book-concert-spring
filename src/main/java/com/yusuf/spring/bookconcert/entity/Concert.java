package com.yusuf.spring.bookconcert.entity;


import com.yusuf.spring.bookconcert.dto.ConcertCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "concert_name")
    private String concertName;

    @Column(name = "concert_date")
    private Date concertDate;

    @Enumerated(EnumType.STRING)
    private ConcertCategory category;

    private int stock;

    private BigDecimal price;

    private String artist;

    private String location;

    public Concert() {
    }
}
