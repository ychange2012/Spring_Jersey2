package com.demo.dao.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository("defultDao")
public class DefultDao extends CommonDaoImpl {
	@Resource(name="mainDataSource")
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

}
