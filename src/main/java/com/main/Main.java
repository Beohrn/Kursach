package com.main;

import com.application.model.AppointRepair;
import com.application.model.Car;
import com.application.model.CarDetalis;
import com.application.view.ApplicationStart;

import com.dao.DaoFactory;
import com.dao.GenericDao;
import com.dao.MySQL.MySqlCarDao;
import com.dao.MySQL.MySqlCarDetalisDao;
import com.dao.MySQL.MySqlDaoFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 18.04.2015.
 */
public class Main {

    public static void main(String[] args) throws Exception {


        DaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection =  daoFactory.getConnection()) {


//            Car car = new Car();
//            car.setId(3);
//            car.setCarName("Mac dfsss");
//            car.setCarNumber("jk 234e23 0");
//            car.setCarState("OK");
//            car.setCarType("Massive");
//            MySqlCarDao carDao = new MySqlCarDao(connection);
//            carDao.createMFid(car);
//
////            CarDetalis carDetalis = new CarDetalis();
////            carDetalis.setId(2);
////            carDetalis.setCarName(car.getCarName());
////            carDetalis.setCarNumber(car.getCarNumber());
////            carDetalis.setCarType(car.getCarType());
////            carDetalis.setCarState(car.getCarState());
////            carDetalis.setCarTonnage("10 T");
////            carDetalis.setCarPhoneNumber("0931992804");
////            carDetalis.setCarGradYear("2003");
////
////            MySqlCarDetalisDao carDetalisDao = new MySqlCarDetalisDao(connection);
////
////            carDetalisDao.create(carDetalis);
////            System.out.println(carDetalisDao.getAll());
//
//

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ApplicationStart.START(args);





    }

    }

