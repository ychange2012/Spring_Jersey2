package com.demo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.demo.dao.impl.DefultDaoImpl;
import com.demo.service.DemoService;

@Service("demoServiceImpl")
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	DefultDaoImpl defultDao;
	
	@Autowired
	ThreadPoolTaskExecutor 	threadPoolTaskExecutor;
	
	public Map<String ,Object> queryById(long id){
		Map<String ,Object> result = new HashMap<String ,Object>();
		String sql = "select id,name,tel from a where a.id = 1";
		try {
			result =  defultDao.queryObjectSQL(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void testThreand() {
		List<Future<List<String>>> futures = new ArrayList<Future<List<String>>>();
		for(int i=0;i<10;i++){
			Future<List<String>> future = 
					threadPoolTaskExecutor.submit(new Callable<List<String>>() {
						@Override
						public List<String> call() throws Exception {
							List<String> result = new ArrayList<String>();
							//线程执行
							return result;
						}
			});
			futures.add(future);
		}
		
		for(int i=0;i<futures.size();i++){
			Future<List<String>> f = futures.get(i);
			try {
				List<String> err = f.get(1000*60*120, TimeUnit.SECONDS);
			} catch (TimeoutException | ExecutionException e) {
				// TODO: handle exception
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				f.cancel(true);
			}
		}
		
	}

	@Override
	public void updateData(long id, String name) {
		String sql = "update TEST_A set name = ? where id = ?";
		long result = defultDao.excuteSQL(sql, name,id);
		System.out.println(result);
	}
	@Cacheable(value="common",key="#id" )
	@Override
	public String query(long id) {
		System.out.println("=====query");
		return defultDao.query(id);
	}
	
	@Cacheable(value="default",key="#id" )
	@Override
	public String query2(long id) {
		System.out.println("=====query");
		return defultDao.query(id);
	}
	
	@CachePut(value="common",key="#id")
	@Override
	public String update(long id, String name) {
		System.out.println("=====update");
		return defultDao.update(id, name);
	}
	
	@CacheEvict(value="common",key="#id") 
	@Override
	public void del(long id) {
		System.out.println("=====del");
		defultDao.del(id);
		
	}
	
	@CachePut(value="common",key="#id")
	@Override
	public String insert(long id, String name) {
		System.out.println("=====insert");
		return defultDao.insert(id, name);
	}
}
