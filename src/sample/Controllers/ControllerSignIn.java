package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ConnectionUtil;
import sample.Models.ModelsKey;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerSignIn {

    Connection conn;

    public ControllerSignIn() throws ClassNotFoundException {conn = ConnectionUtil.conDB();
    }

    @FXML
    private Label status;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button buttonToDataBase;
    @FXML
    private Button buttonToReg;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelPass;
    ModelsKey modelsKey = new ModelsKey();

    @FXML
    public void pressToReg(ActionEvent event){
        buttonToReg.getScene().getWindow().hide();
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/registration.fxml"));
            primaryStage.setTitle("Регистрация");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void pressSignIn(ActionEvent event) throws SQLException {
        modelsKey.pressSignInButton(status,labelLogin,labelPass,txtLogin,txtPassword,buttonToDataBase);
    }
    @FXML
    public void handle(KeyEvent event) throws SQLException {
        KeyCode key = event.getCode();
        if (key == KeyCode.ENTER){
            modelsKey.pressSignInHandle(status,labelLogin,labelPass,txtLogin,txtPassword,buttonToDataBase);
        }
    }
    @FXML
    public void initialize(){
    }
//    public void keyPressed (KeyEvent e){
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_UP -> System.out.println("Up key pressed");
//            case KeyEvent.VK_DOWN -> System.out.println("Down key pressed");
//            case KeyEvent.VK_LEFT -> System.out.println("Left key pressed");
//            case KeyEvent.VK_RIGHT -> System.out.println("Right key pressed");
//        }
//        if(e.getKeyCode() == KeyEvent.VK_A) {
//            if(e.isShiftDown())
//            {
//                System.out.println("Shift key + A pressed");
//            }
//            else
//            {
//                System.out.println("A key pressed");
//            }
//        }
//    }
}

