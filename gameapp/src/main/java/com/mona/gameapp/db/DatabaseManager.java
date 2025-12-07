package com.mona.gameapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple PostgreSQL connection manager.
 * Uses the credentials you provided.
 */
public class DatabaseManager {

    private final String connectionUrl;
    private final String user;
    private final String password;

    public DatabaseManager(String host, int port, String database, String user, String password) {
        this.user = user;
        this.password = password;
        this.connectionUrl = "jdbc:postgresql://" + host + ":" + port + "/" + database;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, user, password);
    }
}
