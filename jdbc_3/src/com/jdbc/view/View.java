package com.jdbc.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.jdbc.action.PersonAction;
import com.jdbc.model.Person;

public class View {
	private static final String CONTEXT="��ӭ��\n"
	+"�����ǹ����б�\n"+"[MAIN/M]:���˵� \n"
	+"[QUERY/Q]:�鿴Ա����ȫ����Ϣ \n"
	+"[GET/G]:�鿴ĳλԱ������ϸ��Ϣ \n"
	+"[ADD/A]:���Ա����Ϣ \n"
	+"[UPDATE/U]:����Ա����Ϣ \n"
	+"[DELETE/D]:ɾ��Ա�� \n"
	+"[SEARCH/S]:��ѯԱ����Ϣ�������������ֻ�������ѯ�� \n"
	+"[EXIT/E]:�˳�Ա�� \n"
	+"[BREAK/B]:�˳���ǰ���ܣ��������˵�";
	
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
		//���ֳ���һֱ����
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
						System.out.println(pe.getId()+",������"+pe.getUser_name()+",���䣺"+pe.getAge());
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
					System.out.println("Please input [birthday](��ʽyyyy-MM-dd):");
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
						System.out.println("��ʽ�������������롣");
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