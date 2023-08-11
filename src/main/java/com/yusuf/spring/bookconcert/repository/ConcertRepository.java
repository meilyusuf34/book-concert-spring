package com.yusuf.spring.bookconcert.repository;

import com.yusuf.spring.bookconcert.entity.Concert;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ConcertRepository extends JpaRepository<Concert, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Concert> findById(Long id);

}
