package com.example.cabBooking;

public class BookingTaskQueue implements BookingQueue{
    private Booking[] bookingList;
    private final int DEFAULT_SIZE = 20;
    private int front=0, back=0, count;

    public BookingTaskQueue() {
        bookingList = new Booking[DEFAULT_SIZE];
    }
    public BookingTaskQueue(int size) {
        bookingList = new Booking[size];
    }

    @Override
    public void addTask(Booking booking) { // O(1)
        // enqueueing - add it to the "back" of the queue

        // check capacity
        if (count < bookingList.length) { //
            bookingList[back % bookingList.length] = booking;
            back++;
            count++;
        }
    }

    @Override
    public Booking removeTask(Booking booking) {
        boolean foundTask = false;

        // do a search //big O(n^2) / O(n) but we remove constants
        for (int i = 0; i < count; i++) {

            System.out.println(i + " this is i");
            if (booking.equals(bookingList[(i+front)%bookingList.length])) {
                // found!
                System.out.println("Found the task!");
                foundTask = true;

                for(int j = i; j < count; j++){
                    bookingList[j] = bookingList[j+1];
                }
                back--;
                count--;
            }
        }
        // not found
        if (!foundTask) {
            System.out.println("Did not find the task!");
        }
        return null;
    }

    @Override
    public Booking removeOldestTask() {
        // dequeing - remove what's in the "front" of the queue

        if (count == 0) {
            return null;
        } else {
            Booking booking = bookingList[front];
            bookingList[front] = null;
            front++;
            count--;

            return booking;
        }
    }

    @Override
    public void clearAllTasks() {
        front = 0;
        back = 0;
        count = 0;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < count; i++) {
            output.append(bookingList[(i+front)%bookingList.length] + "\n");
        }
        return output.toString();
    }

    public Booking[] getTasklist() {
        Booking[] actualTaskList = new Booking[count];

        for (int i = 0; i < count; i++) {
            actualTaskList[i] = bookingList[(i+front)%bookingList.length];
        }
        return actualTaskList;
    }
    public int getCount() {
        return count;
    }
}
