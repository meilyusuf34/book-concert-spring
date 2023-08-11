package com.yusuf.spring.bookconcert.repository;

import com.yusuf.spring.bookconcert.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {


}
