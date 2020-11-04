package sample.Controllers.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ControllerLessons {
    Connection conn;
    public ControllerLessons() throws ClassNotFoundException {conn = ConnectionUtil.conDB();
    }
    @FXML
    private TextField txtTeacher;
    @FXML
    private TextField txtCourse;
    @FXML
    private TextField txtRoom;
    @FXML
    private Label labelTeacher;
    @FXML
    private Label labelCourse;
    @FXML
    private Label labelRoom;
    @FXML
    private Label status;
    @FXML
    private Button toDataBase;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

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
    @FXML
    private void pressInsert(ActionEvent event) throws SQLException {
        status.setText("");
        labelCourse.setText("");
        labelTeacher.setText("");
        labelRoom.setText("");
        String teacher = txtTeacher.getText();
        String course = txtCourse.getText();
        String room = txtRoom.getText();
        LocalDateTime now = LocalDateTime.now();
        if (teacher.isEmpty()){
            labelTeacher.setTextFill(Color.RED);
            labelTeacher.setText("Поле пусто");
        }
        if (course.isEmpty()){
            labelCourse.setTextFill(Color.RED);
            labelCourse.setText("Поле пусто");
        }
        if (room.isEmpty()){
            labelRoom.setTextFill(Color.RED);
            labelRoom.setText("Поле пусто");
        }
        if (!teacher.isEmpty() && !course.isEmpty() && !room.isEmpty()) {
            String sql = "INSERT INTO lessons(teacher, course, room, lesson_date)VALUES(?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, teacher);
            statement.setString(2, course);
            statement.setString(3, room);
            statement.setString(4, dtf.format(now));
            int result = statement.executeUpdate();
            if (result == 1) {
                ResultSet resultSetInsert = statement.getGeneratedKeys();
                while (resultSetInsert.next()) {
                    int id = resultSetInsert.getInt(1);
                    status.setTextFill(Color.web("#0076a3"));
                    status.setText(dtf.format(now) + " зарегистрирован под id = " + id);
                }
            }
        }
    }
}
