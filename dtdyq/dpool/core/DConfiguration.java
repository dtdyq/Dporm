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
 * ���ݿ������࣬ά�����е����ݿ�������Ϣ�����ݿ����ò���
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
	 * �������ݿ����Ӳ�����ʹ��java��properties�ļ���ʽ
	 * �������ƣ�
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
			throw new DpoolException("��������δ�ҵ�");
		}
		return this;
	}
	/**
	 * ���d�������B�ӳ؅�����ʹ��java��properties�xȡxml�ęn
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
			throw new DpoolException("�������ô���");
		}
	}
}









