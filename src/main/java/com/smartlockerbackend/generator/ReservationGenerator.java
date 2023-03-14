package com.smartlockerbackend.generator;

import com.smartlockerbackend.domain.Reservation;
import com.smartlockerbackend.domain.User;
import com.smartlockerbackend.repository.LockerRepo;
import com.smartlockerbackend.repository.ReservationRepo;
import com.smartlockerbackend.repository.ReservationIdSerializer;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@NoArgsConstructor
@Service
public class ReservationGenerator {

    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    LockerRepo lockerRepo;
    @Autowired
    ReservationIdSerializer reservationIdSerializer;
    @Autowired
    UserGenerator userGenerator;


    public void generateExampleReservationData(){

        System.out.println("Generating random reservations: ");
        Random random = new Random();
        int dataSize = 100;
        LocalDateTime now = LocalDateTime.now();
        User user = userGenerator.generateUserForDemo();

        for (int i = 1; i < dataSize; i ++) {

            int lockerNo = random.nextInt(99);

            reservationRepo.findTopByLockerIdOrderByIdDesc(lockerNo).ifPresentOrElse(r -> {
                LocalDateTime end = r.getEnd();
                LocalDateTime startOfNew = end.plusHours((long) random.nextInt(2)+1);
                reservationRepo.save(new Reservation(reservationIdSerializer.genSerialForRes(), startOfNew, generateEndTime(startOfNew), user, lockerRepo.findById(lockerNo).get()));
            }, () -> {
                reservationRepo.save(new Reservation(reservationIdSerializer.genSerialForRes(), generateStartTime(now), generateEndTime(now), user, lockerRepo.findById(lockerNo).get()));
            });
        }
    }

    private LocalDateTime generateStartTime(LocalDateTime now) {
        Random random = new Random();
        LocalDateTime output = now;
        output = output.minusHours(3L);
        output = output.plusHours((long) random.nextInt(3));
        return output;
    }

    private LocalDateTime generateEndTime(LocalDateTime now) {
        Random random = new Random();
        LocalDateTime output = now;
        output = output.plusHours(1L);
        output = output.plusHours((long) random.nextInt(3));
        return output;
    }
}
