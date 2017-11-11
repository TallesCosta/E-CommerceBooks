package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.config.PropertiesConfig;
//import br.com.talles.ecommercebooks.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao implements IDao {
    protected Connection conn;

	public void openConnection() {
        conn = new ConnectionFactory().getConnection();
    }
	
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static class ConnectionFactory {

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
	
}
