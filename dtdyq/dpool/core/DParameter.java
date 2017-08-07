package dtdyq.dpool.core;

/**
 * 数据库连接池配置信息
 * 
 * @author dtdyq
 *
 */
public class DParameter {
	// 数据库连接信息
	private String driverName;
	private String url;
	private String user;
	private String password;

	// 连接池配置信息
	// 初始化连接数
	private int initPoolSize;
	// 最大连接数
	private int maxPoolSize;
	// 最少连接数
	private int minPoolSize;
	// 最大空闲连接数
	private int maxIdleSize;
	// 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException
	private long connectTimeout;

	DParameter() {
		super();
	}

	DParameter(String driverName, String url, String user, String password, int initPoolSize, int maxPoolSize,
			int minPoolSize, int maxIdleSize, long connectTimeout){
		this.driverName = driverName;
		this.url = url;
		this.user = user;
		this.password = password;
		this.initPoolSize = initPoolSize;
		this.maxPoolSize = maxPoolSize;
		this.minPoolSize = minPoolSize;
		this.maxIdleSize = maxIdleSize;
		this.connectTimeout = connectTimeout;
	}

	String getDriverName() {
		return driverName;
	}

	String getUrl() {
		return url;
	}

	String getUser() {
		return user;
	}

	String getPassword() {
		return password;
	}

	int getInitPoolSize() {
		return initPoolSize;
	}

	int getMaxPoolSize() {
		return maxPoolSize;
	}

	int getMinPoolSize() {
		return minPoolSize;
	}

	int getMaxIdleSize() {
		return maxIdleSize;
	}

	long getConnectTimeout() {
		return connectTimeout;
	}


	DParameter setDriverName(String driverName) {
		this.driverName = driverName;
		return this;
	}

	DParameter setUrl(String url) {
		this.url = url;
		return this;
	}

	DParameter setUser(String user) {
		this.user = user;
		return this;
	}

	DParameter setPassword(String password) {
		this.password = password;
		return this;
	}

	DParameter setInitPoolSize(int initPoolSize) {
		this.initPoolSize = initPoolSize;
		return this;
	}

	DParameter setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
		return this;
	}

	DParameter setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
		return this;
	}

	DParameter setMaxIdleSize(int maxIdleSize) {
		this.maxIdleSize = maxIdleSize;
		return this;
	}

	DParameter setConnectTimeout(long connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}

}
