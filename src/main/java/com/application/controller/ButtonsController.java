package com.application.controller;

import com.application.model.AppointRepair;
import com.application.model.Car;
import com.application.model.CarDetalis;
import com.application.model.Regular;
import com.dao.DaoFactory;
import com.dao.MySQL.*;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 23.05.2015.
 */
public class ButtonsController {

    public Regular buttonRegularClick(AppointRepair appointRepair, ObservableList<Regular> list) {
        Regular regular = new Regular();
        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            MySQLAppointRepairDao appointRepairDao = new MySQLAppointRepairDao(connection);
            MySQLRegularDao regularDao = new MySQLRegularDao(connection);
            regular.setModel(appointRepair.getModel());
            regular.setNumber(appointRepair.getNumber());
            regular.setPhone(appointRepair.getPhone());
            regular.setType(appointRepair.getType());
            regular.setTonnage(appointRepair.getTonnage());
            regular.setGradYear(appointRepair.getGradYear());
            regularDao.create(regular);
            List<Regular> regulars = regularDao.getAll();
            for (int i = regulars.size(); i > regulars.size() - 1; i--) {
                list.add(regularDao.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regular;
    }

    public AppointRepair buttonAppointRepairClick(CarDetalis selectedCar, ObservableList<AppointRepair> list) {
        AppointRepair appointRepair = new AppointRepair();
        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            MySQLAppointRepairDao appointRepairDao = new MySQLAppointRepairDao(connection);
            appointRepair.setModel(selectedCar.getCarName());
            appointRepair.setNumber(selectedCar.getCarNumber());
            appointRepair.setState(selectedCar.getCarState());
            appointRepair.setPhone(selectedCar.getCarPhoneNumber());
            appointRepair.setType(selectedCar.getCarType());
            appointRepair.setTonnage(selectedCar.getCarTonnage());
            appointRepair.setGradYear(selectedCar.getCarGradYear());
            appointRepairDao.create(appointRepair);
            List<AppointRepair> repairList = appointRepairDao.getAll();
            for (int i = repairList.size(); i > repairList.size() - 1; i--)
                list.add(appointRepairDao.get(i));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointRepair;
    }

    public void buttonDelete(Car car, CarDetalis carDetalis) {
        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            MySqlCarDetalisDao carDetalisDao = new MySqlCarDetalisDao(connection);
            MySqlCarDao carDao = new MySqlCarDao(connection);
            car.setId(carDetalis.getId());
            car.setCarName(carDetalis.getCarName());
            car.setCarNumber(carDetalis.getCarNumber());
            car.setCarState(carDetalis.getCarState());
            car.setCarType(carDetalis.getCarType());
            carDetalisDao.delete(carDetalis);
            carDao.delete(car);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buttonToRepair(AppointRepair appointRepair, ObservableList<AppointRepair> list) {
        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            MySQLAppointRepairDao appointRepairDao = new MySQLAppointRepairDao(connection);
            appointRepair.setState("Исправен");
            appointRepair.setTypeMF("Поломка устранена");
            appointRepairDao.set(appointRepair);
            list.set(appointRepair.getId() - 1, appointRepair);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buttonRegularyFromCommonTable(CarDetalis carDetalis, ObservableList<Regular> list) {
        DaoFactory daoFactory = new MySqlDaoFactory();
        Car car = new Car();
        Regular regular = new Regular();
        try (Connection connection = daoFactory.getConnection()) {
            MySqlCarDetalisDao carDetalisDao = new MySqlCarDetalisDao(connection);
            MySQLRegularDao regularDao = new MySQLRegularDao(connection);
            regular.setModel(carDetalis.getCarName());
            regular.setNumber(carDetalis.getCarNumber());
            regular.setPhone(carDetalis.getCarPhoneNumber());
            regular.setType(carDetalis.getCarType());
            regular.setTonnage(carDetalis.getCarTonnage());
            regular.setGradYear(carDetalis.getCarGradYear());
            regularDao.create(regular);
            List<Regular> regulars = regularDao.getAll();
            for (int i = regulars.size(); i > regulars.size() - 1; i--) {
                list.add(regularDao.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
