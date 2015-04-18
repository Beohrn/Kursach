package com.main;

import com.dao.DaoFactory;
import com.dao.MySQL.MySQLDaoFactory;
import com.dao.R_StateDao;
import com.entities.R_State;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander on 18.04.2015.
 */
public class Main {

    public static void main(String[] args) {
        DaoFactory daoFactory = new MySQLDaoFactory();
        List<R_State> list;
        try (Connection connection = daoFactory.getConnection()){
            R_StateDao dao = daoFactory.getR_StateDao(connection);
            list = dao.getAll();
            System.out.print(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
