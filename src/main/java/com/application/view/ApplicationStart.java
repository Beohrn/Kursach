package com.application.view;

import com.application.controller.CarAddController;
import com.application.controller.CarController;
import com.application.controller.CarEditController;
import com.application.model.Car;
import com.application.model.CarDetalis;
import com.dao.DaoFactory;
import com.dao.MySQL.MySqlCarDao;
import com.dao.MySQL.MySqlCarDetalisDao;
import com.dao.MySQL.MySqlDaoFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 10.05.2015.
 */
public class ApplicationStart extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MainAPP");

       // initRootLayout();
        showCarOverview();
    }

    private ObservableList<CarDetalis> observableListCar = FXCollections.observableArrayList();

    public ApplicationStart() {
        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            MySqlCarDetalisDao carDao = new MySqlCarDetalisDao(connection);
            List<CarDetalis> list = carDao.getAll();
            for (int i = 1; i <= list.size(); i++) {
                observableListCar.add(carDao.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<CarDetalis> getObservableListCar() {
        return observableListCar;
    }


    public void showCarOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ApplicationStart.class.getResource("/fxml/CarOverview.fxml"));
            rootLayout =  loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            CarController carController = loader.getController();
            carController.setApplicationStart(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showCarEditDialog(CarDetalis carDetalis, Car car) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ApplicationStart.class.getResource("/fxml/CarEditDialog.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Car");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            CarEditController carEditController = loader.getController();
            carEditController.setDialogStage(dialogStage);
            carEditController.setCarDetalis(carDetalis, car);
            dialogStage.showAndWait();
            return carEditController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showCarAddDialog(CarDetalis carDetalis, Car car) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ApplicationStart.class.getResource("/fxml/CarAddDialog.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Car");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            CarAddController carAddController = loader.getController();
            carAddController.setDialogStage(dialogStage);
            carAddController.setCarDetalis(carDetalis, car);
            dialogStage.showAndWait();
            return carAddController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void START(String[] args) {
        launch(args);
    }
}
