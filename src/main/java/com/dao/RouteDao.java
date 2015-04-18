package com.dao;

import com.entities.Route;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 18.04.2015.
 */
public interface RouteDao {

    public Route create();

    public Route read(int key);

    public void update(Route route);

    public void delete(Route route);

    public List<Route> getAll() throws SQLException;

}
