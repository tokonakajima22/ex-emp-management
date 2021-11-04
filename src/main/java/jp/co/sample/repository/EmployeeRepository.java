package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee(rs.getInt("id"), rs.getString("name")
				, rs.getString("image"), rs.getString("gender"),rs.getDate("hireDate")
				, rs.getString("mailAddress"), rs.getString("zipCode"), rs.getString("address")
				, rs.getString("telephone"), rs.getInt("salary"), rs.getString("characteristics"),rs.getInt("departmentsCount"));
		return employee;
	};
	
	public List<Employee> findAll(){
		String sql = "SELECT id, name, image, gender, hireDate, mailAddress, zipCode, address, telephone, salary, characteristics, departmentsCount FROM employees ORDER BY hireDate DESC";
		List<Employee> employeeList = template.query(sql,  EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}
	
	public Employee load(Integer id) {
		String sql = "SELECT * FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		Employee employee = template.queryForObject(sql,param,EMPLOYEE_ROW_MAPPER);
		return employee;
	}
	
	public Employee update(Employee employee) {
		String sql = "UPDATE employee SET name=:name, image=:image, gender=:gender, hireDate=:hireDate, mailAddress=:mailAddress, zipCode=:zipCode, address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, departmentsCount=:departmentsCount WHERE id=:id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(sql, param);
		return employee;
	}
}
