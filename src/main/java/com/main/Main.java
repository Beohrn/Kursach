package com.main;

import com.dao.DaoFactory;
import com.dao.MySQL.MySQLDaoFactory;
import com.dao.R_StateDao;
import com.dao.RouteDao;
import com.entities.R_State;
import com.entities.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 18.04.2015.
 */
public class Main {

    public static void main(String[] args) {
        DaoFactory daoFactory = new MySQLDaoFactory();
        List<R_State> list;
        try (Connection connection = daoFactory.getConnection()){
            R_StateDao dao = daoFactory.getR_StateDao(connection);
            list = dao.getAll();
            System.out.println(list);

            RouteDao routeDao = daoFactory.getRouteDao(connection);
            Route route = new Route();
            route.setIdRoute(1);
            route.setCar("Volvo");
            route.setState(4);
            //List<R_State> list1;
            routeDao.create(route);
            System.out.println("1 >> " + routeDao.getAll());
            route.setIdRoute(1);
            routeDao.delete(route);
            System.out.println("2 >> " + routeDao.getAll());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
