package com.demo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.demo.dao.DefultDao;

@Repository("defultDao")
public class DefultDaoImpl extends CommonDaoImpl implements DefultDao {
	@Resource(name="mainDataSource")
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public String query(long id) {
		List<Map<String,Object>> temp = this.getTemplate().queryForList("select name from TEST_A where id = ?",id);
		if(temp!=null && temp.size()>0){
			return temp.get(0).get("name").toString();
		}
		return null;
	}

	@Override
	public String update(long id, String name) {
		long result = excuteSQL("update TEST_A set name = ? where id = ?", name,id);
		if(result > 0){
			return name;
		}
		return null;
	}

	@Override
	public void del(long id) {
		excuteSQL("delete from TEST_A where id = ?", id);
		
	}

	@Override
	public String insert(long id, String name) {
		long result = excuteSQL("insert into TEST_A (id,name) values(?,?)", id,name);
		if(result > 0){
			return name;
		}
		return null;
	}

}
