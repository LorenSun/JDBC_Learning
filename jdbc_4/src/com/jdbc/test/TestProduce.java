package com.jdbc.test;

import java.sql.SQLException;
import java.util.List;

import com.jdbc.model.Person;
import com.jdbc.way.Producereway;

public class TestProduce {
	public static void main(String[] args){
	String sp_name = "d";
	List<Person> res = null;
	Integer count = 0;
	try {
		//带入参的存储过程
		//res = select_filter(sp_name);
		//showResult(res);
		count = select_count();
		System.out.println(count);
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} 
	public static List<Person> select_filter(String sp_name) throws SQLException{
		Producereway way = new Producereway();
		return way.select_filter(sp_name);
	}
	public static Integer select_count() throws SQLException{
		Producereway way = new Producereway();
		return way.select_count();
	}
	public static void showResult(List<Person> res){
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i).getId()+
					":"+res.get(i).getUser_name()+
					","+res.get(i).getAge());
		}
	}
}
