package com.smartlockerbackend.repository;

import com.smartlockerbackend.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationIdSerializer {

    @Autowired
    ReservationRepo reservationRepo;

    public int genSerialForRes(){
        Optional<Reservation> reservation = reservationRepo.findFirstByOrderByIdDesc();
        return reservation.map(r -> r.getId() + 1).orElse(0);
    }
}
