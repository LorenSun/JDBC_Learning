package com.jdbc.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdbc.action.PersonAction;
import com.jdbc.model.Person;

public class Test {
	public static void main(String[] args) throws Exception {
	PersonAction action = new PersonAction();
	
	Person p = new Person();
	p.setUser_name("–°C");
	p.setSex(1);
	p.setAge(25);
	p.setBirthday(new Date());
	p.setEmail("xiaowang@111.com");
	p.setMobile("13322222122");
	p.setIsdel(0);
	
	//action.add(p);
	p.setId(3);;
	//action.edit(p);
	action.del(2);
	
	List<Map<String,Object>> params = new ArrayList<Map<String,Object>>();
	Map<String,Object> map = new HashMap<>();
	map.put("name", "user_name");
	map.put("rela", "=");
	map.put("value", "'–°c'");
	
	params.add(map);
	//≤È—Ø
	List<Person> result = action.query(params);
	for (int i = 0; i < result.size(); i++) {
		System.out.println(result.get(i).getId()+
				":"+result.get(i).getUser_name());
		}
		
	}
}
