package com.application.controller;

import com.application.model.Car;
import com.application.model.CarDetalis;
import com.dao.DaoFactory;
import com.dao.MySQL.MySqlCarDao;
import com.dao.MySQL.MySqlCarDetalisDao;
import com.dao.MySQL.MySqlDaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Alexander on 10.05.2015.
 */
public class CarAddController {

    @FXML
    private TextField idField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField tonnageField;

    @FXML
    private TextField phoneDriverField;

    @FXML
    private TextField gradYearField;

    private Stage dialogStage;
    private CarDetalis carDetalis;
    private Car car;
    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCarDetalis(CarDetalis carDetalis, Car car) {
        this.carDetalis = carDetalis;
        this.car = car;
        idField.setText(String.valueOf(carDetalis.getId()));
        modelField.setText(carDetalis.getCarName());
        numberField.setText(carDetalis.getCarNumber());
        typeField.setText(carDetalis.getCarType());
        stateField.setText(carDetalis.getCarState());
        tonnageField.setText(carDetalis.getCarTonnage());
        phoneDriverField.setText(carDetalis.getCarPhoneNumber());
        gradYearField.setText(carDetalis.getCarGradYear());

        idField.setText(String.valueOf(car.getId()));
        modelField.setText(car.getCarName());
        numberField.setText(car.getCarNumber());
        typeField.setText(car.getCarType());
        stateField.setText(car.getCarState());

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            if (isInputValid()) {
                MySqlCarDetalisDao carDetalisDao = new MySqlCarDetalisDao(connection);
                MySqlCarDao carDao = new MySqlCarDao(connection);
                car.setId(Integer.parseInt(idField.getText()));
                car.setCarName(modelField.getText());
                car.setCarNumber(numberField.getText());
                car.setCarState(stateField.getText());
                car.setCarType(typeField.getText());


                carDetalis.setId(Integer.parseInt(idField.getText()));
                carDetalis.setCarName(modelField.getText());
                carDetalis.setCarNumber(numberField.getText());
                carDetalis.setCarType(typeField.getText());
                carDetalis.setCarState(stateField.getText());
                carDetalis.setCarTonnage(tonnageField.getText());
                carDetalis.setCarPhoneNumber(phoneDriverField.getText());
                carDetalis.setCarGradYear(gradYearField.getText());

                carDao.create(car);
                carDetalisDao.create(carDetalis);

                okClicked = true;
                dialogStage.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (idField.getText() == null || idField.getText().length() == 0) {
            errorMessage += "No valid id!\n";
        }

        if (modelField.getText() == null || modelField.getText().length() == 0) {
            errorMessage += "No valid model car!\n";
        }

        if (numberField.getText() == null || numberField.getText().length() == 0) {
            errorMessage += "No valid car number!\n";
        }

        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "No valid type car!\n";
        }

        if (stateField.getText() == null || stateField.getText().length() == 0) {
            errorMessage += "No valid state car!\n";
        }

        if (tonnageField.getText() == null || tonnageField.getText().length() == 0) {
            errorMessage += "No valid tonnage car!\n";
        }

        if (phoneDriverField.getText() == null || phoneDriverField.getText().length() == 0) {
            errorMessage += "No valid phone driver!\n";
        }

        if (gradYearField.getText() == null || gradYearField.getText().length() == 0) {
            errorMessage += "No valid graduation year car!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Dialogs.create()
                    .title("Invalid fields")
                    .masthead("Correct invalid fields")
                    .message(errorMessage)
                    .showError();
            return false;
        }

    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
