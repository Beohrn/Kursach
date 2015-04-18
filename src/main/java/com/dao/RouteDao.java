package com.dao;

import com.entities.Route;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 18.04.2015.
 */
public interface RouteDao {

    public Route create(Route route) throws SQLException;

    public Route read(int key) throws SQLException;

    public void update(Route route) throws SQLException;

    public void delete(Route route) throws SQLException;

    public List<Route> getAll() throws SQLException;

}
