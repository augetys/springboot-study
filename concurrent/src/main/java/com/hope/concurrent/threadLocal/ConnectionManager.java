package com.hope.concurrent.threadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lijin on  2022/3/4
 */
public class ConnectionManager {
    private static Connection connect = null;

    public static Connection openConnection() throws SQLException {
        if (connect == null) {
            connect = DriverManager.getConnection("");
        }
        return connect;
    }

    public static void closeConnection() throws SQLException {
        if (connect != null)
            connect.close();
    }
}
