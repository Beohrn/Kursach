package com.dao;

import com.entities.CarInformation;
import com.entities.Route;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 20.04.2015.
 */
public interface CarInfoDAO {

    public CarInformation create(CarInformation carInformation) throws SQLException;

    public CarInformation read(int key) throws SQLException;

    public void update(CarInformation carInformation) throws SQLException;

    public void delete(CarInformation carInformation) throws SQLException;

    public List<CarInformation> getAll() throws SQLException;
}
