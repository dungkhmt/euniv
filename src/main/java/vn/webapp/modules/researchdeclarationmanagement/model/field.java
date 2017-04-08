package vn.webapp.modules.researchdeclarationmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class field {
	private String fieldName;
	private List<String> values = new ArrayList<String>();
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	
	public void setValue(String value) {
		this.values.add(value);
	}
	
	public void removeValue(int index) {
		this.values.remove(index);
	}
	@Override
	public String toString() {
		return "field [fieldName=" + fieldName + ", values=" + values + "]";
	}

}
