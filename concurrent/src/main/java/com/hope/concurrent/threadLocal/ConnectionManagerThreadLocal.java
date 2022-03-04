package com.hope.concurrent.threadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lijin on  2022/3/4
 */
public class ConnectionManagerThreadLocal {
    private static final ThreadLocal<Connection> dbConnectionLocal = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    });

    public Connection getConnection() {
        return dbConnectionLocal.get();
    }
}
