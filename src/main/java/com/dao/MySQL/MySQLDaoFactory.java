package com.dao.MySQL;

import com.application.model.AppointRepair;
import com.application.model.Car;
import com.application.model.CarDetalis;
import com.application.model.Regular;
import com.dao.DaoFactory;
import com.dao.GenericDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander on 06.05.2015.
 */
public class MySqlDaoFactory implements DaoFactory {

    private String user = "root";//Логин пользователя
    private String password = "1234";//Пароль пользователя
    private String url = "jdbc:mysql://localhost:3306/kursach";
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера
    private Map<Class, DaoCreator> creators;

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Car.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlCarDao(connection);
            }
        });

        creators.put(CarDetalis.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection o) {
                return new MySqlCarDetalisDao(o);
            }
        });

        creators.put(AppointRepair.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection o) {
                return new MySQLAppointRepairDao(o);
            }
        });

        creators.put(Regular.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection o) {
                return new MySQLRegularDao(o);
            }
        });
    }
}

