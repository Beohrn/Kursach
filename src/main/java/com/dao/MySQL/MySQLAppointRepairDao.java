package com.dao.MySQL;

import com.application.model.AppointRepair;
import com.dao.GenericDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 11.05.2015.
 */
public class MySQLAppointRepairDao implements GenericDao<AppointRepair> {

    private final Connection connection;

    public MySQLAppointRepairDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM kursach.appointrepair";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO kursach.appointrepair (model, number, typeMullFunc, phone, state, tonnage, gradyear, type) VALUES (?,?,?,?,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE kursach.appointrepair SET typeMullFunc = ? WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM kursach.appointrepair WHERE id = ?;";
    }

    @Override
    public AppointRepair create(AppointRepair object) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            //statement.setInt(1, object.getId());
            statement.setString(1, object.getModel());
            statement.setString(2, object.getNumber());
            statement.setString(3, object.getTypeMF());
            statement.setString(4, object.getPhone());
            statement.setString(5, object.getState());
            statement.setString(6, object.getTonnage());
            statement.setString(7, object.getGradYear());
            statement.setString(8, object.getType());

            statement.executeUpdate();
        }
        return object;
    }

    @Override
    public AppointRepair get(int key) throws SQLException {
        AppointRepair appointRepair = new AppointRepair();
        String sql = getSelectQuery() + " WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            appointRepair.setId(resultSet.getInt("id"));
            appointRepair.setModel(resultSet.getString("model"));
            appointRepair.setNumber(resultSet.getString("number"));
            appointRepair.setTypeMF(resultSet.getString("typeMullFunc"));
            appointRepair.setPhone(resultSet.getString("phone"));
            appointRepair.setState(resultSet.getString("state"));
            appointRepair.setTonnage(resultSet.getString("tonnage"));
            appointRepair.setGradYear(resultSet.getString("gradyear"));
            appointRepair.setType(resultSet.getString("type"));

        }
        return appointRepair;
    }

    @Override
    public void update(AppointRepair object) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            statement.setString(1, object.getTypeMF());
            statement.setInt(2, object.getId());
            statement.executeUpdate();
        }
    }

    public void set(AppointRepair object) throws SQLException {
        String sql = "UPDATE kursach.appointrepair SET state = ?, typeMullFunc = ? WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, object.getState());
            statement.setString(2, object.getTypeMF());
            statement.setInt(3, object.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(AppointRepair object) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1, object.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public List<AppointRepair> getAll() throws SQLException {
        List<AppointRepair> list = new ArrayList<AppointRepair>();
        try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AppointRepair appointRepair = new AppointRepair();
                appointRepair.setId(resultSet.getInt("id"));
                appointRepair.setModel(resultSet.getString("model"));
                appointRepair.setNumber(resultSet.getString("number"));
                appointRepair.setTypeMF(resultSet.getString("typeMullFunc"));
                appointRepair.setPhone(resultSet.getString("phone"));
                appointRepair.setState(resultSet.getString("state"));
                appointRepair.setTonnage(resultSet.getString("tonnage"));
                appointRepair.setGradYear(resultSet.getString("gradyear"));
                appointRepair.setType(resultSet.getString("type"));

                list.add(appointRepair);
            }
        }

        return list;
    }
}
