
package br.com.talles.ecommercebooks.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {
	
	private final Properties customProps = new Properties();

    public DbConfig() {
        loadConfigFile();
    }

    public String getDBUrl() {
        return customProps.getProperty("H2DBURL");
    }

    private void loadConfigFile() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("custom-config.properties");
        try {
            customProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
