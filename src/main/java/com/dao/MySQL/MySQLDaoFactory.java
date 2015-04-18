package com.dao.MySQL;

import com.dao.DaoFactory;
import com.dao.R_StateDao;
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
    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String driver = "com.mysql.jdbc.Driver";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }

    @Override
    public R_StateDao getR_StateDao(Connection connection) {
        return new MySqlR_StateDao(connection);
    }

    @Override
    public RouteDao getRouteDao(Connection connection) {
        return new MySqlRouteDao(connection);
    }
}
