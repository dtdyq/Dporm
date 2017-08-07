package dtdyq.dpool.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import dtdyq.dpool.exception.DpoolException;

/**
 * 
 * @author dtdyq
 * 数据库配置类，维护所有的数据库连接信息，数据库配置参数
 *
 */
public class DConfiguration {
	
	private DParameter dSourceBean = new DParameter();
	
	DParameter getParameter() {
		return dSourceBean;
	}
	DConfiguration setParameter(DParameter dSourceBean) {
		this.dSourceBean = dSourceBean;
		return this;
	}
	
	/**
	 * 配置数据库连接参数，使用java的properties文件格式
	 * 属性名称：
	 * 		dpool.driverName
	 * 		dpool.url
	 * 		dpool.user
	 * 		dpool.password
	 * @param connFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws DpoolException
	 */
	public DConfiguration loadConnMsg(String connFile) throws FileNotFoundException,IOException, DpoolException{
		File file = new File(connFile);
		if(!file.exists()){
			throw new FileNotFoundException();
		}
		Properties prop = new Properties();
		prop.load(new FileInputStream(file));
		try{
			dSourceBean.setDriverName(prop.getProperty("dpool.driverName"))
			.setUrl(prop.getProperty("dpool.url"))
			.setUser(prop.getProperty("dpool.user"))
			.setPassword(prop.getProperty("dpool.password"));
		}catch(Exception e){
			throw new DpoolException("配置属性未找到");
		}
		return this;
	}
	/**
	 * 加載數據庫連接池參數，使用java的properties讀取xml文檔
	 * @param connFile
	 * @throws InvalidPropertiesFormatException
	 * @throws IOException
	 * @throws DpoolException
	 */
	public void loadConnParam(String connFile) throws InvalidPropertiesFormatException, IOException, DpoolException{
		File file = new File(connFile);
		if(!file.exists()){
			throw new FileNotFoundException();
		}
		Properties prop = new Properties();
		prop.loadFromXML(new FileInputStream(file));
		try {
			
			dSourceBean.setInitPoolSize(Integer.parseInt(prop.getProperty("initPoolSize","8")))
				.setMaxPoolSize(Integer.parseInt(prop.getProperty("maxPoolSize","15")))
				.setMinPoolSize(Integer.parseInt(prop.getProperty("minPoolSize", "3")))
				.setMaxIdleSize(Integer.parseInt(prop.getProperty("maxIdleSize", "3")))
				.setConnectTimeout(Long.parseLong(prop.getProperty("connectionTimeout","5000")));
		}catch (Exception e){
			throw new DpoolException("属性配置错误");
		}
	}
}









