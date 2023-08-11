package com.yusuf.spring.bookconcert.controller;

import com.yusuf.spring.bookconcert.dto.ConcertDTO;
import com.yusuf.spring.bookconcert.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concerts")
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @GetMapping
    public ResponseEntity<List<ConcertDTO>> getAllConcerts() {
        List<ConcertDTO> concertDTOList = concertService.getAllConcert();
        return ResponseEntity.ok(concertDTOList);
    }

    @PostMapping
    public ResponseEntity<ConcertDTO> createConcert(@RequestBody ConcertDTO concertDTO) {
        ConcertDTO createdConcertDto = concertService.createConcert(concertDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConcertDto);
    }
}
