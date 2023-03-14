package com.smartlockerbackend.service;


import com.smartlockerbackend.domain.*;
import com.smartlockerbackend.repository.LockerRepo;
import com.smartlockerbackend.repository.ReservationRepo;
import com.smartlockerbackend.repository.ReservationIdSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private LockerRepo lockerRepo;
    @Autowired
    private ReservationIdSerializer reservationIdSerializer;

    public boolean bookLocker(Locker locker, User user, LocalDateTime start, LocalDateTime end) {
        if(availabilityCheckForLockerTime(locker.getId(), start, end).isEmpty()) {
            return false;
        } else {
            reservationRepo.save(new Reservation(reservationIdSerializer.genSerialForRes(), start, end, user, locker));
            return true;
        }
    }

    public Optional<LockerReservations> availabilityCheckForLockerTime(int lockerId, LocalDateTime start, LocalDateTime end) {

        List<Reservation> list = reservationRepo.findByLockerId(lockerId);

        List<Reservation> tempList = list.stream().filter(r ->
                !(r.getStart().isAfter(start) && r.getStart().isBefore(end))
                && !(r.getEnd().isAfter(start) && r.getEnd().isBefore(end))
                && !(r.getStart().equals(start))
                && !(r.getEnd().equals(end)))
                .collect(Collectors.toList());

        if(tempList.size() != list.size()) {
            return Optional.empty();
        } else if (tempList.isEmpty()){
            return Optional.of(new LockerReservations(lockerRepo.findById(lockerId).get(), list));
        } else {
            return Optional.of(new LockerReservations(list));
        }
    }

    public List<LockerReservations> availabilityCheckSizeTime(Size size, LocalDateTime start, LocalDateTime end) {

        return lockerRepo.findBySize(size).stream()
                .map(l -> availabilityCheckForLockerTime(l.getId(), start, end))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
