package com.application.controller;

import com.application.model.AppointRepair;
import com.application.model.Car;
import com.application.model.CarDetalis;
import com.application.model.Regular;
import com.application.view.ApplicationStart;
import com.dao.DaoFactory;
import com.dao.MySQL.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Priority;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.util.Callback;
import org.controlsfx.dialog.Dialogs;

import javax.swing.plaf.synth.ColorType;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alexander on 10.05.2015.
 */
public class CarController {

    @FXML
    private TableView<CarDetalis> carTableView;

    @FXML
    private TableColumn<CarDetalis, Integer> idColumn;

    @FXML
    private TableColumn<CarDetalis, String> nameColumn;

    @FXML
    private TableColumn<CarDetalis, String> numberColumn;

    @FXML
    private TableColumn<CarDetalis, String> stateColumn;

    @FXML
    private TableColumn<CarDetalis, String> typeColumn;

    @FXML
    private Label name;

    @FXML
    private Label number;

    @FXML
    private Label type;

    @FXML
    private Label state;

    @FXML
    private Label tonnage;

    @FXML
    private Label phoneDriver;

    @FXML
    private Label graduationYear;

    @FXML
    private TableView<AppointRepair> carMallFuncTable;

    @FXML
    private TableColumn<AppointRepair, Integer> idMFColumn;

    @FXML
    private TableColumn<AppointRepair, String> modelColumnMF;

    @FXML
    private TableColumn<AppointRepair, String> numberColumnMF;

    @FXML
    private TableColumn<AppointRepair, String> stateColumnMF;

    @FXML
    private TableColumn<AppointRepair, String> typeColumnMF;

    @FXML
    private TableColumn<AppointRepair, String> typeMullFuncColumn;

    @FXML
    private TableColumn<AppointRepair, String> phoneMF;

    @FXML
    private TableColumn<AppointRepair, String> tonnageMF;

    @FXML
    private TableColumn<AppointRepair, String> gradMF;

    @FXML
    private TextField mullfuncField;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TableView<Regular> regularTableView;

    @FXML
    private TableColumn<Regular, Integer> idRegular;

    @FXML
    private TableColumn<Regular, String> modelRegular;

    @FXML
    private TableColumn<Regular, String> numberRegular;

    @FXML
    private TableColumn<Regular, String> phoneRegular;

    @FXML
    private TableColumn<Regular, String> typeRegular;

    @FXML
    private TableColumn<Regular, String> tonnageRegular;

    @FXML
    private TableColumn<Regular, String> gradRegular;

    private ObservableList<Regular> regularObservableList = FXCollections.observableArrayList();
    private ObservableList<AppointRepair> listApp = FXCollections.observableArrayList();
    private ApplicationStart applicationStart;

    public CarController() {

    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<CarDetalis, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<CarDetalis, String>("carName"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<CarDetalis, String>("carNumber"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<CarDetalis, String>("carState"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<CarDetalis, String>("carType"));




        addMF();
        addRegulary();
        showCarDetalis(null);

        carTableView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> showCarDetalis(newValue)
        );
    }
    private ObservableList<String> stateList = FXCollections.observableArrayList("Свободен", "На маршруте");
    private void forRegular(ObservableList<Regular> list) {
        idRegular.setCellValueFactory(new PropertyValueFactory<Regular, Integer>("id"));
        modelRegular.setCellValueFactory(new PropertyValueFactory<Regular, String>("model"));
        numberRegular.setCellValueFactory(new PropertyValueFactory<Regular, String>("number"));
        phoneRegular.setCellValueFactory(new PropertyValueFactory<Regular, String>("phone"));
        typeRegular.setCellValueFactory(new PropertyValueFactory<Regular, String>("type"));
        tonnageRegular.setCellValueFactory(new PropertyValueFactory<Regular, String>("tonnage"));
        gradRegular.setCellValueFactory(new PropertyValueFactory<Regular, String>("gradYear"));

        regularTableView.setItems(list);
    }

    private void forAppointrepair(ObservableList<AppointRepair> list) {
        idMFColumn.setCellValueFactory(new PropertyValueFactory<AppointRepair, Integer>("id"));
        modelColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("model"));
        numberColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("number"));
        stateColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("state"));
        typeColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("type"));
        carMallFuncTable.setItems(list);
    }

    public void setApplicationStart(ApplicationStart applicationStart) {
        this.applicationStart = applicationStart;
        carTableView.setItems(applicationStart.getObservableListCar());

    }

    private void showCarDetalis(CarDetalis carDetalis) {
        if (carDetalis != null) {
            name.setText(carDetalis.getCarName());
            number.setText(carDetalis.getCarNumber());
            type.setText(carDetalis.getCarType());
            state.setText(carDetalis.getCarState());
            tonnage.setText(carDetalis.getCarTonnage());
            phoneDriver.setText(carDetalis.getCarPhoneNumber());
            graduationYear.setText(carDetalis.getCarGradYear());

        } else {
            name.setText("");
            number.setText("");
            type.setText("");
            state.setText("");
            tonnage.setText("");
            phoneDriver.setText("");
            graduationYear.setText("");
        }
    }

    @FXML
    private void handleNewCar() {
        CarDetalis tempCar = new CarDetalis();
        Car carTemp = new Car();
        boolean okClicked = applicationStart.showCarAddDialog(tempCar, carTemp);
        if (okClicked) {
            applicationStart.getObservableListCar().add(tempCar);
        }
    }

    @FXML
    private void handleDelete() {
        ButtonsController controller = new ButtonsController();
        CarDetalis carDetalis = carTableView.getSelectionModel().getSelectedItem();

        controller.buttonDelete(carDetalis);
        int index = carTableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            carTableView.getItems().remove(index);

        } else {
            Dialogs.create()
                    .title("No selection")
                    .masthead("No Car Selected")
                    .message("Please select a person in the table!")
                    .showWarning();
        }
    }


    @FXML
    private void handleAppointRepair() {
        CarDetalis selectedCar = carTableView.getSelectionModel().getSelectedItem();

        ButtonsController controller = new ButtonsController();
        if (selectedCar != null) {
            controller.buttonAppointRepairClick(selectedCar, listApp);
            forAppointrepair(listApp);
            int index = carTableView.getSelectionModel().getSelectedIndex();
            if (index >= 0)
                carTableView.getItems().remove(index);
                controller.buttonDelete(selectedCar);
        } else {
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Car Selected")
                    .message("Please select a car in the table")
                    .showWarning();
        }
    }

    private void addMF() {
        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            MySQLAppointRepairDao appointRepairDao = new MySQLAppointRepairDao(connection);
            List<AppointRepair> list = appointRepairDao.getAll();
            for (int i = 1; i <= list.size(); i++) {
                listApp.add(appointRepairDao.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            typeMullFuncColumn.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("typeMF"));

            forAppointrepair(listApp);
        }
    }

    @FXML
    private void handleConfirm() {
        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            MySQLAppointRepairDao appointRepairDao = new MySQLAppointRepairDao(connection);
            AppointRepair appointRepair = carMallFuncTable.getSelectionModel().getSelectedItem();
            appointRepair.setTypeMF(mullfuncField.getText());
            appointRepairDao.update(appointRepair);
            listApp.set(appointRepair.getId() - 1, appointRepair);
            carMallFuncTable.setItems(listApp);
            typeMullFuncColumn.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("typeMF"));
            mullfuncField.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToRepair() {
        AppointRepair appointRepair = carMallFuncTable.getSelectionModel().getSelectedItem();
        ButtonsController controller = new ButtonsController();
        controller.buttonToRepair(appointRepair, listApp);
        carMallFuncTable.setItems(listApp);
        Task task = createWorker();
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
        stateColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("state"));
        typeMullFuncColumn.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("typeMF"));
    }


    private Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(20);
                    updateProgress(i + 1, 10);
                }
                return true;
            }
        };
    }

    @FXML
    private void handleRegulary() {
        ButtonsController controller = new ButtonsController();
        AppointRepair appointRepair = carMallFuncTable.getSelectionModel().getSelectedItem();
        if (appointRepair != null) {
            controller.buttonRegularClick(appointRepair, regularObservableList);
            forRegular(regularObservableList);
        }

    }

    private void addRegulary() {
        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            MySQLRegularDao regularDao = new MySQLRegularDao(connection);
            List<Regular> list = regularDao.getAll();
            for (int i = 1; i <= list.size(); i++) {
                regularObservableList.add(regularDao.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            forRegular(regularObservableList);
        }
    }

    @FXML
    private void handleRegularyFromCommonTable() {
        CarDetalis carDetalis = carTableView.getSelectionModel().getSelectedItem();
        ButtonsController controller = new ButtonsController();
        controller.buttonRegularyFromCommonTable(carDetalis, regularObservableList);
        forRegular(regularObservableList);
        int index = carTableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            carTableView.getItems().remove(index);

        } else {
            Dialogs.create()
                    .title("No selection")
                    .masthead("No Car Selected")
                    .message("Please select a person in the table!")
                    .showWarning();
        }
    }

    @FXML
    private void handleWritten() {
        AppointRepair appointRepair = carMallFuncTable.getSelectionModel().getSelectedItem();
        ButtonsController controller = new ButtonsController();
        int index = carMallFuncTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            controller.buttonWritten(appointRepair);
            carMallFuncTable.getItems().remove(index);
        }

    }

    @FXML
    private void handleSend() {
        Dialogs.create()
                .title("Отправка")
                .message("Список исправных авто оправлен технологу")
                .showInformation();
    }

    @FXML
    private void handleAbout() {
        Dialogs.create()
                .title("О программе")
                .masthead("бла-бла")
                .message("Автор: Алесандр")
                .showInformation();
    }

    @FXML
    private void handleClose() {
        System.exit(0);
    }



}
