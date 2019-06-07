package com.beard.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class ConnectorDB {

    private final static Logger LOGGER = Logger.getLogger(ConnectorDB.class);

    public BasicDataSource getDataSource() {

        Properties property = new Properties();
        BasicDataSource ds = new BasicDataSource();

        try {
            property.load(getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties"));

            ds.setUrl(property.getProperty("jdbc.url"));
            ds.setUsername(property.getProperty("jdbc.username"));
            ds.setPassword(property.getProperty("jdbc.password"));

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

        } catch (IOException e) {
            LOGGER.warn("cannot connect to DB");
        }
        return ds;
    }
}
