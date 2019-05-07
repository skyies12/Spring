package com.study.spring23.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.study.spring23.dto.TicketDto;

public class TicketDao {
	JdbcTemplate template;
	
	PlatformTransactionManager transactionManager;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public TicketDao() {
		System.out.println(template);
	}
	
	public void buyTicket(final TicketDto dto) {
		System.out.println("buyTicket()");
		System.out.println("dto.getConsumerId() : " + dto.getConsumerId());
		System.out.println("dto.getAmount() : " + dto.getAmount());	
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into card (consumerid, amount) values (?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getConsumerId());
					pstmt.setString(2, dto.getAmount());
					
					return pstmt;
				}
			});
			
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into ticket (consumerid, countnum) values (?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getConsumerId());
					pstmt.setString(2, dto.getAmount());
					
					return pstmt;
				}
			});
			
			transactionManager.commit(status);
		} catch(Exception e) {
			System.out.println("Rollback 되었습니다.");
			transactionManager.rollback(status);
		}
	}
}
