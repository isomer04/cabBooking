package com.example.cabBooking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.time.LocalDate;

public class BookingMainScreenController {

    @FXML
    private Button addTaskBtn;

    @FXML
    private Button clearAllTaskBtn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField fromLocationTextField;

    @FXML
    private TextField passengerNumber;

    @FXML
    private Button removeTaskBtn;

    @FXML
    private TextField timeTextField;

    @FXML
    private ListView<Booking> toDoListView;

    @FXML
    private TextField toLocationTextField;

    @FXML
    private Text cabRemaining;

    @FXML
    void addTaskHandler(ActionEvent event) {
        String fromLocationAddr = fromLocationTextField.getText();
        String toLocationAddr = toLocationTextField.getText();
        LocalDate selectedDate = datePicker.getValue();
        String selectedTime = timeTextField.getText();
        String passengers = passengerNumber.getText();

        Booking booking = new Booking(fromLocationAddr, toLocationAddr, selectedDate, selectedTime, passengers);

//        ToDoDB.tasklist.addTask(task);

        ToDoDB.bookingList.addTask(booking);
        System.out.println(booking);

        refreshTaskListView();

        fromLocationTextField.clear();
        toLocationTextField.clear();
        timeTextField.clear();
        passengerNumber.clear();

    }

    private void refreshTaskListView() {
        // take everything in my ArrayTaskQueue object
        // and put it into the ListView object

        toDoListView.getItems().clear();

//        Booking[] allTaskArray = ToDoDB.bookingList.getTasklist();
        Booking[] allBookingTaskArray = ToDoDB.bookingList.getTasklist();

        for (int i = 0; i < ToDoDB.bookingList.getCount(); i++) {
            toDoListView.getItems().add(allBookingTaskArray[i]);
        }

//        for (int i = 0; i < tasklist.getCount(); i++) {
//            toDoListView.getItems().add(allTaskArray[i]);
//        }
//        cabRemaining.setText("Cab Remaining " +ToDoDB.bookingList.getTasklist());
    }

    @FXML
    void removeTaskHandler(ActionEvent event) {
        ToDoDB.completedTasks.addTask(ToDoDB.bookingList.removeOldestTask());
        refreshTaskListView();
    }

    @FXML
    void removeSelectedTaskHandler(ActionEvent event) {
        Booking selectedTask = toDoListView.getSelectionModel().getSelectedItem();
        System.out.println(selectedTask);

        try {
            ToDoDB.completedTasks.addTask(ToDoDB.bookingList.removeTask(selectedTask));
        } catch (NullPointerException n) {
            System.out.println("Please select a task");
        }
        refreshTaskListView();
    }

    @FXML
    void clearAllTaskHandler(ActionEvent event) {
        ToDoDB.bookingList.clearAllTasks();
        System.out.println(ToDoDB.bookingList + "from clearAllTaskHandler");
        refreshTaskListView();
    }

    @FXML
    void viewCompletedTasksBtn(ActionEvent event) {
        Stage newStage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("completedtasks-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            newStage.setTitle("Cab Booking App");
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            System.out.println("Trouble with opening FXML.");
            System.out.println(e);
        }
    }
}
