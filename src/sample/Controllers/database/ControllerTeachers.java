package sample.Controllers.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class ControllerTeachers {
    Connection conn;
    public ControllerTeachers() throws ClassNotFoundException {conn = ConnectionUtil.conDB();
    }
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddr;
    @FXML
    private TextField txtPhone;
    @FXML
    private Label labelName;
    @FXML
    private Label labelAddr;
    @FXML
    private Label labelPhone;
    @FXML
    private Label status;
    @FXML
    private Button toDataBase;

    @FXML
    private void pressInsert(ActionEvent event) throws SQLException {
        labelName.setText("");
        labelAddr.setText("");
        labelPhone.setText("");
        status.setText("");
        String name = txtName.getText();
        String addr = txtAddr.getText();
        String phone = txtPhone.getText();
        if (name.isEmpty()){
            labelName.setTextFill(Color.RED);
            labelName.setText("Поле пусто");
        }
        if(addr.isEmpty()){
            labelAddr.setTextFill(Color.RED);
            labelAddr.setText("Поле пусто");
        }
        if(phone.isEmpty()){
            labelPhone.setTextFill(Color.RED);
            labelPhone.setText("Поле пусто");
        }
        if (!name.isEmpty() && !addr.isEmpty() && !phone.isEmpty()) {
            String sql = "INSERT INTO teachers(name, addr, phone)VALUES(?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, addr);
            statement.setString(3, phone);
            int result = statement.executeUpdate();
            if (result == 1) {
                ResultSet resultSetInsert = statement.getGeneratedKeys();
                while (resultSetInsert.next()) {
                    int id = resultSetInsert.getInt(1);
                    status.setTextFill(Color.web("#0076a3"));
                    status.setText(name + " зарегистрирован под id = " + id);
                }
            }
        }
    }
    @FXML
    private void pressToDataBase(ActionEvent event){
        toDataBase.getScene().getWindow().hide();
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
