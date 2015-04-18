package com.dao.MySQL;

import com.dao.R_StateDao;
import com.entities.R_State;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 18.04.2015.
 */
public class MySqlR_StateDao implements R_StateDao{
private final Connection connection;

    @Override
    public R_State create(R_State r_state) throws SQLException {
        String sql = "INSERT INTO R_State (idState_type, Value) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, r_state.getIdState());
            statement.setString(2, r_state.getValue());
            statement.executeUpdate();
        }
        return r_state;
    }

    @Override
    public R_State read(int key) throws SQLException {
        String sql = "SELECT * FROM mydb.R_State WHERE idState_type = ?";
        R_State r_state = new R_State();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            r_state.setIdState(resultSet.getInt("idState_Type"));
            r_state.setValue(resultSet.getString("Value"));
            r_state.toString();

        }
        return r_state;
    }

    @Override
    public void update(R_State r_state) throws SQLException {
        String sql = "UPDATE R_State SET Value = ? WHERE idState_type = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, r_state.getValue());
            statement.setInt(2, r_state.getIdState());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(R_State r_state) throws SQLException {
        String sql = "DELETE FROM R_State WHERE idState_type = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, r_state.getIdState());
            statement.executeUpdate();
        }
    }

    @Override
    public List<R_State> getAll() throws SQLException {
        String sql = "SELECT * FROM mydb.R_State;";
        List<R_State> list = new ArrayList<R_State>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                R_State r_state = new R_State();
                r_state.setIdState(resultSet.getInt("idState_Type"));
                r_state.setValue(resultSet.getString("Value"));
                r_state.toString();
                list.add(r_state);
            }
        }

        return list;
    }

    public MySqlR_StateDao(Connection connection) {
        this.connection = connection;
    }
}
