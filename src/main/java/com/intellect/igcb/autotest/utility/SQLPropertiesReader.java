package com.intellect.igcb.autotest.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class SQLPropertiesReader {
	
	
	public static void main(String args[]) {
		System.out.println(SQLPropertiesReader.getInstance().getProperty("DBNEG6783D_PRODUCT"));
	}
	
	

    private final Properties props = new Properties();

    private SQLPropertiesReader() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("sql.properties");
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class PropHolder {
        private static final SQLPropertiesReader INSTANCE = new SQLPropertiesReader();
    }

    public static SQLPropertiesReader getInstance() {
        return PropHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return props.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return props.containsKey(key);
    }

}
