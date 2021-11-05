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
				, rs.getString("image"), rs.getString("gender"),rs.getDate("hire_date")
				, rs.getString("mail_address"), rs.getString("zip_code"), rs.getString("address")
				, rs.getString("telephone"), rs.getInt("salary"), rs.getString("characteristics"),rs.getInt("dependents_count"));
		return employee;
	};
	
	public List<Employee> findAll(){
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees ORDER BY hire_date DESC";
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
		String sql = "UPDATE employee SET name=:name, image=:image, gender=:gender, hire_date=:hireDate, mail_address=:mailAddress, zip_code=:zipCode, address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, dependents_count=:dependentsCount WHERE id=:id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(sql, param);
		return employee;
	}
}
