package com.jdbc.way;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.db.DBUtil;
import com.jdbc.model.Account;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Accountway {
	public void add(Account ac) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + "insert into account_info" + 
				"(account,amount)" + 
				"values(?,?)";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

		pstmt.setString(1, ac.getAccount());
		pstmt.setDouble(2, ac.getAmount());
		pstmt.execute();

	}

	public void update(Account ac) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
				" update account_info" + 
				" set account=?,amount=? "+
				" where id = ?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

		pstmt.setString(1, ac.getAccount());
		pstmt.setDouble(2, ac.getAmount());
		pstmt.setInt(3, ac.getId());
		pstmt.execute();
	}

	public void delete(Account ac) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					" delete from account_info" + 
					" where id = ?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setInt(1, ac.getId());
		pstmt.execute();
	}

	public List<Account> query(Account ac) throws Exception {
		List<Account> acs = new ArrayList<Account>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from account_info");
		sb.append(" where account like ?");
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sb.toString());
		pstmt.setString(1, "%"+ac.getAccount()+"%");
		ResultSet rs = pstmt.executeQuery();

		Account a = null;
		while (rs.next()) {
			a = new Account();
			a.setId(rs.getInt("id"));
			a.setAccount(rs.getString("account"));
			a.setAmount(rs.getDouble("amount"));
			acs.add(a);
		}
		return acs;
	}

	public Account get(Integer id) throws Exception {
		Account a = null;
		Connection conn = DBUtil.getConnection();
		String sql = "" + " select * from account_info" + " where id = ?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			a = new Account();
			a.setId(rs.getInt("id"));
			a.setAccount(rs.getString("account"));
			a.setAmount(rs.getDouble("amount"));
		}
		return a;
	}
}
