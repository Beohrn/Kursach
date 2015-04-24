package com.main;

import com.application.model.MainAPP;
import com.dao.CarInfoDAO;
import com.dao.DaoFactory;
import com.dao.MySQL.MySQLDaoFactory;

import com.dao.RouteDao;
import com.entities.CarInformation;

import com.entities.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 18.04.2015.
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        DaoFactory daoFactory = new MySQLDaoFactory();
//        List<CarInformation> list;
//        try (Connection connection = daoFactory.getConnection()){
//
//
//            //RouteDao routeDao = daoFactory.getRouteDao(connection);
////            Route route = new Route();
////            route.setIdRoute(2);
////            route.setCar("Volvo");
////
////            routeDao.create(route);
////
//            //CarInfoDAO carInfoDAO = daoFactory.getCarInfoDao(connection);
////
////            CarInformation carInformation = new CarInformation();
////            carInformation.setIdCar(2);
////            carInformation.setModelCar("Volvo FMX");
////            carInformation.setState("ready");
////            carInformation.setNumber("АІ 4205 К");
////            carInfoDAO.create(carInformation);
//
//            //System.out.println(carInfoDAO.read(1));
//            //routeDao.read(1);
//
//            //daoFactory.close();
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        DaoFactory daoFactory = new MySQLDaoFactory();
        List<CarInformation> list;
        try (Connection connection = daoFactory.getConnection()){
            RouteDao routeDao = daoFactory.getRouteDao(connection);
            Route route = new Route();
            route.setIdRoute(4);
            route.setCar("Volvo");
            routeDao.create(route);
            CarInfoDAO carInfoDAO = daoFactory.getCarInfoDao(connection);
            CarInformation carInformation = new CarInformation();
            carInformation.setIdCar(4);
            carInformation.setModelCar("Volvo FMX");
            carInformation.setState("ready");
            carInformation.setNumber("АІ 4205 К");
            carInfoDAO.create(carInformation);
            System.out.println(carInfoDAO.read(1));
            System.out.println(routeDao.read(1));
            daoFactory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MainAPP.MAIN(args);
    }
}
