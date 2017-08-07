package dtdyq.dpool.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author dtdyq
 * 
 * ����Դ�����࣬�������е����ݿ����ӣ��������ݿ�����Ӻ��ͷ�
 */
public class DPoolSource implements PoolSource{

	//��������
	private ConcurrentLinkedQueue<Connection> idleConnection = 
			new ConcurrentLinkedQueue<>();
	//��ʹ�õ�����
	private ConcurrentLinkedQueue<Connection> activeConnection = 
			new ConcurrentLinkedQueue<>();
	
	DParameter parameter;
	
	void init(DParameter parameter) throws ClassNotFoundException, SQLException{
		this.parameter = parameter;
		Class.forName(parameter.getDriverName());
		for(int i=0; i<8; i++){
			idleConnection.add(
					DriverManager.getConnection(parameter.getUrl(), parameter.getUser(), parameter.getPassword()));
		}
	}
	@Override
	public int idleConnectionSize() {
		return idleConnection.size();
	}
	@Override
	public int totalConnectionSize() {
		return idleConnectionSize()+activeConnection.size();
	}
	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		//����п������ӣ���ֱ�ӷ�������
		if(!idleConnection.isEmpty()){
			connection = idleConnection.remove();
			activeConnection.add(connection);
		}else if(totalConnectionSize() < parameter.getMaxPoolSize()){
			//���С��������������������򴴽��µ�����
			connection = DriverManager.getConnection(parameter.getUrl(), parameter.getUser(), parameter.getPassword());
		}
		return connection;
	}
	@Override
	public void destroy() {
		idleConnection.forEach(val->{
			try {
				if(val != null)
					val.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		activeConnection.forEach(val->{
			try {
				if(val != null)
					val.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}
	@Override
	public void release(Connection connection) {
		if(connection != null){
			activeConnection.remove(connection);
			
			if(idleConnection.size() < parameter.getMaxIdleSize() || totalConnectionSize() < parameter.getMinPoolSize()){
				idleConnection.add(connection);
			}else {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
