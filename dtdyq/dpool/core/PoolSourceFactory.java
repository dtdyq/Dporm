package dtdyq.dpool.core;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import dtdyq.dpool.exception.DpoolException;

/**
 * 数据库连接池管理类，对外的接口
 * @author Admin
 *
 */
public class PoolSourceFactory{
	private DConfiguration configuration = new DConfiguration();
	private DPoolSource source;
	
	public PoolSourceFactory(String propFile,String xmlFile) throws InvalidPropertiesFormatException, IOException, DpoolException{
		configuration.loadConnMsg(propFile);
		configuration.loadConnParam(xmlFile);
	}
	public synchronized PoolSource getPoolSource() throws ClassNotFoundException, SQLException{
		if(source == null){
			source = new DPoolSource();
			source.init(configuration.getParameter());
		}
		return source;
	}
}
