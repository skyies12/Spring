package com.study.spring25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.spring25.dto.TicketDto;

public class TicketDao {
	JdbcTemplate template;
	
	//PlatformTransactionManager transactionManager;
	TransactionTemplate transactionTemplate1;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
//	public void setTransactionManager(PlatformTransactionManager transactionManager) {
//		this.transactionManager = transactionManager;
//	}
	
	public void setTransactionTemplate1(TransactionTemplate transactionTemplate) {
		this.transactionTemplate1 = transactionTemplate;
	}
	
	public TicketDao() {
		System.out.println(template);
	}
	
	public void buyTicket(final TicketDto dto) {		
		try {
			transactionTemplate1.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
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
				}
			});

		} catch(Exception e) {
			System.out.println("transactionTemplate1 : Rollback");
		}
	}
}
