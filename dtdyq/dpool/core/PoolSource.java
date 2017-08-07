package dtdyq.dpool.core;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author dtdyq
 * 
 * 数据库连接池接口
 *
 */
public interface PoolSource {
	//剩余空闲连接数
	int idleConnectionSize();
	//总连接数
	int totalConnectionSize();
	//获取连接
	Connection getConnection() throws SQLException;
	//销毁数据库连接池
	void destroy();
	//释放连接
	void release(Connection connection);
	
}
