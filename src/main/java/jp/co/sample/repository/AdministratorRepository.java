package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator(rs.getInt("id"), rs.getString("name"), rs.getString("mailAddress"), rs.getString("password"));
		return administrator;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String sql = "INSERT INTO administrators (name, mailAddress, password) VALUES(:name, :mailAddress, :password);";
		template.update(sql, param);
	}
	
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id, name, mailAddress, password FROM administrators WHERE mailAddress=:mailAddress && password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress",mailAddress).addValue("password",password);
		try {
			return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		}catch(Exception e) {
			return null;
		}
	}
	

}
