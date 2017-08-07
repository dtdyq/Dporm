package dtdyq.dpool.core;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author dtdyq
 * 
 * ���ݿ����ӳؽӿ�
 *
 */
public interface PoolSource {
	//ʣ�����������
	int idleConnectionSize();
	//��������
	int totalConnectionSize();
	//��ȡ����
	Connection getConnection() throws SQLException;
	//�������ݿ����ӳ�
	void destroy();
	//�ͷ�����
	void release(Connection connection);
	
}
