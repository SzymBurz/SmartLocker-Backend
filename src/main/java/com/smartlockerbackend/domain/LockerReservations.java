package com.smartlockerbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class LockerReservations {

    //this class is Basically Wrapper for List<Reservation> but with methods that makes it easier to display and handle in Vaadin
    Locker locker;
    List<Reservation> reservations;

    public LockerReservations(List<Reservation> reservations) {
        this.reservations = reservations;

        if(!reservations.isEmpty()){
            this.locker = reservations.get(0).getLocker();

        }
    }

    public LockerReservations(Locker locker) {

        this.locker = locker;
        this.reservations = new ArrayList<>();
    }

    public String reservationsToString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm");

        if (!reservations.isEmpty()) {

            StringBuilder stringBuilder = new StringBuilder();

            for (Reservation r : reservations) {
                stringBuilder.append("(" + r.getStart().format(formatter) + " - " + r.getEnd().format(formatter) + ") ");
            }

            return stringBuilder.toString();
        } else {
            return "no reservations";
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append( "locker Id: " + locker.getId() + ": ");
        stringBuilder.append(reservationsToString());
        return  stringBuilder.toString();
    }
}
