package com.study.spring20.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.study.spring20.MemberDTO;
import com.study.spring20.dto.BDto;
import com.study.spring20.dto.BPageInfo;


public class BDao {
	
	DataSource dataSource;
	
	public static BDao instance = new BDao();
	
	int listCount = 10;	// 한페이지당 보여줄 게시물의 갯수
	int pageCount = 10; // 하단에 보여줄 페이지 리스트이 갯수

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
	
	public void modify(String bNum, String bId, String bName, String bTitle, String bContent, String bFile) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update mvc_board1 set bName = ?, bTitle = ?, bContent = ? ,bFile = ?  where bId = ? and bNum = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bFile);
			pstmt.setString(5, bId);
			pstmt.setString(6, bNum);
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
	
	public BDto contentView(String strID, String kind, String intbNum) {
		if(kind.equals("view")) {
			upHit(strID);
		} 
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = dataSource.getConnection();
			String sql = "select * from mvc_board1 where bId = ? and bNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strID));
			pstmt.setInt(2, Integer.parseInt(intbNum));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bNum = rs.getInt("bNum");
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");		
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bFile = rs.getString("bFile");
				Date bDate = rs.getDate("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String id = rs.getString("id");
				System.out.println(id);
				dto = new BDto(bNum, bId, bName, bTitle, bContent, bFile, bDate, bHit, bGroup, bStep, bIndent, id);
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
	
	
	public void write(String bNum, String bName, String bTitle,  String bFile, String bContent, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			if(bNum.equals("0")) {
				con = dataSource.getConnection();
				String sql = "insert into mvc_board1" +
						" (bNum, bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, id)" +
						" values " +
						" (?, mvc_board_seq1.nextval, ?, ?, ?, 0, mvc_board_seq1.currval, 0, 0, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(bNum));
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bContent);
				pstmt.setString(5, id);
				int rn = pstmt.executeUpdate();
			} else if(bNum.equals("1")) {
				con = dataSource.getConnection();
				String sql = "insert into mvc_board1" +
						" (bNum, bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, id)" +
						" values " +
						" (?, mvc_board_seq2.nextval, ?, ?, ?, 0, mvc_board_seq2.currval, 0, 0, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(bNum));
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bContent);
				pstmt.setString(5, id);
				int rn = pstmt.executeUpdate();
			} else if(bNum.equals("2")) {
				con = dataSource.getConnection();
				String sql = "insert into mvc_board1" +
						" (bNum, bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, id)" +
						" values " +
						" (?, mvc_board_seq3.nextval, ?, ?, ?, 0, mvc_board_seq3.currval, 0, 0, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(bNum));
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bContent);
				pstmt.setString(5, id);
				int rn = pstmt.executeUpdate();
			} else if(bNum.equals("3")) {
				con = dataSource.getConnection();
				String sql = "insert into mvc_board1" +
						" (bNum, bId, bName, bTitle, bFile, bContent, bHit, bGroup, bStep, bIndent, id)" +
						" values " +
						" (?, mvc_board_seq4.nextval, ?, ?, ?, ?, 0, mvc_board_seq4.currval, 0, 0, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(bNum));
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bFile);
				pstmt.setString(5, bContent);
				pstmt.setString(6, id);
				int rn = pstmt.executeUpdate();
			} 
			
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
	
	
	public ArrayList<BDto> list(int curPage, String intNum) {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {
				con = dataSource.getConnection();
				String sql = "select * from (select rownum num, A.* from (select * from mvc_board1 where bNum=? order by bgroup desc, bstep asc ) A where rownum <= ? ) B where B.num >= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(intNum));
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int bNum = rs.getInt("bNum");
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					String bFile = rs.getString("bFile");
					Date bDate = rs.getDate ("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
				
					BDto dto = new BDto(bNum, bId, bName, bTitle, bFile, bContent, bDate, bHit, bGroup, bStep, bIndent);
					
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
	
	public ArrayList<MemberDTO> Memberlist(int curPage) {
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {
				con = dataSource.getConnection();
				String sql = "select * from (select rownum num, A.* from (select * from members where id NOT IN('admin') order by rdate desc) A where rownum <= ? ) B where B.num >=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String id = rs.getString("id");
					String pw = rs.getString("pw");
					String name = rs.getString("name");
					String birth = rs.getString("birth");
					String eMail = rs.getString("eMail");
					Date date = rs.getDate("rdate");
					String gender = rs.getString("gender");
					String rating = rs.getString("rating");
					
					MemberDTO dto = new MemberDTO(id, pw, name, eMail, date, gender, birth, rating);
					//BDto dto = new BDto(bNum, bId, bName, bTitle, bFile, bContent, bDate, bHit, bGroup, bStep, bIndent);
					
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
			String sql = "update mvc_board1 set bHit = bHit + 1 where bId = ?";
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
	
	public void delete(String bId, String bNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "delete from mvc_board1 where bId = ? and bNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bId));
			pstmt.setInt(2, Integer.parseInt(bNum));
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
	
	public BDto reply_view(String str, String intNum) {
		BDto dto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select * from mvc_board1 where bId = ? and bNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(str));
			pstmt.setInt(2, Integer.parseInt(intNum));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bNum = rs.getInt("bNum");
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Date bDate = rs.getDate("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				dto = new BDto(bNum, bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
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
	
	public void reply(String bNum, String bId, String bName, String bTitle, String bContent, String bGroup,
						String bStep, String bIndent, String checkid) {
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			if(bNum.equals("0")) {
				String sql = "insert into mvc_board1" +
						" (bNum, bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id)" +
						" values " +
						" (?, mvc_board_seq1.nextval , ?, ?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(bNum));
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bContent);
				pstmt.setInt(5, Integer.parseInt(bGroup));
				pstmt.setInt(6, Integer.parseInt(bStep) + 1);
				pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
				pstmt.setString(8, checkid);
				
				int rn = pstmt.executeUpdate();
			} else if(bNum.equals("1")) {
				String sql = "insert into mvc_board1" +
						" (bNum, bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id)" +
						" values " +
						" (?, mvc_board_seq2.nextval, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(bNum));
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bContent);
				pstmt.setInt(5, Integer.parseInt(bGroup));
				pstmt.setInt(6, Integer.parseInt(bStep) + 1);
				pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
				pstmt.setString(8, checkid);
				
				int rn = pstmt.executeUpdate();
			} else if(bNum.equals("2")) {
				String sql = "insert into mvc_board1" +
						" (bNum, bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id)" +
						" values " +
						" (?, mvc_board_seq3.nextval, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(bNum));
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bContent);
				pstmt.setInt(5, Integer.parseInt(bGroup));
				pstmt.setInt(6, Integer.parseInt(bStep) + 1);
				pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
				pstmt.setString(8, checkid);
				
				int rn = pstmt.executeUpdate();
			}else if(bNum.equals("3")) {
				String sql = "insert into mvc_board1" +
						" (bNum, bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id)" +
						" values " +
						" (?, mvc_board_seq4.nextval, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(bNum));
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bContent);
				pstmt.setInt(5, Integer.parseInt(bGroup));
				pstmt.setInt(6, Integer.parseInt(bStep) + 1);
				pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
				pstmt.setString(8, checkid);
				
				int rn = pstmt.executeUpdate();
			}
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
			String sql = "update mvc_board1 " + 
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
	
	public BPageInfo articlePage(int curPage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 10;
		int pageCount = 5;
		
		int totalCount = 0;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "select count(*) as total from members";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("total");
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
		
		// 총 페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0) {
			totalPage++;
		}
		int myCurPage = curPage;
		if(myCurPage > totalPage) {
			myCurPage = totalPage;
		}
		if(myCurPage < 1) {
			myCurPage = 1;
		}
		
		// 시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public BPageInfo articlePage(int curPage, String bNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 10;
		int pageCount = 5;
		
		int totalCount = 0;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "select count(*) as total from mvc_board1 where bNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bNum));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("total");
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
		
		// 총 페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0) {
			totalPage++;
		}
		int myCurPage = curPage;
		if(myCurPage > totalPage) {
			myCurPage = totalPage;
		}
		if(myCurPage < 1) {
			myCurPage = 1;
		}
		
		// 시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	public BPageInfo memberarticlePage(int curPage) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int listCount = 10;
		int pageCount = 5;
		
		int totalCount = 0;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "select count(*) as total from members";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("total");
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
		
		// 총 페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0) {
			totalPage++;
		}
		int myCurPage = curPage;
		if(myCurPage > totalPage) {
			myCurPage = totalPage;
		}
		if(myCurPage < 1) {
			myCurPage = 1;
		}
		
		// 시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public ArrayList<BDto> selectlist(int curPage, String strTitle, String intNum, String strContent, String strName, String selectNum) {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		try {
			con = dataSource.getConnection();
			if(selectNum.equals("0")) {
				String sql = "select * from (select rownum num, A.* from (select * from mvc_board1 where bNum=" + intNum + " and bTitle like " + "'%" + strTitle + "%'" + "order by bgroup desc, bstep asc ) A where rownum <= ? ) B where B.num >= ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int bNum = rs.getInt("bNum");
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					String bFile = rs.getString("bFile");
					Date bDate = rs.getDate("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					
					BDto dto = new BDto(bNum, bId, bName, bTitle, bContent,bFile, bDate, bHit, bGroup, bStep, bIndent);
					
					dtos.add(dto);
				}
			} else if(selectNum.equals("1")) {
				String sql = "select * from (select rownum num, A.* from (select * from mvc_board1 where bNum=" + intNum + " and bContent like " + "'%" + strContent + "%'" + "order by bgroup desc, bstep asc ) A where rownum <= ? ) B where B.num >= ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int bNum = rs.getInt("bNum");
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Date bDate = rs.getDate("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					
					BDto dto = new BDto(bNum, bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
					
					dtos.add(dto);
				}
			} else if(selectNum.equals("2")) {
				String sql = "select * from (select rownum num, A.* from (select * from mvc_board1 where bNum=" + intNum + " and bName like " + "'%" + strTitle + "%'" + "order by bgroup desc, bstep asc ) A where rownum <= ? ) B where B.num >= ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, nEnd);
				pstmt.setInt(2, nStart);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int bNum = rs.getInt("bNum");
					int bId = rs.getInt("bId");
					String bName = rs.getString("bName");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Date bDate = rs.getDate("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					
					BDto dto = new BDto(bNum, bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
					
					dtos.add(dto);
				}
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
}