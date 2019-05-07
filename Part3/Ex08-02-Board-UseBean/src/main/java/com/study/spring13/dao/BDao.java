package com.study.spring13.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.spring13.dto.BDto;

public class BDao {
	
	DataSource dataSource;
	
	public static BDao instance = new BDao();

	public static BDao getInstance() {
		return instance;
	}
	
	private BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update mvc_board " +
				" set bName = ?," + 
				" bTitle = ?," +
				" bContent = ? " +
				" where bId = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
	}

	
	public BDto contentView(String strID, String kind) {
		if(kind.equals("view")) {
			upHit(strID);
		} 
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strID));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
		return dto;
	}
	
	
	public void write(String bName, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "insert into mvc_board" +
					" (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent)" +
					" values " +
					" (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			int rn = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}			
	}
	
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "select * " +
						" from mvc_board " +
						" order by bGroup desc, bStep asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				
				dtos.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}			
		return dtos;
	}
	
	private void upHit(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "update mvc_board set bHit = bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,bId);
			
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
	
	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "delete from mvc_board where bId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bId));
			int rn = pstmt.executeUpdate();	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
	
	public BDto reply_view(String str) {
		BDto dto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(str));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
		return dto;
	}
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup,
						String bStep, String bIndent) {
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "insert into mvc_board" +
					" (bId, bName, bTitle, bContent, bGroup, bStep, bIndent)" +
					" values " +
					" (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1);
			
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
	
	private void replyShape(String strGroup, String strStep) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "update mvc_board " + 
						" set bStep = bStep + 1 " +
						" where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
}
