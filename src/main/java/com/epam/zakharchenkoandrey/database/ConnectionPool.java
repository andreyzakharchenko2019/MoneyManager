package com.epam.zakharchenkoandrey.database;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private final String CONNECTION_PULL_BUNDLE = "database";
    private final String DEFAULT_LOCALE = "";
    private final String CONNECTION_PULL_DRIVER = "db.driver";
    private final String CONNECTION_PULL_URL = "db.url";
    private final String CONNECTION_PULL_USER = "db.user";
    private final String CONNECTION_PULL_PASSWORD = "db.password";
    private final String CONNECTION_PULL_SIZE = "db.poolsize";
    private final Locale LOCALE = new Locale(DEFAULT_LOCALE);
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle(CONNECTION_PULL_BUNDLE, LOCALE);
    private final String DRIVER = BUNDLE.getString(CONNECTION_PULL_DRIVER);
    private final String URL = BUNDLE.getString(CONNECTION_PULL_URL);
    private final String USER = BUNDLE.getString(CONNECTION_PULL_USER);
    private final String PASSWORD = BUNDLE.getString(CONNECTION_PULL_PASSWORD);
    private final int CONNECTIONS_COUNT = Integer.parseInt(BUNDLE.getString(CONNECTION_PULL_SIZE));
    private BlockingQueue<Connection> CONNECTION_QUEUE = new ArrayBlockingQueue<Connection>(CONNECTIONS_COUNT);
    private static volatile ConnectionPool instance;
    public static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < CONNECTIONS_COUNT; i++) {
            try {
                CONNECTION_QUEUE.put(getConnection());
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return connection;
    }

    public Connection retrieve() {
        Connection connection = null;
        try {
            connection = CONNECTION_QUEUE.take();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        return connection;
    }

    public void putBack (Connection connection)  {
        if (connection != null) {
            try {
                CONNECTION_QUEUE.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }
}