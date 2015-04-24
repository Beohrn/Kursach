package com.dao.MySQL;

import com.dao.CarInfoDAO;
import com.entities.CarInformation;
import com.entities.Route;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 20.04.2015.
 */
public class MySqlCarInfoDAO implements CarInfoDAO {

    private final Connection connection;

    public MySqlCarInfoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CarInformation create(CarInformation carInformation) throws SQLException {
        String sql = "INSERT INTO carinformation (idCar, modelCar, state, number) VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carInformation.getIdCar());
            statement.setString(2, carInformation.getModelCar());
            statement.setString(3, carInformation.getState());
            statement.setString(4, carInformation.getNumber());
            statement.executeUpdate();
        }

        return carInformation;
    }

    @Override
    public CarInformation read(int key) throws SQLException {
        String sql = "SELECT * FROM mydatabase.carinformation WHERE idCar = ?";
        CarInformation carInformation = new CarInformation();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            carInformation.setIdCar(resultSet.getInt("idCar"));
            carInformation.setModelCar(resultSet.getString("modelCar"));
            carInformation.setState(resultSet.getString("state"));
            carInformation.setNumber(resultSet.getString("number"));
            carInformation.toString();
        }
        return carInformation;
    }

    @Override
    public void update(CarInformation carInformation) throws SQLException {
        String sql = "UPDATE carinformation SET modelCar = ?, state = ?, number = ? WHERE idCar = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, carInformation.getModelCar());
            statement.setString(2, carInformation.getState());
            statement.setString(3, carInformation.getNumber());
            statement.setInt(4, carInformation.getIdCar());
            statement.executeUpdate();

        }
    }

    @Override
    public void delete(CarInformation carInformation) throws SQLException {
        String sql = "DELETE FROM carinformation WHERE idCar = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carInformation.getIdCar());
            statement.executeUpdate();
        }
    }

    @Override
    public List<CarInformation> getAll() throws SQLException {
        String sql = "SELECT * FROM mydatabase.carinformation;";
        List<CarInformation> list = new ArrayList<CarInformation>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CarInformation carInformation = new CarInformation();
                carInformation.setIdCar(resultSet.getInt("idCar"));
                carInformation.setModelCar(resultSet.getString("modelCar"));
                carInformation.setState(resultSet.getString("state"));
                carInformation.setNumber(resultSet.getString("number"));
                carInformation.toString();
                list.add(carInformation);
            }
        }

        return list;
    }


}
