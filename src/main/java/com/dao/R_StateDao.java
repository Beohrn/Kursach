package com.dao;

import com.entities.R_State;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 18.04.2015.
 */
public interface R_StateDao {

    public R_State create();

    public R_State read(int key) throws SQLException;

    public void update(R_State r_state);

    public void delete(R_State r_state);

    public List<R_State> getAll() throws SQLException;

}
