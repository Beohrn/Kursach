package com.dao.MySQL;

import com.application.model.Car;
import com.dao.GenericDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 06.05.2015.
 */
public class MySqlCarDao implements GenericDao<Car> {

    private final Connection connection;
    public MySqlCarDao(Connection connection) {
        this.connection = connection;
    }



    @Override
    public String getSelectQuery() {
        return "SELECT * FROM kursach.car";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO kursach.car (idCar, carName, carNumber, carState, carType) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE kursach.car SET carName = ?, carNumber = ?, carState = ?, carType = ? WHERE idCar = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM kursach.car WHERE idCar = ?;";
    }

    @Override
    public Car create(Car car)throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            statement.setInt(1, car.getId());
            statement.setString(2, car.getCarName());
            statement.setString(3, car.getCarNumber());
            statement.setString(4, car.getCarState());
            statement.setString(5, car.getCarType());
            statement.executeUpdate();
        }
        return car;
    }

    @Override
    public Car get(int key) throws SQLException {
        Car car = new Car();
        String sql = getSelectQuery() + " WHERE idCar = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            car.setId(resultSet.getInt("idCar"));
            car.setCarName(resultSet.getString("carName"));
            car.setCarNumber(resultSet.getString("carNumber"));
            car.setCarState(resultSet.getString("carState"));
            car.setCarType(resultSet.getString("carType"));
            //car.toString();
        }
        return car;
    }

    @Override
    public void update(Car car) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            statement.setString(1, car.getCarName());
            statement.setString(2, car.getCarNumber());
            statement.setString(3, car.getCarState());
            statement.setString(4, car.getCarType());
            statement.setInt(5, car.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Car car) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1, car.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Car> getAll() throws SQLException {
        List<Car> list = new ArrayList<Car>();
        try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("idCar"));
                car.setCarName(resultSet.getString("carName"));
                car.setCarNumber(resultSet.getString("carNumber"));
                car.setCarState(resultSet.getString("carState"));
                car.setCarType(resultSet.getString("carType"));
                car.toString();
                list.add(car);
            }
        }

        return list;
    }




}
