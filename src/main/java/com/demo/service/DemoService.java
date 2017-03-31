package com.demo.service;

import java.util.Map;

public interface DemoService {
	public Map<String ,Object> queryById(long id);
	public void updateData(long id,String name);
	public void testThreand();
	
	public String query(long id);
	public String query2(long id);
	public String update(long id ,String name);
	public void del(long id);
	public String insert(long id,String name);
}
