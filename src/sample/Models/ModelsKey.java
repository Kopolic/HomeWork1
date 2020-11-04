package sample.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ConnectionUtil;

import java.awt.*;
import java.io.IOException;
import java.sql.*;

public class ModelsKey {
    Connection conn;
    public ModelsKey() throws ClassNotFoundException {conn = ConnectionUtil.conDB();
    }
    public void pressReg(Label labelPassword, Label labelEmail, Label labelLogin, Label status, TextField txtPassword,TextField txtLogin, TextField txtEmail, TextField txtFirstName, TextField txtLastName) throws SQLException {
        labelPassword.setText("");
        labelEmail.setText("");
        labelLogin.setText("");
        status.setText("");
        boolean bool = true;
        String checkLogin;
        String checkEmail;
        String pass = txtPassword.getText();
        String login = txtLogin.getText();
        String email = txtEmail.getText();
        String sql = "SELECT * FROM users WHERE login=? or email=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,login);
        statement.setString(2,email);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            checkLogin = resultSet.getString("login");
            checkEmail = resultSet.getString("email");
            if(login.equals(checkLogin) && email.equals(checkEmail)) {
                labelLogin.setTextFill(Color.web("#cd0000"));
                labelEmail.setTextFill(Color.web("#cd0000"));
                labelLogin.setText("Такой логин уже существует");
                labelEmail.setText("Такая почта уже существует");
            }
            if (login.equals(checkLogin) && !email.equals(checkEmail)){
                labelLogin.setTextFill(Color.web("#cd0000"));
                labelEmail.setTextFill(Color.web("#cd0000"));
                labelLogin.setText("Такой логин уже существует");
            }
            if (!login.equals(checkLogin) && email.equals(checkEmail)){
                labelLogin.setTextFill(Color.web("#cd0000"));
                labelEmail.setTextFill(Color.web("#cd0000"));
                labelEmail.setText("Такая почта уже существует");

            }
            bool = false;
        }
        //Я еще пустые поля задал паролю
        if(login.isEmpty()){
            labelLogin.setTextFill(Color.web("#cd0000"));
            labelLogin.setText("Поле пусто");
            labelPassword.setTextFill(Color.web("#cd0000"));
            bool = false;
        }
        if(email.isEmpty()){
            labelEmail.setTextFill(Color.web("#cd0000"));
            labelEmail.setText("Поле пусто");
            labelPassword.setTextFill(Color.web("#cd0000"));
            bool = false;
        }
        if (pass.isEmpty()){
            labelLogin.setTextFill(Color.web("#cd0000"));
            labelEmail.setTextFill(Color.web("#cd0000"));
            labelPassword.setTextFill(Color.web("#cd0000"));
            labelPassword.setText("Поле пусто");
            bool = false;
        }
        if (email.isEmpty() && login.isEmpty()){
            labelLogin.setTextFill(Color.web("#cd0000"));
            labelLogin.setText("Поле пусто");
            labelEmail.setTextFill(Color.web("#cd0000"));
            labelEmail.setText("Поле пусто");
            labelPassword.setTextFill(Color.web("#cd0000"));
            bool = false;
        }
        if (email.isEmpty() && pass.isEmpty()){
            labelLogin.setTextFill(Color.web("#cd0000"));
            labelEmail.setTextFill(Color.web("#cd0000"));
            labelEmail.setText("Поле пусто");
            labelPassword.setTextFill(Color.web("#cd0000"));
            labelPassword.setText("Поле пусто");
            bool = false;
        }
        if (login.isEmpty() && pass.isEmpty()){
            labelLogin.setTextFill(Color.web("#cd0000"));
            labelLogin.setText("Поле пусто");
            labelEmail.setTextFill(Color.web("#cd0000"));
            labelPassword.setTextFill(Color.web("#cd0000"));
            labelPassword.setText("Поле пусто");
            bool = false;
        }
        if (email.isEmpty() && login.isEmpty() && pass.isEmpty()){
            labelLogin.setTextFill(Color.web("#cd0000"));
            labelLogin.setText("Поле пусто");
            labelEmail.setTextFill(Color.web("#cd0000"));
            labelEmail.setText("Поле пусто");
            labelPassword.setTextFill(Color.web("#cd0000"));
            labelPassword.setText("Поле пусто");
            bool = false;
        }
        if (bool){
            String sqlInsert = "INSERT INTO users(firstName, lastName, email, login, password)VALUES(?, ?, ?, ?, ?)";
            PreparedStatement statementInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            statementInsert.setString(1, txtFirstName.getText());
            statementInsert.setString(2, txtLastName.getText());
            statementInsert.setString(3, txtEmail.getText());
            statementInsert.setString(4, txtLogin.getText());
            statementInsert.setString(5, txtPassword.getText());
            int result = statementInsert.executeUpdate();
            if (result == 1) {
                ResultSet resultSetInsert = statementInsert.getGeneratedKeys();
                while (resultSetInsert.next()) {
                    int id = resultSetInsert.getInt(1);
                    status.setTextFill(Color.web("#0076a3"));
                    status.setText(txtFirstName.getText() + " " + txtLastName.getText() + " зарегистрирован id = " + id);
                }
            }
        }
    }
    public void pressSignInButton(Label status, Label labelLogin, Label labelPass, TextField txtLogin, TextField txtPassword, Button buttonToDataBase) throws SQLException {
        boolean bool = true;
        status.setText("");
        labelLogin.setText("");
        labelPass.setText("");
        String checkLogin = "";
        String checkPass = "";
        String login = txtLogin.getText();
        String pass = txtPassword.getText();
        if (login.isEmpty()){
            labelLogin.setTextFill(Color.RED);
            labelLogin.setText("Поле пусто");
        }
        if (pass.isEmpty()){
            labelPass.setTextFill(Color.RED);
            labelPass.setText("Поле пусто");
        }
        if (login.isEmpty() && pass.isEmpty()){
            labelPass.setTextFill(Color.RED);
            labelPass.setText("Поле пусто");
            labelLogin.setTextFill(Color.RED);
            labelLogin.setText("Поле пусто");
        }
        if (!login.isEmpty() && !pass.isEmpty()) {
            String sql = "SELECT * FROM users WHERE login=? and password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,login);
            statement.setString(2,pass);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                checkLogin = resultSet.getString("login");
                checkPass = resultSet.getString("password");
                if (login.equals(checkLogin) && pass.equals(checkPass)) {
                    bool = false;
                    status.setText("Вы вошли");
                    status.setTextFill(Color.web("#0076a3"));
                    buttonToDataBase.setOnAction(event1 -> {
                        buttonToDataBase.getScene().getWindow().hide();
                        try {
                            Stage primaryStage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/database.fxml"));
                            primaryStage.setTitle("База даныых");
                            primaryStage.setScene(new Scene(root));
                            primaryStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            if (bool) {
                status.setTextFill(Color.RED);
                status.setText("Неверный логин или пароль");
            }
        }

    }
    public void pressSignInHandle(Label status, Label labelLogin, Label labelPass, TextField txtLogin, TextField txtPassword, Button buttonToDataBase) throws SQLException {
        boolean bool = true;
        status.setText("");
        labelLogin.setText("");
        labelPass.setText("");
        String checkLogin = "";
        String checkPass = "";
        String login = txtLogin.getText();
        String pass = txtPassword.getText();
        if (login.isEmpty()) {
            labelLogin.setTextFill(Color.RED);
            labelLogin.setText("Поле пусто");
        }
        if (pass.isEmpty()) {
            labelPass.setTextFill(Color.RED);
            labelPass.setText("Поле пусто");
        }
        if (login.isEmpty() && pass.isEmpty()) {
            labelPass.setTextFill(Color.RED);
            labelPass.setText("Поле пусто");
            labelLogin.setTextFill(Color.RED);
            labelLogin.setText("Поле пусто");
        }
        if (!login.isEmpty() && !pass.isEmpty()) {
            String sql = "SELECT * FROM users WHERE login=? and password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                checkLogin = resultSet.getString("login");
                checkPass = resultSet.getString("password");
                if (login.equals(checkLogin) && pass.equals(checkPass)) {
                    bool = false;
                    status.setText("Вы вошли");
                    status.setTextFill(Color.web("#0076a3"));
                    buttonToDataBase.getScene().getWindow().hide();
                    try {
                        Stage primaryStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/database.fxml"));
                        primaryStage.setTitle("База даныых");
                        primaryStage.setScene(new Scene(root));
                        primaryStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (bool) {
                status.setTextFill(Color.RED);
                status.setText("Неверный логин или пароль");
            }
        }
    }
}
