package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ConnectionUtil;
import sample.Models.ModelsKey;

import java.io.IOException;
import java.sql.*;

public class ControllerRegistration {

    Connection conn;

    public ControllerRegistration() throws ClassNotFoundException {conn = ConnectionUtil.conDB();
    }
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelEmail;
    @FXML
    private Button buttonToSignIn;
    @FXML
    private Label status;
    @FXML
    private Label labelPassword;

    ModelsKey modelsKey = new ModelsKey();

    @FXML
    private void pressReg(ActionEvent event) throws SQLException {
        modelsKey.pressReg(labelPassword,labelEmail,labelLogin,status,txtPassword,txtLogin,txtEmail,txtFirstName,txtLastName);
    }
    @FXML
    private void pressToSignIn(ActionEvent event){
        buttonToSignIn.getScene().getWindow().hide();
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/signIn.fxml"));
            primaryStage.setTitle("Вход");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handle(KeyEvent event) throws SQLException {
        KeyCode key = event.getCode();
        if (key == KeyCode.ENTER){
            modelsKey.pressReg(labelPassword,labelEmail,labelLogin,status,txtPassword,txtLogin,txtEmail,txtFirstName,txtLastName);
        }
    }
    @FXML
    private void initialize(){
//        buttonToSignIn.setOnAction(event -> {
//            buttonToSignIn.getScene().getWindow().hide();
//            try {
//                Stage primaryStage = new Stage();
//                Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/signIn.fxml"));
//                primaryStage.setTitle("Вход");
//                primaryStage.setScene(new Scene(root));
//                primaryStage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }
}