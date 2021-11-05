package jp.co.sample.form;

public class UpdateEmployeeForm {
	
	private String id;
	private String departmentCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartmentCount() {
		return departmentCount;
	}
	public void setDepartmentCount(String departmentCount) {
		this.departmentCount = departmentCount;
	}
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", departmentCount=" + departmentCount + "]";
	}
	
	
	

}
