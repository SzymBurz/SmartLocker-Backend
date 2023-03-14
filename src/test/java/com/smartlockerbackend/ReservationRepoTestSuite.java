package com.smartlockerbackend;

import com.smartlockerbackend.domain.Locker;
import com.smartlockerbackend.domain.Reservation;
import com.smartlockerbackend.domain.Size;
import com.smartlockerbackend.domain.User;
import com.smartlockerbackend.generator.LockerGenerator;
import com.smartlockerbackend.generator.ReservationGenerator;
import com.smartlockerbackend.repository.LockerRepo;
import com.smartlockerbackend.repository.ReservationRepo;
import com.smartlockerbackend.repository.ReservationIdSerializer;
import com.smartlockerbackend.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReservationRepoTestSuite {

    @Autowired
    LockerRepo lockerRepo;

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ReservationIdSerializer reservationIdSerializer;

    @Autowired
    LockerGenerator lockerGenerator;

    @Autowired
    ReservationGenerator reservationGenerator;


    @Test
    void lockerRepoTest() {

        Locker testLocker = new Locker(1, Size.S);
        lockerRepo.save(testLocker);
        Optional<Locker> lockerById = lockerRepo.findById(1);
        Assertions.assertTrue(lockerById.isPresent());
        lockerRepo.deleteAll();
    }

    @Test
    void saveReservationTest() {
        Locker testLocker = new Locker(131, Size.S);
        lockerRepo.save(testLocker);
        User testUser = new User(1, "Kiemoon");
        userRepo.save(testUser);
        Reservation testReservation = new Reservation(1, LocalDateTime.now(), LocalDateTime.now().plusHours(2), testUser, testLocker);
        reservationRepo.save(testReservation);

        List<Reservation> byLockerId = reservationRepo.findByLockerId(131);
        if(!byLockerId.isEmpty()){
            System.out.println(" " + byLockerId.get(0).getLocker().getId());
        }else{
            System.out.println("didn't work");
        }

        lockerRepo.deleteAll();
        reservationRepo.deleteAll();
        userRepo.deleteAll();

    }

    @Test
    void getByLockerIdSortById() {
        lockerRepo.deleteAll();
        reservationRepo.deleteAll();
        userRepo.deleteAll();
        Locker testLocker = new Locker(131, Size.S);
        lockerRepo.save(testLocker);
        User testUser = new User(1, "Kiemoon");
        userRepo.save(testUser);
        Reservation testReservation1 = new Reservation(1, LocalDateTime.now(), LocalDateTime.now().plusHours(2), testUser, testLocker);
        reservationRepo.save(testReservation1);
        Reservation testReservation2 = new Reservation(2, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), testUser, testLocker);
        reservationRepo.save(testReservation2);
        Reservation testReservation3 = new Reservation(3, LocalDateTime.now(), LocalDateTime.now().plusHours(8), testUser, testLocker);
        reservationRepo.save(testReservation3);

        Optional<Reservation> byLockerId = reservationRepo.findTopByLockerIdOrderByIdDesc(131);
        if(!byLockerId.isEmpty()){
            System.out.println(" " + byLockerId.get());
        }else{
            System.out.println("didn't work");
        }

        List<Reservation> top2ByUserIdOrderByEnd = reservationRepo.findTop2ByUserIdOrderByEndDesc(1);

        for(Reservation r: top2ByUserIdOrderByEnd) {
            System.out.println(r);
        }

    }

    @Test
    void getLockerThatDoesNotExist() {
        lockerRepo.deleteAll();
        reservationRepo.deleteAll();
        userRepo.deleteAll();
        System.out.println("getLockerThatDoesNotExist: ");
        Optional<Reservation> byLockerId = reservationRepo.findTopByLockerIdOrderByIdDesc(131);
        if(!byLockerId.isEmpty()){
            System.out.println(" " + byLockerId.get());
        }else{
            System.out.println("empty!");
        }
    }

    @Test
    void findTop() {
        lockerRepo.deleteAll();
        reservationRepo.deleteAll();
        userRepo.deleteAll();
        Locker testLocker = new Locker(131, Size.S);
        lockerRepo.save(testLocker);
        User testUser = new User(1, "Kiemoon");
        userRepo.save(testUser);
        Reservation testReservation1 = new Reservation(1, LocalDateTime.now(), LocalDateTime.now().plusHours(2), testUser, testLocker);
        reservationRepo.save(testReservation1);
        Reservation testReservation2 = new Reservation(2, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), testUser, testLocker);
        reservationRepo.save(testReservation2);
        Reservation testReservation3 = new Reservation(3, LocalDateTime.now(), LocalDateTime.now().plusHours(8), testUser, testLocker);
        reservationRepo.save(testReservation3);

        Optional<Reservation> byLockerId = reservationRepo.findFirstByOrderByIdDesc();
        if(!byLockerId.isEmpty()){
            System.out.println(" " + byLockerId.get());
        }else{
            System.out.println("didn't work");
        }
    }

    @Test
    void serializerTest() {
        lockerRepo.deleteAll();
        reservationRepo.deleteAll();
        userRepo.deleteAll();
        Locker testLocker = new Locker(131, Size.S);
        lockerRepo.save(testLocker);
        User testUser = new User(1, "Kiemoon");
        userRepo.save(testUser);
        Reservation testReservation1 = new Reservation(reservationIdSerializer.genSerialForRes(), LocalDateTime.now(), LocalDateTime.now().plusHours(2), testUser, testLocker);
        reservationRepo.save(testReservation1);
        Reservation testReservation2 = new Reservation(reservationIdSerializer.genSerialForRes(), LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), testUser, testLocker);
        reservationRepo.save(testReservation2);
        Reservation testReservation3 = new Reservation(reservationIdSerializer.genSerialForRes(), LocalDateTime.now(), LocalDateTime.now().plusHours(8), testUser, testLocker);
        reservationRepo.save(testReservation3);

        reservationRepo.findAll().forEach(System.out::println);

    }

    @Test
    void lockerGeneratorTest() {
        lockerRepo.deleteAll();
        reservationRepo.deleteAll();
        userRepo.deleteAll();
        lockerGenerator.generateExampleData();
        lockerRepo.findAll().forEach(System.out::println);
    }

    @Test
    void reservationGeneratorTest() {
        lockerRepo.deleteAll();
        reservationRepo.deleteAll();
        userRepo.deleteAll();
        lockerGenerator.generateExampleData();

        reservationGenerator.generateExampleReservationData();
        reservationRepo.findAll().forEach(System.out::println);
    }

}
