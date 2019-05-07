package com.study.spring14.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.study.spring14.dto.BDto;
import com.study.spring14.util.Constant;

public class BDao {
	
	DataSource dataSource;
	
	JdbcTemplate template = null;
	
	public static BDao instance = new BDao();
	
	public BDao() {
		template = Constant.template;
	}
	
	public void modify(final String bId, final String bName, final String bTitle, final String bContent) {
		String sql = "update mvc_board " +
				" set bName = ?," + 
				" bTitle = ?," +
				" bContent = ? " +
				" where bId = ?";
		template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bId));
			}
		});
	}
	
	public BDto contentView(String strID, String kind) {
		if(kind.equals("view")) {
			upHit(strID);
		} 

		String sql = "select * from mvc_board where bId = " + strID;
		return template.queryForObject(sql, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	
	public void write(final String bName, final String bTitle, final String bContent) {

		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "insert into mvc_board" +
						" (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent)" +
						" values " +
						" (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				return pstmt;
			}
		});
	}
	
	public ArrayList<BDto> list() {
		String sql = "select * " +
				" from mvc_board " +
				" order by bGroup desc, bStep asc";
		return (ArrayList<BDto>)template.query(sql, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	private void upHit(final String bId) {
		
		String sql = "update mvc_board set bHit = bHit + 1 where bId = ?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,Integer.parseInt(bId));
			}
		});
	}
	
	public void delete(final String bId) {
		String sql = "delete from mvc_board where bId = ?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bId));
			}
		});
	}
	
	public BDto reply_view(String str) {
		String sql = "select * from mvc_board where bId = " + str;
		return template.queryForObject(sql, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public void reply(final String bId, final String bName, final String bTitle, final String bContent, final String bGroup,
			final String bStep, final String bIndent) {
		replyShape(bGroup, bStep);
		
		String sql = "insert into mvc_board" +
				" (bId, bName, bTitle, bContent, bGroup, bStep, bIndent)" +
				" values " +
				" (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bGroup));
				ps.setInt(5, Integer.parseInt(bStep) + 1);
				ps.setInt(6, Integer.parseInt(bIndent) + 1);
			}
		});
	}
	
	private void replyShape(final String strGroup, final String strStep) {
		
		String sql = "update mvc_board " + 
				" set bStep = bStep + 1 " +
				" where bGroup = ? and bStep > ?";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(strGroup));
				ps.setInt(2, Integer.parseInt(strStep));
			}
		});
	}
}
