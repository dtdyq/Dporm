package dtdyq.dorm.model;

import dtdyq.dorm.enums.ColumnType;

/**
 * describe the column
 * 
 * @author dtdyq
 *
 */
public class ColumnDescriptor {
	private String name;
	private boolean isKey;
	private boolean isAuto;
	private String dValue;//default value
	private ColumnType columnType;
	public String getName() {
		return name;
	}
	public ColumnDescriptor setName(String name) {
		this.name = name;
		return this;
	}
	public boolean isKey() {
		return isKey;
	}
	public ColumnDescriptor setKey(boolean isKey) {
		this.isKey = isKey;
		return this;
	}
	public boolean isAuto() {
		return isAuto;
	}
	public ColumnDescriptor setAuto(boolean isAuto) {
		this.isAuto = isAuto;
		return this;
	}
	public String getdValue() {
		return dValue;
	}
	public ColumnDescriptor setdValue(String dValue) {
		this.dValue = dValue;
		return this;
	}
	public ColumnType getColumnType() {
		return columnType;
	}
	public ColumnDescriptor setColumnType(ColumnType columnType) {
		this.columnType = columnType;
		return this;
	}
	
}
