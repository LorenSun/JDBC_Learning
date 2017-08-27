package com.jdbc.service;

import java.sql.SQLException;

import com.jdbc.db.DBUtil;
import com.jdbc.model.Account;
import com.jdbc.model.Transinfo;
import com.jdbc.way.Accountway;
import com.jdbc.way.Transinfoway;
import com.mysql.jdbc.Connection;

public class TransService {
	public String transaction(Account from,Account to,Double amount) throws SQLException
	{
		Accountway accountway  = new Accountway();
		Transinfoway transinfoway = new Transinfoway();
		Connection conn = DBUtil.getConnection();
		conn.setAutoCommit(false);//关闭自动提交
		try {
			Accountway acway = new Accountway();
			Transinfoway tranway = new Transinfoway();
			
			from.setAmount(from.getAmount()-amount);
			accountway.update(from);
			
//			String a = null;
//			a.split("1");
			
			to.setAmount(to.getAmount()+amount);
			accountway.update(to);
			
			Transinfo info = new Transinfo();
			info.setSource_account(from.getAccount());
			info.setSource_id(from.getId());
			info.setDestination_account(to.getAccount());
			info.setDestination_id(to.getId());
			info.setAmount(amount);
			
			transinfoway.insert(info);	
			conn.commit();
			
			return "success!";
			
		} catch (Exception e) {
			conn.rollback();	//若出错，则回滚
			e.printStackTrace();
			return "failed!";
		}
	}
}
