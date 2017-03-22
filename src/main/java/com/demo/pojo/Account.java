package com.demo.pojo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="data", description="Sample model" )
public class Account {
	@ApiModelProperty(position = 1, required = true, value = "账号")
	private int account;
	
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@ApiModelProperty(position = 2, required = true, value = "密码")
	private String password;
	
	public List<item> getItems() {
		return items;
	}
	public void setItems(List<item> items) {
		this.items = items;
	}

	private List<item> items;
}


class item{
	@ApiModelProperty(position = 1, required = true, value = "ID")
	private int id;
	@ApiModelProperty(position = 2, required = true, value = "姓名")
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
