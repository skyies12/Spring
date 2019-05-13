package com.study.springboot.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<UserDTO> list() {
		String sql = "select * from myuser";
		List<UserDTO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
		
		return list;
	}
	
	public int insert(UserDTO dto) {
		String sql = "insert into myuser(id, name) values(?, ?)";
		
		return jdbcTemplate.update(sql, dto.getId(), dto.getName());
	}
}
