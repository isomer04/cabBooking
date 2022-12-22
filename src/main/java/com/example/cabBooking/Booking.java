package com.example.cabBooking;

import java.time.LocalDate;

public class Booking {
    private String from;
    private String to;
    private LocalDate date;
    private String time;
    private String passengers;
//    private int ;

    public Booking(String from, String to, LocalDate date, String time, String passengers) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", passengers='" + passengers + '\'' +
                '}';
    }
}
