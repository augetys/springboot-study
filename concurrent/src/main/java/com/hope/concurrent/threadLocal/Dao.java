package com.hope.concurrent.threadLocal;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by lijin on  2022/3/4
 */
public class Dao {

    public static void insert() throws SQLException {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.openConnection();

        // 使用connection进行操作

        connectionManager.closeConnection();
    }
}
