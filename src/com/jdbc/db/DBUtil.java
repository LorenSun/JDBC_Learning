package com.jdbc.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBUtil {
	private static final String URL="jdbc:mysql://127.0.0.1:3306/sun";
	private static final String USER="root";
	private static final String PASSWORD="root";
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1\加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//2\获得驱动的连接
		Connection conn =  (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		//3\操纵数据库，通过链接，实现增删改查
		Statement stmt = (Statement) conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("select user_name,age from person");
		while(rs.next())
		{
			System.out.println(rs.getString("user_name")+","+rs.getInt("age"));
		}
	}

}
