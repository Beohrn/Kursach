package com.dao.MySQL;

import com.dao.CarInfoDAO;
import com.dao.DaoFactory;
import com.dao.RouteDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alexander on 18.04.2015.
 */
public class MySQLDaoFactory implements DaoFactory {

    private String login = "root";
    private String password = "1234";
    private String url = "jdbc:mysql://localhost:3306/mydatabase";
    private String driver = "com.mysql.jdbc.Driver";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }

    @Override
    public CarInfoDAO getCarInfoDao(Connection connection) {
        return new MySqlCarInfoDAO(connection);
    }

    @Override
    public RouteDao getRouteDao(Connection connection) {
        return new MySqlRouteDao(connection);
    }

    public void close() throws SQLException {
        getConnection().close();
    }
}
