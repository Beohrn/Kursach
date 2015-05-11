package com.dao;

import com.application.model.Car;
import com.application.model.CarDetalis;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Alexander on 18.04.2015.
 */
/** Фабрика объектов для работы с базой данных */
public interface DaoFactory {

    public interface DaoCreator<Connection> {
        public GenericDao create(Connection connection);
    }

    /** Возвращает подключение к базе данных */
    public Connection getConnection() throws  SQLException;
}
