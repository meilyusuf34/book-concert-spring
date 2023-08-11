package com.yusuf.spring.bookconcert.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "concert_id")
    private Concert concert;
    
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    public Ticket() {
    }
}
