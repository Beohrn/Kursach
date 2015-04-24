package com.dao.MySQL;

import com.dao.RouteDao;
import com.entities.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 18.04.2015.
 */
public class MySqlRouteDao implements RouteDao {

    private final Connection connection;

    public MySqlRouteDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Route create(Route route) throws SQLException {
        String sql = "INSERT INTO Route (idRoute, Car) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, route.getIdRoute());
            statement.setString(2, route.getCar());

            statement.executeUpdate();
        }

        return route;
    }

    @Override
    public Route read(int key) throws SQLException {
        String sql = "SELECT * FROM mydatabase.route WHERE idRoute = ?";
        Route route = new Route();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            route.setIdRoute(resultSet.getInt("idRoute"));
            route.setCar(resultSet.getString("Car"));
            route.toString();
        }
        return route;
    }

    @Override
    public void update(Route route) throws SQLException {
        String sql = "UPDATE Route SET Car = ? WHERE idRoute = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, route.getCar());
            statement.setInt(2, route.getIdRoute());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Route route) throws SQLException {
        String sql = "DELETE FROM Route WHERE idRoute = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, route.getIdRoute());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Route> getAll() throws SQLException {
        String sql = "SELECT * FROM mydatabase.route;";
        List<Route> list = new ArrayList<Route>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Route route = new Route();
                route.setIdRoute(resultSet.getInt("idRoute"));
                route.setCar(resultSet.getString("Car"));
                route.toString();
                list.add(route);
            }
        }

        return list;
    }
}
