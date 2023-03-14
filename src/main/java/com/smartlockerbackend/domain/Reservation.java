package com.smartlockerbackend.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Document(collection = "reservations")
public class Reservation {

    @Id
    int id;
    LocalDateTime start;
    LocalDateTime end;
    User user;
    Locker locker;

    public Reservation(int id, LocalDateTime start, LocalDateTime end, User user, Locker locker) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.user = user;
        this.locker = locker;
    }

    public Reservation(LocalDateTime start, LocalDateTime end, User user, Locker locker) {
        this.start = start;
        this.end = end;
        this.user = user;
        this.locker = locker;
    }
}
