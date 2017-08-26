package com.jdbc.way;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.db.DBUtil;
import com.jdbc.model.Person;
import com.mysql.jdbc.Connection;

public class Producereway {
	public static Integer select_count() throws SQLException{
		Integer count = 0;
		//1、获得连接
		Connection conn = DBUtil.getConnection(); 
		//2、获得callablestatement
		CallableStatement  cs = conn.prepareCall("call sp_select_count(?)");
		cs.registerOutParameter(1, Types.INTEGER);
		//3、执行存储过程
		cs.execute();
		//4、处理返回的结果:结果集，出参
		count = cs.getInt(1);
		return count;
	}
	public static List<Person> select_filter(String sp_name) throws SQLException
	{
		List<Person> result = new ArrayList<Person>();
		Person p = null;
		//1、获得连接
		Connection conn = DBUtil.getConnection(); 
		//2、获得callablestatement
		CallableStatement  cs = conn.prepareCall("call sp_select_filter(?)");
		cs.setString(1,sp_name);
		//3、执行存储过程
		cs.execute();
		//4、处理返回的结果:结果集，出参
		ResultSet res = cs.getResultSet();
		while(res.next())
		{
			p = new Person();
			p.setId(res.getInt("id"));
			p.setUser_name(res.getString("user_name"));
			p.setAge(res.getInt("age"));
			
			result.add(p);
		}
		return result;
	}
	public static void select_nofilter() throws SQLException{
		//1、获得连接
		Connection conn = DBUtil.getConnection(); 
		//2、获得callablestatement
		CallableStatement  cs = conn.prepareCall("call sp_select_nofilter()");
		//3、执行存储过程
		cs.execute();
		//4、处理返回的结果:结果集，出参
		ResultSet res = cs.getResultSet();
		while(res.next())
		{
			System.out.println(res.getString("user_name")+" "+
		res.getString("email")+" "+res.getString("mobile"));
		}
	}
}
