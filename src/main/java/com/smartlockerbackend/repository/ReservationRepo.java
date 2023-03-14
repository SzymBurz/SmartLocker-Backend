package com.smartlockerbackend.repository;

import com.smartlockerbackend.domain.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepo extends MongoRepository<Reservation, Integer> {

    List<Reservation> findByLockerId(int lockerId);

    Optional<Reservation> findTopByLockerIdOrderByIdDesc(int id);

    List<Reservation> findTop2ByUserIdOrderByEndDesc(int id);

    Optional<Reservation> findFirstByOrderByIdDesc();

    List<Reservation> findByUserId(int id);
}
