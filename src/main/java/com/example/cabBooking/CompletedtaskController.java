package com.example.cabBooking;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CompletedtaskController {

    @FXML
    private ListView<Booking> completedTasksListView;

    public void initialize() {
        // take everything in my ArrayTaskQueue object
        // and put it into the ListView object

        completedTasksListView.getItems().clear();
        Booking[] allTaskArray = BookingDB.completedTasks.getTasklist();

        for (int i = 0; i < BookingDB.completedTasks.getCount(); i++) {
            completedTasksListView.getItems().add(allTaskArray[i]);
        }
    }

}
