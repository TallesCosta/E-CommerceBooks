
package br.com.talles.ecommercebooks.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
	
	private final Properties customProps = new Properties();

    public PropertiesConfig() {
        loadConfigFile();
    }

    public String getDBUrl() {
        return customProps.getProperty("H2DBURL");
    }

	public String getWebDriverChromePath() {
		return customProps.getProperty("WEBDRIVER") + "/chromedriver";
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
