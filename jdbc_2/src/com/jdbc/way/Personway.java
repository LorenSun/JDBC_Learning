package com.jdbc.way;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jdbc.db.DBUtil;
import com.jdbc.model.Person;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Personway {
	public void addp(Person p) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + "insert into Person" + "(user_name,sex,age,birthday,email,mobile,create_user,create_date,"
				+ "update_user,update_date,isdel)" + "values(" + "?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

		pstmt.setString(1, p.getUser_name());
		pstmt.setInt(2, p.getSex());
		pstmt.setInt(3, p.getAge());
		pstmt.setDate(4, new Date(p.getBirthday().getTime()));
		pstmt.setString(5, p.getEmail());
		pstmt.setString(6, p.getMobile());
		pstmt.setString(7, p.getCreate_user());
		pstmt.setString(8, p.getUpdate_user());
		pstmt.setInt(9, p.getIsdel());

		pstmt.execute();

	}

	public void updatep(Person p) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + " update Person" + " set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?, "
				+ " update_user=?,update_date=current_date(),isdel=? " + " where id = ?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

		pstmt.setString(1, p.getUser_name());
		pstmt.setInt(2, p.getSex());
		pstmt.setInt(3, p.getAge());
		pstmt.setDate(4, new Date(p.getBirthday().getTime()));
		pstmt.setString(5, p.getEmail());
		pstmt.setString(6, p.getMobile());
		pstmt.setString(7, p.getUpdate_user());
		pstmt.setInt(8, p.getIsdel());
		pstmt.setInt(9, p.getId());

		pstmt.execute();
	}

	public void deletep(Integer id) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "" + " delete from Person" + " where id = ?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.execute();
	}

	public List<Person> query() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery("select id,user_name,age from Person");

		List<Person> per = new ArrayList<Person>();
		Person p = null;
		while (rs.next()) {
			p = new Person();
			p.setId(rs.getInt("id"));
			p.setUser_name(rs.getString("user_name"));
			p.setAge(rs.getInt("age"));
			per.add(p);
		}
		return per;
	}

	public Person get(Integer id) throws Exception {
		Person p = null;
		Connection conn = DBUtil.getConnection();
		String sql = "" + " select * from Person" + " where id = ?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			p = new Person();
			p.setId(rs.getInt("id"));
			p.setUser_name(rs.getString("user_name"));
			p.setAge(rs.getInt("age"));
			p.setSex(rs.getInt("sex"));
			p.setBirthday(rs.getDate("Birthday"));
			p.setEmail(rs.getString("email"));
			p.setMobile(rs.getString("mobile"));
			p.setCreate_date(rs.getDate("create_date"));
			p.setCreate_user(rs.getString("create_user"));
			p.setUpdate_date(rs.getDate("update_date"));
			p.setUpdate_user(rs.getString("update_user"));
			p.setIsdel(rs.getInt("isdel"));
		}
		return p;
	}

	public List<Person> query(List<Map<String, Object>> params) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		List<Person> per = new ArrayList<Person>();
		sb.append("select * from Person where 1=1");
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map = params.get(i);
				sb.append(" and " + map.get("name") + " " + map.get("rela") + " " + map.get("value"));
			}
		}
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sb.toString());
		System.out.println(sb.toString());
		ResultSet rs = ptmt.executeQuery();
		Person p = null;
		while (rs.next()) {
			p = new Person();
			p.setId(rs.getInt("id"));
			p.setUser_name(rs.getString("user_name"));
			p.setAge(rs.getInt("age"));
			p.setSex(rs.getInt("sex"));
			p.setBirthday(rs.getDate("Birthday"));
			p.setEmail(rs.getString("email"));
			p.setMobile(rs.getString("mobile"));
			p.setCreate_date(rs.getDate("create_date"));
			p.setCreate_user(rs.getString("create_user"));
			p.setUpdate_date(rs.getDate("update_date"));
			p.setUpdate_user(rs.getString("update_user"));
			p.setIsdel(rs.getInt("isdel"));

			per.add(p);
		}
		return per;
	}
}
