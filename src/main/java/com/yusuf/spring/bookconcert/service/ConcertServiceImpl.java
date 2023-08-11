package com.yusuf.spring.bookconcert.service;

import com.yusuf.spring.bookconcert.dto.ConcertDTO;
import com.yusuf.spring.bookconcert.entity.Concert;
import com.yusuf.spring.bookconcert.repository.ConcertRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertServiceImpl implements ConcertService {

    @Autowired
    private  ConcertRepository concertRepository;
    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public List<ConcertDTO> getAllConcert() {
        List<Concert> concertList = concertRepository.findAll();
        return concertList.stream().map(concert -> modelMapper.map(concert, ConcertDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ConcertDTO createConcert(ConcertDTO concertDTO) {
        Concert concert = convertToEntity(concertDTO);
        Concert savedConcert = concertRepository.save(concert);
        return convertToDTO(savedConcert);
    }

    private Concert convertToEntity(ConcertDTO concertDTO) {
        return modelMapper.map(concertDTO, Concert.class);
    }

    private ConcertDTO convertToDTO(Concert concert) {
        return modelMapper.map(concert, ConcertDTO.class);
    }


}
