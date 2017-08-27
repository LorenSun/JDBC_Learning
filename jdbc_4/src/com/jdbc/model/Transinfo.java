package com.jdbc.model;

import java.util.Date;

public class Transinfo {
	private Integer id;
	private Integer source_id;
	private String source_account;
	private Integer destination_id;
	private String destination_account;
	private double amount;
	private Date create_at;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSource_id() {
		return source_id;
	}
	public void setSource_id(Integer source_id) {
		this.source_id = source_id;
	}
	public String getSource_account() {
		return source_account;
	}
	public void setSource_account(String source_account) {
		this.source_account = source_account;
	}
	public Integer getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(Integer destination_id) {
		this.destination_id = destination_id;
	}
	public String getDestination_account() {
		return destination_account;
	}
	public void setDestination_account(String destination_account) {
		this.destination_account = destination_account;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	
}
