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
    public R_State create() {
        return null;
    }

    @Override
    public R_State read(int key) throws SQLException {
        String sql = "SELECT * FROM mydb.R_State WHERE isState_type = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            R_State r_state = new R_State();
            r_state.setIdState(resultSet.getInt("idState_Type"));
            r_state.setValue(resultSet.getString("Value"));
            r_state.toString();
            return r_state;
        }

    }

    @Override
    public void update(R_State r_state) {

    }

    @Override
    public void delete(R_State r_state) {

    }

    @Override
    public List<R_State> getAll() throws SQLException {
        String sql = "SELECT * FROM mydb.R_State;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<R_State> list = new ArrayList<R_State>();
        while (resultSet.next()) {
            R_State r_state = new R_State();
            r_state.setIdState(resultSet.getInt("idState_Type"));
            r_state.setValue(resultSet.getString("Value"));
            r_state.toString();
            list.add(r_state);
        }

        return list;
    }

    public MySqlR_StateDao(Connection connection) {
        this.connection = connection;
    }
}
