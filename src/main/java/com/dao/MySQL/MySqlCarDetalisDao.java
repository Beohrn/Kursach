package com.dao.MySQL;

import com.application.model.Car;
import com.application.model.CarDetalis;

import com.dao.GenericDao;
import com.dao.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 06.05.2015.
 */
public class MySqlCarDetalisDao implements GenericDao<CarDetalis> {

    private final Connection connection;
    public MySqlCarDetalisDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public String getSelectQuery() {
        return "SELECT * FROM kursach.cardetalis";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO kursach.cardetalis (idCarDetalis, carName, carNumber, " +
                "carType, carState, carTonnage, carPhoneNumber, carGradYear) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE kursach.cardetalis SET carName = ?, " +
                "carNumber = ?, carType = ?, carState = ?, carTonnage = ?, " +
                "carPhoneNumber = ?, carGradYear = ? WHERE idCarDetalis = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM kursach.cardetalis WHERE idCarDetalis = ?;";
    }

    @Override
    public CarDetalis create(CarDetalis object) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getCarName());
            statement.setString(3, object.getCarNumber());
            statement.setString(4, object.getCarType());
            statement.setString(5, object.getCarState());
            statement.setString(6, object.getCarTonnage());
            statement.setString(7, object.getCarPhoneNumber());
            statement.setString(8, object.getCarGradYear());
            statement.executeUpdate();
        }
        return object;
    }

    @Override
    public CarDetalis get(int key) throws SQLException {
        CarDetalis carDetalis = new CarDetalis();
        String sql = getSelectQuery() + " WHERE idCarDetalis = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            carDetalis.setId(resultSet.getInt("idCarDetalis"));
            carDetalis.setCarName(resultSet.getString("carName"));
            carDetalis.setCarNumber(resultSet.getString("carNumber"));
            carDetalis.setCarType(resultSet.getString("carType"));
            carDetalis.setCarState(resultSet.getString("carState"));
            carDetalis.setCarTonnage(resultSet.getString("carTonnage"));
            carDetalis.setCarPhoneNumber(resultSet.getString("carPhoneNumber"));
            carDetalis.setCarGradYear(resultSet.getString("carGradYear"));
            carDetalis.toString();
        }
        return carDetalis;
    }

    @Override
    public void update(CarDetalis object) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {

            statement.setString(1, object.getCarName());
            statement.setString(2, object.getCarNumber());
            statement.setString(3, object.getCarType());
            statement.setString(4, object.getCarState());
            statement.setString(5, object.getCarTonnage());
            statement.setString(6, object.getCarPhoneNumber());
            statement.setString(7, object.getCarGradYear());
            statement.setInt(8, object.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(CarDetalis object) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1, object.getId());
            statement.executeUpdate();
        }

    }

    @Override
    public List<CarDetalis> getAll() throws SQLException {
        List<CarDetalis> list = new ArrayList<CarDetalis>();
        try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CarDetalis carDetalis = new CarDetalis();
                carDetalis.setId(resultSet.getInt("idCarDetalis"));
                carDetalis.setCarName(resultSet.getString("carName"));
                carDetalis.setCarNumber(resultSet.getString("carNumber"));
                carDetalis.setCarType(resultSet.getString("carType"));
                carDetalis.setCarState(resultSet.getString("carState"));
                carDetalis.setCarTonnage(resultSet.getString("carTonnage"));
                carDetalis.setCarPhoneNumber(resultSet.getString("carPhoneNumber"));
                carDetalis.setCarGradYear(resultSet.getString("carGradYear"));
                carDetalis.toString();
                list.add(carDetalis);
            }
        }
        return list;
    }
}
