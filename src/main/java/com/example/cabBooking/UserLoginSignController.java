package com.example.cabBooking;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserLoginSignController {
    
    @FXML
    private AnchorPane paneLogin;

    @FXML
    private TextField usernameLogin;

    @FXML
    private PasswordField passwordLogin;

    @FXML
    private ComboBox type;

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane paneSignup;

    @FXML
    private TextField usernameSignup;

    @FXML
    private TextField passwordSignup;

    @FXML
    private TextField emailSignup;

    @FXML
    private Text invalidLogin;

    @FXML
    private ComboBox  type_up;

    public static String[] loggedUser = null;

//    string mysqlUrl = jdbc:mysql://localhost:3306/mysql
//    String username = "root";
//    String password = "password";

//        this is postgres
//    String url = "jdbc:postgresql://localhost:5432/postgres";
//    String username = "postgres";
//    String password = "password";

//    try {
//        Connection conn =   DriverManager.getConnection(url, username, password);
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }

    // JDBC URL for MySQL
    String url = "jdbc:mysql://localhost:3306/cab_booking?useSSL=true";



    String username = "root";
    String password = "123456";

    public void LoginPaneShow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
    public void SignupPaneShow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }


    @FXML
    private void Login(ActionEvent event) throws Exception {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, usernameLogin.getText());
            statement.setString(2, passwordLogin.getText());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Successful login
                System.out.println("Successfully logged in");
                btnLogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("todo-mainScreen.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            } else {
                // Incorrect login
                invalidLogin.setText("Incorrect Username Or Password");
                System.out.println("====================");
                System.out.println(usernameLogin.getText());
                System.out.println(passwordLogin.getText());
                System.out.println("====================");
                System.out.println("incorrect login");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    boolean count = true;

    public void addUser(ActionEvent event) throws IOException {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, usernameSignup.getText());
            statement.setString(2, passwordSignup.getText());
            statement.setString(3, emailSignup.getText());
            statement.executeUpdate();

            // Rest of the code for successful signup
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean newLineExists(File file) throws IOException {
        RandomAccessFile fileHandler = new RandomAccessFile(String.valueOf(file), "r");
        long fileLength = fileHandler.length() - 1;
        if (fileLength < 0) {
            fileHandler.close();
            return true;
        }
        fileHandler.seek(fileLength);
        byte readByte = fileHandler.readByte();
        fileHandler.close();

        if (readByte == 0xA || readByte == 0xD) {
            return true;
        }
        return false;
    }
}
