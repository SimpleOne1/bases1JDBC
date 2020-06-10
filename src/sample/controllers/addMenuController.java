package sample.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Plant;
import sample.DbConnection;
public class addMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField colourField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField sortField;





    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            Plant plant =new Plant(nameField.getText(),colourField.getText(),new BigDecimal(priceField.getText()),sortField.getText());
            try {
                DbConnection dbConnection=new DbConnection();
                dbConnection.addPlant(plant);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/views/mainMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
