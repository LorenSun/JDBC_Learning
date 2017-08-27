package com.jdbc.action;

import com.jdbc.model.Account;
import com.jdbc.service.TransService;
import com.jdbc.way.Accountway;

public class TransAction {
	public static void main(String[] args) {
	try {
		String res = trans();
		System.out.println(res);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public static String trans() throws Exception
	{
		Accountway accountway = new Accountway();
		TransService transService = new TransService();
		
		Account from = accountway.get(1);
		Account to = accountway.get(2);
		
		return transService.transaction(from, to, 10d);
	}
}
