package com.devanshu.compiler.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class PropertyUtils {

	public static Logger logger = LoggerFactory.getLogger(PropertyUtils.class);
	
    private static Properties properties;

    public static String home_path=null;
    static {
    	String overrideFile = System.getProperty("user.home") + File.separator + "llcompiler.properties";
        File file = new File(overrideFile);
        if (file.exists()) {
        	properties = new Properties();
            try {
            	properties.load(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Could not read resource file from file system");
            }
        }
    }

    public static String gethomePath() {
    	home_path = getProperty("home_path");
        return home_path;
    }
    
    public static String getProperty(String propName) {
        if (properties != null && properties.containsKey(propName)) {
            String property = properties.getProperty(propName);
            return property;
        } else {
           logger.error(" property missing in resource file");
        }
		return null;
    }

}
