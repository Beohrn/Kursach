package com.dao.MySQL;

import com.application.model.Regular;
import com.dao.GenericDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 23.05.2015.
 */
public class MySQLRegularDao implements GenericDao<Regular> {

    private final Connection connection;

    public MySQLRegularDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM kursach.regulary";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO kursach.regulary (model, number, phone, type, tonnage, gradyear) VALUES (?,?,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE kursach.regulary SET model = ?, number = ?, phone = ?, type = ?, tonnage = ?, gradyear = ? WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM kursach.regulary WHERE id = ?;";
    }

    @Override
    public Regular create(Regular object) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            //statement.setInt(1, object.getId());
            statement.setString(1, object.getModel());
            statement.setString(2, object.getNumber());
            statement.setString(3, object.getPhone());
            statement.setString(4, object.getType());
            statement.setString(5, object.getTonnage());
            statement.setString(6, object.getGradYear());
            statement.executeUpdate();
        }
        return object;
    }

    @Override
    public Regular get(int key) throws SQLException {
        Regular regular = new Regular();
        String sql = getSelectQuery() + " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            regular.setId(resultSet.getInt("id"));
            regular.setModel(resultSet.getString("model"));
            regular.setNumber(resultSet.getString("number"));
            regular.setPhone(resultSet.getString("phone"));
            regular.setType(resultSet.getString("type"));
            regular.setTonnage(resultSet.getString("tonnage"));
            regular.setGradYear(resultSet.getString("gradyear"));
        }
        return regular;
    }

    @Override
    public void update(Regular object) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            statement.setString(1, object.getModel());
            statement.setString(2, object.getNumber());
            statement.setString(3, object.getPhone());
            statement.setString(4, object.getType());
            statement.setString(5, object.getTonnage());
            statement.setString(6, object.getGradYear());
            statement.setInt(7, object.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Regular object) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getCreateQuery())) {
            statement.setInt(1, object.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Regular> getAll() throws SQLException {
        List<Regular> list = new ArrayList<Regular>();
        try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Regular regular = new Regular();
                regular.setId(resultSet.getInt("id"));
                regular.setModel(resultSet.getString("model"));
                regular.setNumber(resultSet.getString("number"));
                regular.setPhone(resultSet.getString("phone"));
                regular.setType(resultSet.getString("type"));
                regular.setTonnage(resultSet.getString("tonnage"));
                regular.setGradYear(resultSet.getString("gradyear"));
                list.add(regular);
            }
        }
        return list;
    }
}
