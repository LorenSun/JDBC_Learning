package com.jdbc.action;

import java.util.List;
import java.util.Map;

import com.jdbc.model.Person;
import com.jdbc.way.Personway;


public class PersonAction {
	public void add(Person p) throws Exception{
		Personway pew = new Personway();
		p.setSex(1);
		p.setCreate_user("ADMIN");
		p.setUpdate_user("ADMIN");
		p.setIsdel(1);
		
		pew.addp(p);
	}
	public void edit(Person p) throws Exception{
		Personway pew = new Personway();
		pew.updatep(p);
	}
	public void del(Integer id) throws Exception{
		Personway pew = new Personway();
		pew.deletep(id);
	}
	public void get(Integer id) throws Exception{
		Personway pew = new Personway();
		pew.get(id);
	}
	public List<Person> query() throws Exception{
		Personway pew = new Personway();
		return pew.query();
	}
	public List<Person> query(List<Map<String,Object>> params) throws Exception{
		Personway pew = new Personway();
		return pew.query(params);
	}
}
