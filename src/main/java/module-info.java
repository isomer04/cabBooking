module com.example.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cabBooking to javafx.fxml;
    exports com.example.cabBooking;
}