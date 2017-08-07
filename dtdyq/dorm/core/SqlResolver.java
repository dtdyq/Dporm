package dtdyq.dorm.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dtdyq.dorm.enums.SqlType;

/**
 * 
 * @author dtdyq
 * 
 * resolve the original sql string to specific Object
 * 
 * select * from User where id = #{id} and name = #{name} order by #{age}
 *
 */
public class SqlResolver {
	private SqlType sqlType = null;
	//the runnable sql string after resolve
	private String sql = null;
	//the specific value's name which need to be replaced
	private List<String> names = new ArrayList<>();
	
	public SqlResolver(){
		
	}
	public SqlResolver reslove(String sql){
		sql = sql.trim();
		if(sql.startsWith("insert")){
			sqlType = SqlType.INSERT;
		}else if(sql.startsWith("update")){
			sqlType = SqlType.UPDATE;
		}else if(sql.startsWith("delete")){
			sqlType = SqlType.DELETE;
		}else{
			sqlType = SqlType.SELECT;
		}
		
		String reg = "#\\{\\w+\\}";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(sql);
		while (matcher.find()) {
			String temp = matcher.group();
			names.add(temp.substring(2,temp.length()-1));
		}
		this.sql = sql.replaceAll(reg, "?");
		return this;
	}
	public SqlType getSqlType() {
		return sqlType;
	}
	public void setSqlType(SqlType sqlType) {
		this.sqlType = sqlType;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	
}







