package com.demo.service;

import java.util.Map;

public interface DemoService {
	public Map<String ,Object> queryById(long id);
	public void updateData(long id,String name);
	public void testThreand();
}
