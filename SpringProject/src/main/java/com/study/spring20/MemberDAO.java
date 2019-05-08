package com.study.spring20;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.study.spring20.dto.BDto;
import com.study.spring20.util.Constant;

public class MemberDAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	String uid = "scott";
//	String upw = "tiger";
	
	JdbcTemplate template = null;	
	
	public static final int MEMBER_NONEXISTENET = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOO = 0;
	public static final int MEMBER_LOGIN_SUCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	public static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {
		template = Constant.template;
	}
	public static MemberDAO getInstance() {
		return instance;
	}
	
	
	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public int insertNciname(MemberDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into nickname values(?)";
		int ri = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getNickname());
			pstmt.executeUpdate();
			ri = MemberDAO.MEMBER_JOIN_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}			
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int confirmNickname(String nickname) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from nickname where nickname = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ri = MemberDAO.MEMBER_EXISTENT;
			} else {
				ri = MemberDAO.MEMBER_NONEXISTENET;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();		
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}		
		return ri;
	}
	
	public int insertMember(MemberDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into members values(?,?,?,?,?,?,?,'준회원')";
		int ri = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getBirth());
			pstmt.setString(5, dto.geteMail());
			pstmt.setTimestamp(6, dto.getrDate());
			pstmt.setString(7, dto.getGender());
			pstmt.executeUpdate();
			ri = MemberDAO.MEMBER_JOIN_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}			
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int confirmId(String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ri = MemberDAO.MEMBER_EXISTENT;
			} else {
				ri = MemberDAO.MEMBER_NONEXISTENET;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();		
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}		
		return ri;
	}
	
	public int deletenickname(String nickname) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete nickname where nickname = '" + nickname + "'";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ri = MemberDAO.MEMBER_EXISTENT;
			} else {
				ri = MemberDAO.MEMBER_NONEXISTENET;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();		
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}		
		return ri;
	}
	
	public int delete(String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ri = MemberDAO.MEMBER_EXISTENT;
			} else {
				ri = MemberDAO.MEMBER_NONEXISTENET;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();		
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}		
		return ri;
	}
	
	public int checkId(String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ri = MemberDAO.MEMBER_EXISTENT;
			} else {
				ri = MemberDAO.MEMBER_NONEXISTENET;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();		
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}		
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri = 0;
		String dbPw;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pw from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbPw = rs.getString("pw");
				if(dbPw.equals(pw)) {
					System.out.println("login OK");
					ri = MemberDAO.MEMBER_LOGIN_SUCESS;
				} else {
					System.out.println("login Fail");
					ri = MemberDAO.MEMBER_LOGIN_PW_NO_GOO;
				}
			} else {
				System.out.println("login Fail");
				ri = MemberDAO.MEMBER_LOGIN_IS_NOT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();		
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public MemberDTO getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where id = ?";
		MemberDTO dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.seteMail(rs.getString("eMail"));
				dto.setrDate(rs.getTimestamp("rDate"));
				dto.setGender(rs.getString("gender"));
				dto.setRating(rs.getString("Rating"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();		
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public int updateMember(MemberDTO dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update members set pw=?, eMail=?, birth=?, gender=? where id = ? ";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.geteMail());
			pstmt.setString(3, dto.getBirth());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getId());
			ri = pstmt.executeUpdate();

			System.out.println("성공");
			System.out.println(ri);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();		
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int update(MemberDTO dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update members set Rating ='정회원' where id = ? ";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			ri = pstmt.executeUpdate();

			System.out.println(dto.getRating());
			System.out.println(ri);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();		
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
}
