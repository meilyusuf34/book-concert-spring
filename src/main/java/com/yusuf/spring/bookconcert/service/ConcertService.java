package com.yusuf.spring.bookconcert.service;

import com.yusuf.spring.bookconcert.dto.ConcertDTO;

import java.util.List;

public interface ConcertService {

    List<ConcertDTO> getAllConcert();

    ConcertDTO createConcert(ConcertDTO concertDTO);
}
