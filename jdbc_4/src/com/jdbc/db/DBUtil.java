package com.jdbc.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class DBUtil {
	private static final String URL="jdbc:mysql://127.0.0.1:3306/imooc";
	private static final String USER="root";
	private static final String PASSWORD="root";
	
	private static Connection conn = null;
	static {
		try {
			//1\加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//2\获得驱动的连接
			conn =  (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		return conn;
	}
}
