package com.example.cabBooking;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

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
    private void Login (ActionEvent event) throws Exception{
            BufferedReader reader = null;
            try {
                List<User> users = new ArrayList<User>();
                String line = "";
                reader = new BufferedReader(new FileReader("user.csv"));
                reader.readLine();

                while((line = reader.readLine()) != null) { // Big O(n)
                    String[] fields = line.split(",");

                    if(fields.length > 0) {
                        if(fields[0].equals(usernameLogin.getText()) && fields[1].equals(passwordLogin.getText())) {

                            System.out.println("field[0]=" + fields[0] + "/field[1]=" + fields[1]);
                            System.out.println("successfully logged in");
                            btnLogin.getScene().getWindow().hide();
                            Parent root = FXMLLoader.load(getClass().getResource("todo-mainScreen.fxml"));
                            Stage mainStage = new Stage();
                            Scene scene = new Scene(root);
                            mainStage.setScene(scene);
                            mainStage.show();
                        } else {
                            invalidLogin.setText("Incorrect Username Or Password");
                            System.out.println("====================");
                            System.out.println(usernameLogin.getText());
                            System.out.println(passwordLogin.getText());
                            System.out.println("====================");
                            System.out.println("field[0]=" + fields[0] + ".field[1]=" + fields[1]);
                            System.out.println("incorrect login");
                        }
                    }
                }

                for(User u: users) {
                    System.out.printf("[userName=%s, Password=%s, email=%s]\n", u.getName(), u.getPassword(), u.getEmail());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    boolean count = true;

    public void addUser(ActionEvent event) throws IOException {
        List<User> users = new ArrayList<User>();

            //create demo Users
            User user = new User();

            user.setName(usernameSignup.getText());
            user.setEmail(emailSignup.getText());
            user.setPassword(passwordSignup.getText());
            users.add(user);

            FileWriter fileWriter = null;
            File file = new File("user.csv");

            //2nd challenge
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("user.csv", true));

        while (count == true){
            bufferedWriter.append("Name, Password,  Email\n");
            count = false;
        }

        try {
                for(User u: users) { //Big O(n)
//                    fileWriter.
                    bufferedWriter.append(u.getName());
                    bufferedWriter.append(",");
                    bufferedWriter.append(u.getPassword());
                    bufferedWriter.append(",");
                    bufferedWriter.append(u.getEmail());
                    bufferedWriter.append("\n");
                }
//            alert.setTitle("Alert");
//            alert.setContentText("You can log in now");

            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
