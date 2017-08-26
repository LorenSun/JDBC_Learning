package com.jdbc.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.jdbc.action.PersonAction;
import com.jdbc.model.Person;

public class View {
	private static final String CONTEXT="欢迎：\n"
	+"下面是功能列表：\n"+"[MAIN/M]:主菜单 \n"
	+"[QUERY/Q]:查看员工的全部信息 \n"
	+"[GET/G]:查看某位员工的详细信息 \n"
	+"[ADD/A]:添加员工信息 \n"
	+"[UPDATE/U]:更新员工信息 \n"
	+"[DELETE/D]:删除员工 \n"
	+"[SEARCH/S]:查询员工信息（根据姓名，手机号来查询） \n"
	+"[EXIT/E]:退出员工 \n"
	+"[BREAK/B]:退出当前功能，返回主菜单";
	
	private static final String OPERATION_MAIN = "MAIN";
	private static final String OPERATION_QUERY = "QUERY";
	private static final String OPERATION_GET = "GET";
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_UPDATE = "UPDATE";
	private static final String OPERATION_DELETE = "DELETE";
	private static final String OPERATION_SEARCH = "SEARCH";
	private static final String OPERATION_EXIT = "EXIT";
	private static final String OPERATION_BREAK = "BREAK";
	
	public static void main(String[] args) throws Exception {
		System.out.println(CONTEXT);
		Scanner cin = new Scanner(System.in);
		Person person = new Person();
		PersonAction action = new PersonAction();
		String previous = null;
		Integer step = 1;
		//保持程序一直运行
		while(cin.hasNext())
		{
			String in = cin.next().toString();
			if(OPERATION_EXIT.equals(in.toUpperCase())
					||OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())){
				System.out.println("Exit successful!");
				break;
			}else if(OPERATION_QUERY.equals(in.toUpperCase())
					||OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase())){
				try {
					List<Person>  list = action.query();
					for (Person pe : list) {
						System.out.println(pe.getId()+",姓名："+pe.getUser_name()+",年龄："+pe.getAge());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else if(OPERATION_ADD.equals(in.toUpperCase())
					||OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
					||OPERATION_ADD.equals(previous)){
				previous = OPERATION_ADD;
				if(step==1){
					System.out.println("Please input [name]:");
				}else if(step==2){
					person.setUser_name(in);
					System.out.println("Please input [age]:");
				}else if(step==3){
					person.setAge(Integer.valueOf(in));
					System.out.println("Please input [birthday](格式yyyy-MM-dd):");
				}else if(step==4){
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date birthday = null;
					try {
						birthday = sf.parse(in);
						person.setBirthday(birthday);
						System.out.println("Please input [email]:");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						System.out.println("格式有误，请重新输入。");
						step = 3;
					}
				}else if(step == 5){
					person.setEmail(in);
					System.out.println("Please input [mobile]:");
				}else if(step == 6){
					person.setMobile(in);
					try {
						action.add(person);
						System.out.println("Successful!");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Failed!");
					}
				}
				if(OPERATION_ADD.equals(previous)){
					step++;
				}
			}else{
				System.out.println("your input is "+in);
			}
		}
	}
}