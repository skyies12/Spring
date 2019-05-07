package com.study.spring20.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;


import com.study.spring20.MemberDTO;
import com.study.spring20.dto.BDto;
import com.study.spring20.dto.BPageInfo;
import com.study.spring20.util.Constant;


public class BDao {
	
	DataSource dataSource;
	
	int listCount = 10;	// 한페이지당 보여줄 게시물의 갯수
	int pageCount = 10; // 하단에 보여줄 페이지 리스트이 갯수

	JdbcTemplate template = null;
	
	public BDao() {
		template = Constant.template;
	}
	
	public void modify(final String bNum, final String bId, final String bName, final String bTitle, final String bContent, final String bFile) {

		String sql = "update mvc_board1 set bName = ?, bTitle = ?, bContent = ? ,bFile = ?  where bId = ? and bNum = ?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				pstmt.setString(4, bFile);
				pstmt.setString(5, bId);
				pstmt.setString(6, bNum);				
			}
		});
	}
	
	public BDto contentView(String strID, String kind, String intbNum) {
		if(kind.equals("view")) {
			upHit(strID);
		} 
	
		String sql = "select * from mvc_board1 where bId = " + strID +  " and bNum = " + intbNum;
		
		return template.queryForObject(sql, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	
	public void write(final String bNum, final String bName, final String bTitle, final String bFile, final String bContent, final String id) {
		if(bNum.equals("0")) {
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into mvc_board1" +
							" (bNum, bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, id)" +
							" values " +
							" (?, mvc_board_seq1.nextval, ?, ?, ?, 0, mvc_board_seq1.currval, 0, 0, ?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(bNum));
					pstmt.setString(2, bName);
					pstmt.setString(3, bTitle);
					pstmt.setString(4, bContent);
					pstmt.setString(5, id);
					
					return pstmt;
				}
			});
		} else if(bNum.equals("1")) {
			template.update(new PreparedStatementCreator() {
		
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into mvc_board1" +
							" (bNum, bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, id)" +
							" values " +
							" (?, mvc_board_seq2.nextval, ?, ?, ?, 0, mvc_board_seq2.currval, 0, 0, ?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(bNum));
					pstmt.setString(2, bName);
					pstmt.setString(3, bTitle);
					pstmt.setString(4, bContent);
					pstmt.setString(5, id);
					
					return pstmt;
				}
			});
		} else if(bNum.equals("2")) {
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into mvc_board1" +
							" (bNum, bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, id)" +
							" values " +
							" (?, mvc_board_seq3.nextval, ?, ?, ?, 0, mvc_board_seq3.currval, 0, 0, ?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(bNum));
					pstmt.setString(2, bName);
					pstmt.setString(3, bTitle);
					pstmt.setString(4, bContent);
					pstmt.setString(5, id);
					
					return pstmt;
				}
			});
		} else if(bNum.equals("3")) {
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into mvc_board1" +
							" (bNum, bId, bName, bTitle, bFile, bContent, bHit, bGroup, bStep, bIndent, id)" +
							" values " +
							" (?, mvc_board_seq4.nextval, ?, ?, ?, ?, 0, mvc_board_seq4.currval, 0, 0, ?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(bNum));
					pstmt.setString(2, bName);
					pstmt.setString(3, bTitle);
					pstmt.setString(4, bFile);
					pstmt.setString(5, bContent);
					pstmt.setString(6, id);
					
					return pstmt;
				}
			});
		}
	}
	
	
	public ArrayList<BDto> list(int curPage, String intNum) {
	
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
	
		String sql = "select * from (select rownum num, A.* from (select * from mvc_board1 where bNum=" + intNum + "order by bgroup desc, bstep asc ) A where rownum <= " + nEnd + " ) B where B.num >= " + nStart;
		
		return (ArrayList<BDto>)template.query(sql, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public ArrayList<MemberDTO> Memberlist(int curPage) {

		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		String sql = "select * from (select rownum num, A.* from (select * from members where id NOT IN('admin') order by rdate desc) A where rownum <= " + nEnd + " ) B where B.num >=" + nStart;
		
		return (ArrayList<MemberDTO>)template.query(sql, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
		
	}
	
	
	private void upHit(final String bId) {
	
		String sql = "update mvc_board1 set bHit = bHit + 1 where bId = ?";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bId));
			}
		});
	}
	
	public void delete(final String bId, final String bNum) {
		
		String sql = "delete from mvc_board1 where bId = ? and bNum = ?";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, Integer.parseInt(bId));
				pstmt.setInt(2, Integer.parseInt(bNum));
			}
		});
	}
	
	public BDto reply_view(String str, String intNum) {
	
		String sql = "select * from mvc_board1 where bId = " + str + " and bNum = " + intNum;
		
		return template.queryForObject(sql, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public void reply(final String bNum, final String bId, final String bName, final String bTitle, final String bContent, final String bGroup,
			final String bStep, final String bIndent, final String checkid) {
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		if(bNum.equals("0")) {
			String sql = "insert into mvc_board1" +
					" (bNum, bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id)" +
					" values " +
					" (?, mvc_board_seq1.nextval , ?, ?, ?, ?, ?, ?, ?)";
			
			template.update(sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1, Integer.parseInt(bNum));
					pstmt.setString(2, bName);
					pstmt.setString(3, bTitle);
					pstmt.setString(4, bContent);
					pstmt.setInt(5, Integer.parseInt(bGroup));
					pstmt.setInt(6, Integer.parseInt(bStep) + 1);
					pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
					pstmt.setString(8, checkid);
				}
			});
		} else if(bNum.equals("1")) {
			String sql = "insert into mvc_board1" +
					" (bNum, bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id)" +
					" values " +
					" (?, mvc_board_seq2.nextval, ?, ?, ?, ?, ?, ?, ?)";
			template.update(sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1, Integer.parseInt(bNum));
					pstmt.setString(2, bName);
					pstmt.setString(3, bTitle);
					pstmt.setString(4, bContent);
					pstmt.setInt(5, Integer.parseInt(bGroup));
					pstmt.setInt(6, Integer.parseInt(bStep) + 1);
					pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
					pstmt.setString(8, checkid);
				}
			});
		} else if(bNum.equals("2")) {
			String sql = "insert into mvc_board1" +
					" (bNum, bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id)" +
					" values " +
					" (?, mvc_board_seq3.nextval, ?, ?, ?, ?, ?, ?, ?)";
			template.update(sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1, Integer.parseInt(bNum));
					pstmt.setString(2, bName);
					pstmt.setString(3, bTitle);
					pstmt.setString(4, bContent);
					pstmt.setInt(5, Integer.parseInt(bGroup));
					pstmt.setInt(6, Integer.parseInt(bStep) + 1);
					pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
					pstmt.setString(8, checkid);
				}
			});
		} else if(bNum.equals("3")) {
			String sql = "insert into mvc_board1" +
					" (bNum, bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id)" +
					" values " +
					" (?, mvc_board_seq4.nextval, ?, ?, ?, ?, ?, ?, ?)";
			template.update(sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1, Integer.parseInt(bNum));
					pstmt.setString(2, bName);
					pstmt.setString(3, bTitle);
					pstmt.setString(4, bContent);
					pstmt.setInt(5, Integer.parseInt(bGroup));
					pstmt.setInt(6, Integer.parseInt(bStep) + 1);
					pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
					pstmt.setString(8, checkid);
				}
			});
		}
	}
	
	private void replyShape(final String strGroup, final String strStep) {
		
		String sql = "update mvc_board1 " + 
				" set bStep = bStep + 1 " +
				" where bGroup = ? and bStep > ?";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, Integer.parseInt(strGroup));
				pstmt.setInt(2, Integer.parseInt(strStep));
			}
		});
	}
	
	public BPageInfo articlePage(int curPage) {
		
		int listCount = 10;
		int pageCount = 5;
		
		int totalCount = 0;
		
		String sql = "select count(*) as total from members";
		
		totalCount = template.queryForInt(sql);
		
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
		
		int listCount = 10;
		int pageCount = 5;
		
		int totalCount = 0;
		
		String sql = "select count(*) as total from mvc_board1 where bNum = " + bNum;
		
		totalCount = template.queryForInt(sql);
		
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
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		String sql = "";
		
		if(selectNum.equals("0")) {
			sql = "select * from (select rownum num, A.* from (select * from mvc_board1 where bNum=" + intNum + " and bTitle like " + "'%" + strTitle + "%'" + "order by bgroup desc, bstep asc ) A where rownum <= " + nEnd + " ) B where B.num >= " + nStart;
			
		
		} else if(selectNum.equals("1")) {
			sql = "select * from (select rownum num, A.* from (select * from mvc_board1 where bNum=" + intNum + " and bContent like " + "'%" + strContent + "%'" + "order by bgroup desc, bstep asc ) A where rownum <= " + nEnd + " ) B where B.num >= " + nStart;
			
			
		} else if(selectNum.equals("2")) {
			sql = "select * from (select rownum num, A.* from (select * from mvc_board1 where bNum=" + intNum + " and bName like " + "'%" + strTitle + "%'" + "order by bgroup desc, bstep asc ) A where rownum <= " + nEnd + " ) B where B.num >= " + nStart;

		}
		return (ArrayList<BDto>)template.query(sql, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
}