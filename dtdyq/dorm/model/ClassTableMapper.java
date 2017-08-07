package dtdyq.dorm.model;

import java.awt.image.RescaleOp;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.Descriptor;

import dtdyq.dorm.annotation.Column;
import dtdyq.dorm.annotation.Table;
import dtdyq.dorm.enums.ColumnType;
import dtdyq.dorm.tool.AnnotationDetail;

/**
 * class Mapper:resolve the class which has been annotated by Table annotation
 * it provides the mapping between table's column name and clsss's attribute
 * 
 * @author dtdyq
 *
 */
public class ClassTableMapper {
	//target class
	private Class<?> clazz;
	//table name
	private String tableName;
	//key is clazz's attribute and value is table columnDescriptor
	private Map<Field,ColumnDescriptor> mappers = new HashMap<>();
	//table's primary key
	private String tableKey;
	
	private Map<String,Field> map = new HashMap<>();
	public ClassTableMapper(Class<?> clazz){
		Annotation annotation = clazz.getAnnotation(Table.class);
		this.tableName = new AnnotationDetail(annotation).getValue("value");
		this.clazz = clazz;
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			AnnotationDetail detail = new AnnotationDetail(field.getAnnotation(Column.class));
			Class<?> type = field.getType();
			ColumnDescriptor descriptor = new ColumnDescriptor()
					.setName(detail.getValue("name"))
					.setAuto(Boolean.parseBoolean(detail.getValue("auto")))
					.setKey(Boolean.parseBoolean(detail.getValue("key")))
					.setdValue(detail.getValue("value"));
			if(type.isAssignableFrom(Boolean.class)){
				descriptor.setColumnType(ColumnType.BOOLEAN);
			}else if(type.isAssignableFrom(Date.class)){
				descriptor.setColumnType(ColumnType.DATE);
			}else if(type.isAssignableFrom(Float.class) || type.isAssignableFrom(Double.class)){
				descriptor.setColumnType(ColumnType.DOUBLE);
			}else{
				descriptor.setColumnType(ColumnType.STRING);
			}
			mappers.put(field, descriptor);
			map.put(field.getName(), field);
			boolean b = Boolean.parseBoolean(detail.getValue("key"));
			if(b){
				tableKey = detail.getValue("name");
			}
		}
	}
	public List<Field> getCLassFieldsName(){
		return mappers.keySet().stream().collect(Collectors.toList());
	}
	public ColumnDescriptor getColumnByFieldName(String name){
		return mappers.get(map.get(name));
	}
	public ColumnDescriptor getColumnByField(Field field){
		return mappers.get(field);
	}
	public Field getFieldByColumnName(String columnName){
		Field res = null;
		for(Entry<Field, ColumnDescriptor> item : mappers.entrySet()){
			if(item.getValue().getName().equals(columnName)){
				res = item.getKey();
			}
		}
		return res;
	}
	public String getTableKey(){
		return tableKey;
	}
}













