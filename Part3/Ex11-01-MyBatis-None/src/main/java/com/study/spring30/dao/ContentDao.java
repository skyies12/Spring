package com.study.spring30.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.study.spring30.dto.ContentDto;

public class ContentDao implements IDao {
	JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public ContentDao() {
		
	}
	
	@Override
	public ArrayList<ContentDto> listDao() {
		String sql = "select * from board order by mId desc";
		
		ArrayList<ContentDto> dtos = (ArrayList<ContentDto>)template.query(sql, new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
		return dtos;
	}
	
	@Override
	public void writeDao(final String mWriter, final String mContent) {
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "insert into board (mId, mWriter, mContent) values (board_seq.nextval, ?, ?)";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, mWriter);
				pstmt.setString(2, mContent);
				
				return pstmt;
			}
		});
	}
	@Override
	public ContentDto viewDao(String strID) {
		String sql = "select * from board where mId = " + strID;
		
		return template.queryForObject(sql, new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
	}
	
	@Override
	public void deleteDao(final String bId) {
		String sql = "delete from board where mId = ?";
		
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bId));
			}
		});
	}
}
