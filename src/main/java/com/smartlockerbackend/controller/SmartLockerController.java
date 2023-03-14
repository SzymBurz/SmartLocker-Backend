package com.smartlockerbackend.controller;

import com.smartlockerbackend.generator.UserGenerator;
import com.smartlockerbackend.repository.ReservationRepo;
import com.smartlockerbackend.service.BookingService;
import com.smartlockerbackend.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/v1/api/")
@RestController
public class SmartLockerController {

    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    BookingService bookingService;
    @Autowired
    UserGenerator userGenerator;

    @PostMapping(value = "/reservationsCheck")
    public List<LockerReservations> getReservationsCheck(@RequestBody Map<String, Object> requestMap) {
        return bookingService.availabilityCheckSizeTime(
                Size.valueOf((String) requestMap.get("size")),
                LocalDateTime.parse((CharSequence) requestMap.get("start")),
                LocalDateTime.parse((CharSequence) requestMap.get("end")));
    }

    @GetMapping("/demoUser")
    public User getDemoUser() {
        return userGenerator.generateUserForDemo();
    }

    @PostMapping("/newReservation")
    public boolean bookNew(@RequestBody Reservation reservation){
        return bookingService.bookLocker(reservation.getLocker(), reservation.getUser(), reservation.getStart(), reservation.getEnd());
    }

    @GetMapping("/defaultReservation")
    public Reservation getDefaultReservationTemplate() {
        Locker testLocker = new Locker(131, Size.S);
        User testUser = new User(1, "Kiemoon");
        Reservation testReservation = new Reservation(1, LocalDateTime.now(), LocalDateTime.now().plusHours(2), testUser, testLocker);
        return testReservation;
    }

    @GetMapping("/defaultSizeTimeHttpEntity")
    public HttpEntity<Map> getDefaultSizeTimeHttpEntity() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        Map<String, Object> sizeTimeMap = new HashMap<>();
        sizeTimeMap.put("size", Size.S);
        sizeTimeMap.put("start", LocalDateTime.now());
        sizeTimeMap.put("end", LocalDateTime.now().plusHours(2));
        return new HttpEntity<>(sizeTimeMap, httpHeaders);
    }

    @PostMapping("/reservationsForUser")
    public List<Reservation> getReservationsForUser(@RequestBody User user){
        return reservationRepo.findByUserId(user.getId());
    }
}
