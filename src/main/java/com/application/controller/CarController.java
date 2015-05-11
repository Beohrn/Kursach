package com.application.controller;

import com.application.model.AppointRepair;
import com.application.model.Car;
import com.application.model.CarDetalis;
import com.application.view.ApplicationStart;
import com.dao.DaoFactory;
import com.dao.MySQL.MySQLAppointRepairDao;
import com.dao.MySQL.MySqlCarDao;
import com.dao.MySQL.MySqlCarDetalisDao;
import com.dao.MySQL.MySqlDaoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.dialog.Dialogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private Button delete;


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
    private TableColumn<AppointRepair, String> phoneColumnMF;

    @FXML
    private TableColumn<AppointRepair, String> typeMullFuncColumn;

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
        showCarDetalis(null);

        carTableView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> showCarDetalis(newValue)
        );

        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DaoFactory daoFactory = new MySqlDaoFactory();
                try (Connection connection = daoFactory.getConnection()) {
                    MySqlCarDetalisDao carDetalisDao = new MySqlCarDetalisDao(connection);
                    MySqlCarDao carDao = new MySqlCarDao(connection);
                    Car car = new Car();

                    CarDetalis carDetalis = carTableView.getSelectionModel().getSelectedItem();
                    car.setId(carDetalis.getId());
                    car.setCarName(carDetalis.getCarName());
                    car.setCarNumber(carDetalis.getCarNumber());
                    car.setCarState(carDetalis.getCarState());
                    car.setCarType(carDetalis.getCarType());
                    int index = carTableView.getSelectionModel().getSelectedIndex();
                    if (index >= 0) {
                        carDetalisDao.delete(carDetalis);
                        carDao.delete(car);
                        carTableView.getItems().remove(index);

                    } else {
                        Dialogs.create()
                                .title("No selection")
                                .masthead("No Car Selected")
                                .message("Please select a person in the table!")
                                .showWarning();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

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


    private ObservableList<AppointRepair> listApp = FXCollections.observableArrayList();
    @FXML
    private void handleAppointRepair() {
        CarDetalis selectedCar = carTableView.getSelectionModel().getSelectedItem();
        AppointRepair appointRepair = new AppointRepair();


        if (selectedCar != null) {
            DaoFactory daoFactory = new MySqlDaoFactory();
            try (Connection connection = daoFactory.getConnection()) {
                MySQLAppointRepairDao appointRepairDao = new MySQLAppointRepairDao(connection);
                appointRepair.setModel(selectedCar.getCarName());
                appointRepair.setNumber(selectedCar.getCarNumber());
                appointRepair.setState(selectedCar.getCarState());
                appointRepair.setPhone(selectedCar.getCarPhoneNumber());
                appointRepair.setType(selectedCar.getCarType());
                appointRepairDao.create(appointRepair);

                List<AppointRepair> list = appointRepairDao.getAll();
                for (int i = list.size(); i > list.size() - 1; i--) {
                    listApp.add(appointRepairDao.get(i));
                }

                idMFColumn.setCellValueFactory(new PropertyValueFactory<AppointRepair, Integer>("id"));
                modelColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("model"));
                numberColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("number"));
                stateColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("state"));
                typeColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("type"));
                carMallFuncTable.setItems(listApp);
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
            idMFColumn.setCellValueFactory(new PropertyValueFactory<AppointRepair, Integer>("id"));
            modelColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("model"));
            numberColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("number"));
            stateColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("state"));
            typeColumnMF.setCellValueFactory(new PropertyValueFactory<AppointRepair, String>("type"));
            carMallFuncTable.setItems(listApp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void

}
