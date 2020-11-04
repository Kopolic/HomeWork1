package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ConnectionUtil;
import sample.Models.ModelCourses;
import sample.Models.ModelLessons;
import sample.Models.ModelTeachers;
import sample.Models.ModelUsers;

import java.io.IOException;
import java.sql.*;

public class ControllerDataBase {
    Connection conn;
    public ControllerDataBase() throws ClassNotFoundException {conn = ConnectionUtil.conDB();}

    ObservableList<ModelCourses> listCourses = FXCollections.observableArrayList();

    ObservableList<ModelLessons> listLessons = FXCollections.observableArrayList();

    ObservableList<ModelTeachers> listTeachers = FXCollections.observableArrayList();

    ObservableList<ModelUsers> listUsers = FXCollections.observableArrayList();

    @FXML
    private TableView<ModelCourses> tableCourses;
    @FXML
    private TableColumn<ModelCourses,String> coursesId;
    @FXML
    private TableColumn<ModelCourses,String> coursesTitle;
    @FXML
    private TableColumn<ModelCourses,String> coursesLength;
    @FXML
    private TableColumn<ModelCourses,String> coursesDescription;






    @FXML
    private TableView<ModelLessons> tableLessons;
    @FXML
    private TableColumn<ModelLessons,String> lessonsId;
    @FXML
    private TableColumn<ModelLessons,String> lessonsTeacher;
    @FXML
    private TableColumn<ModelLessons,String> lessonsCourses;
    @FXML
    private TableColumn<ModelLessons,String> lessonsRoom;
    @FXML
    private TableColumn<ModelLessons,String> lessonsDate;






    @FXML
    private TableView<ModelTeachers> tableTeachers;
    @FXML
    private TableColumn<ModelTeachers,String> teachersId;
    @FXML
    private TableColumn<ModelTeachers,String> teachersName;
    @FXML
    private TableColumn<ModelTeachers,String> teachersAddr;
    @FXML
    private TableColumn<ModelTeachers,String> teachersPhone;





    @FXML
    private TableView<ModelUsers> tableUser;
    @FXML
    private TableColumn<ModelUsers,String> userId;
    @FXML
    private TableColumn<ModelUsers,String> userFirstName;
    @FXML
    private TableColumn<ModelUsers,String> userLastName;
    @FXML
    private TableColumn<ModelUsers,String> userEmail;
    @FXML
    private TableColumn<ModelUsers,String> userLogin;
    @FXML
    private TableColumn<ModelUsers,String> userPassword;




    @FXML
    private TextField txtCourses;
    @FXML
    private Label statusCourses;
    @FXML
    private TextField txtUsers;
    @FXML
    private Label statusUsers;
    @FXML
    private Button updateDataBase;
    @FXML
    private TextField txtLessons;
    @FXML
    private TextField txtTeachers;
    @FXML
    private Label statusLessons;
    @FXML
    private Label statusTeachers;
    @FXML
    private Button updateCourses;
    @FXML
    private Button updateLessons;
    @FXML
    private Button updateTeachers;




    @FXML
    public void initialize() {
        try {
            String coursesSQL = "SELECT * FROM courses";
            PreparedStatement coursesStatement = conn.prepareStatement(coursesSQL);
            ResultSet resultCourses = coursesStatement.executeQuery();
            while (resultCourses.next()) {
                listCourses.add(new ModelCourses(
                        resultCourses.getString(1),
                        resultCourses.getString(2),
                        resultCourses.getString(3),
                        resultCourses.getString(4)));
            }
            coursesId.setCellValueFactory(new PropertyValueFactory<>("id"));
            coursesTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            coursesLength.setCellValueFactory(new PropertyValueFactory<>("length"));
            coursesDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

            tableCourses.setItems(listCourses);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        try {
            String sql = "SELECT * FROM lessons";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                listLessons.add(new ModelLessons(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
            lessonsId.setCellValueFactory(new PropertyValueFactory<>("id"));
            lessonsTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
            lessonsCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));
            lessonsRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
            lessonsDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            tableLessons.setItems(listLessons);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        try {
            String sql = "SELECT * FROM teachers";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                listTeachers.add(new ModelTeachers(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
            }
            teachersId.setCellValueFactory(new PropertyValueFactory<>("id"));
            teachersName.setCellValueFactory(new PropertyValueFactory<>("name"));
            teachersAddr.setCellValueFactory(new PropertyValueFactory<>("addr"));
            teachersPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

            tableTeachers.setItems(listTeachers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            String sql = "SELECT * FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                listUsers.add(new ModelUsers(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)));
            }
            userId.setCellValueFactory(new PropertyValueFactory<>("id"));
            userFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            userLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            userLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            userPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

            tableUser.setItems(listUsers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @FXML
    private void deleteCourses(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(txtCourses.getText());
        String sql = "DELETE FROM courses WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,id);
        int res = statement.executeUpdate();
        if (res == 1) {
            statusCourses.setTextFill(Color.web("#0076a3"));
            statusCourses.setText("Данные удалены");
        }
    }
    @FXML
    private void deleteTeachers(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(txtTeachers.getText()) ;
        String sql = "DELETE FROM teachers WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,id);
        int res = statement.executeUpdate();
        if (res == 1) {
            statusTeachers.setTextFill(Color.web("#0076a3"));
            statusTeachers.setText("Данные удалены");
        }
    }
    @FXML
    private void deleteLessons(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(txtLessons.getText()) ;
        String sql = "DELETE FROM lessons WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,id);
        int res = statement.executeUpdate();
        if (res == 1) {
            statusLessons.setTextFill(Color.web("#0076a3"));
            statusLessons.setText("Данные удалены");
        }
    }
    @FXML
    private void deleteUsers(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(txtUsers.getText()) ;
        String sql = "DELETE FROM users WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,id);
        int res = statement.executeUpdate();
        if (res == 1) {
            statusUsers.setTextFill(Color.web("#0076a3"));
            statusUsers.setText("Данные удалены");
        }
    }
    @FXML
    private void updateCourses(ActionEvent event) throws SQLException {
        updateCourses.getScene().getWindow().hide();
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/database/courses.fxml"));
            primaryStage.setTitle("База даныых");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String sql = "INSERT INTO courses(title)VALUES(?)";
//        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        statement.setString(1, txtCourses.getText());
//        int result = statement.executeUpdate();
//        if (result == 1) {
//            ResultSet resultSet = statement.getGeneratedKeys();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                statusCourses.setTextFill(Color.web("#0076a3"));
//                statusCourses.setText("Данные добавлены под id = " + id);
//            }
//        }
    }
    @FXML
    private void updateLessons(ActionEvent event) throws SQLException {
        updateLessons.getScene().getWindow().hide();
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/database/lessons.fxml"));
            primaryStage.setTitle("База даныых");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String sql = "INSERT INTO lessons(room)VALUES(?)";
//        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        statement.setString(1, txtLessons.getText());
//        int result = statement.executeUpdate();
//        if (result == 1) {
//            ResultSet resultSet = statement.getGeneratedKeys();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                statusLessons.setTextFill(Color.web("#0076a3"));
//                statusLessons.setText("Данные добавлены под id = " + id);
//            }
//        }
    }
    @FXML
    private void updateTeachers(ActionEvent event) throws SQLException {
        updateTeachers.getScene().getWindow().hide();
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/database/teachers.fxml"));
            primaryStage.setTitle("База даныых");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String sql = "INSERT INTO teachers(phone)VALUES(?)";
//        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        statement.setString(1, txtTeachers.getText());
//        int result = statement.executeUpdate();
//        if (result == 1) {
//            ResultSet resultSet = statement.getGeneratedKeys();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                statusTeachers.setTextFill(Color.web("#0076a3"));
//                statusTeachers.setText("Данные добавлены под id = " + id);
//            }
//        }
    }
    @FXML
    private void updateUsers(ActionEvent event) throws SQLException {

//        String sql = "INSERT INTO users(lastName)VALUES(?)";
//        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        statement.setString(1, txtUsers.getText());
//        int result = statement.executeUpdate();
//        if (result == 1) {
//            ResultSet resultSet = statement.getGeneratedKeys();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                statusUsers.setTextFill(Color.web("#0076a3"));
//                statusUsers.setText("Данные добавлены под id = " + id);
//            }
//        }
    }
    @FXML
    private void updateDataBase(ActionEvent event){
        updateDataBase.getScene().getWindow().hide();
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