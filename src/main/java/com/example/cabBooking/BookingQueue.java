package com.example.cabBooking;

public interface BookingQueue {

        public void addTask(Booking booking);
        public Booking removeTask(Booking booking);
        public Booking removeOldestTask();
        public void clearAllTasks();


}
