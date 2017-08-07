package dtdyq.dorm.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SqlConfig {
	private Properties properties;
	public SqlConfig(){
		
	}
	public SqlConfig(FileInputStream is) throws IOException{
		properties.load(is);
	}
	public String getDriverName(){
		return properties.getProperty("dorm.driverName");
	}
	public String getUrl(){
		return properties.getProperty("dorm.url");
	}
	public String getUser(){
		return properties.getProperty("dorm.user");
	}
	public String getPassword(){
		return properties.getProperty("dorm.password");
	}
	public void setAttr(String key,String value){
		this.properties.setProperty(key, value);
	}
}
