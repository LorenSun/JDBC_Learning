package com.jdbc.way;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.db.DBUtil;
import com.jdbc.model.Transinfo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Transinfoway {
	public void insert(Transinfo info) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + "insert into trans_info" + 
				"(source_id,source_account,destination_id,destination_account,amount)" + 
				"values(?,?,?,?,?)";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

		pstmt.setInt(1, info.getSource_id());
		pstmt.setString(2, info.getSource_account());
		pstmt.setInt(3, info.getDestination_id());
		pstmt.setString(4, info.getDestination_account());
		pstmt.setDouble(5, info.getAmount());
		pstmt.execute();

	}

	public void update(Transinfo info) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
				" update trans_info" + 
				" set source_id=?,source_account=?,destination_id=?,"+ 
				" destination_account=?,amount=? "+
				" where id = ?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

		pstmt.setInt(1, info.getSource_id());
		pstmt.setString(2, info.getSource_account());
		pstmt.setInt(3, info.getDestination_id());
		pstmt.setString(4, info.getDestination_account());
		pstmt.setDouble(5, info.getAmount());
		pstmt.setInt(6, info.getId());
		pstmt.execute();
	}

	public void delete(Transinfo info) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + 
					" delete from trans_info" + 
					" where id = ?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setInt(1, info.getId());
		pstmt.execute();
	}

	public List<Transinfo> query(Transinfo info) throws Exception {
		List<Transinfo> infs = new ArrayList<Transinfo>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from trans_info");
		sb.append(" where account like ?");
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sb.toString());
		pstmt.setString(1, "%"+info.getSource_account()+"%");
		ResultSet rs = pstmt.executeQuery();

		Transinfo inf = null;
		while (rs.next()) {
			inf = new Transinfo();
			inf.setId(rs.getInt("id"));
			inf.setSource_id(rs.getInt("source_id"));
			inf.setSource_account(rs.getString("source_account"));
			inf.setDestination_id(rs.getInt("destination_id"));
			inf.setDestination_account(rs.getString("destination_account"));
			inf.setAmount(rs.getDouble("amount"));
			inf.setCreate_at(rs.getDate("create_at"));
			
			infs.add(inf);
		}
		return infs;
	}
	
}
