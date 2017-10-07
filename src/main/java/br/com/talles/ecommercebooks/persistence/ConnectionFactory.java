package br.com.talles.ecommercebooks.persistence;

import br.com.talles.ecommercebooks.config.PropertiesConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String DBURL;

    public ConnectionFactory() {
        PropertiesConfig dbConfig = new PropertiesConfig();
        DBURL = dbConfig.getDBUrl();
    }

    public Connection getConnection() {
        try {
			Class.forName("org.h2.Driver");
            return DriverManager.getConnection(DBURL, "sa", "");
		} catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
