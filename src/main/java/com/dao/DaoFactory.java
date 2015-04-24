package com.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Alexander on 18.04.2015.
 */
public interface DaoFactory {

    public Connection getConnection() throws SQLException;

    public void close() throws SQLException;

    public CarInfoDAO getCarInfoDao(Connection connection);

    public RouteDao getRouteDao(Connection connection);
}
