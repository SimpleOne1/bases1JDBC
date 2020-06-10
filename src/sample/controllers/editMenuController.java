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
import sample.DbConnection;
import sample.Plant;

public class editMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button idChooserButton;

    @FXML
    private TextField idText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField colourText;

    @FXML
    private TextField priceText;

    @FXML
    private TextField sortText;

    @FXML
    private Button changeButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
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
        idChooserButton.setOnAction(event -> {
            try {
                DbConnection dbConnection=new DbConnection();
                Plant plant= dbConnection.getPlant(new Long(idText.getText()));
                nameText.setText(plant.getName());
                colourText.setText(plant.getColour());
                priceText.setText(plant.getPrice().toString());
                sortText.setText(plant.getSort());
            }
            catch(SQLException e){
                System.out.println("Не удалось найти запись.");
            }
        });
        changeButton.setOnAction(event -> {
            try {
                DbConnection dbConnection=new DbConnection();
                Plant plant= dbConnection.getPlant(new Long(idText.getText()));
                plant.setName(nameText.getText());
                plant.setColour(colourText.getText());
                plant.setPrice(new BigDecimal(priceText.getText()));
                plant.setSort(sortText.getText());
                dbConnection.editPlant(plant);
            }
            catch(SQLException e){

            }
        });
    }
}
