package com.demo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


public interface CommonDao {
	public DataSource getDataSource();
	public void setDataSource(DataSource dataSource );
	
	public List<Map<String,Object>> queryListSQL(String sql) throws SQLException;
	public List<Map<String,Object>> queryListSQL(String sql, Object...obj) throws SQLException;
	public List<Map<String,Object>> queryListSQL(Connection con ,String sql) throws SQLException;
	public List<Map<String,Object>> queryListSQL(Connection con ,String sql, Object...obj) throws SQLException;
	public List<Map<String,Object>> querySQLWithPage(String sql ,int pageSize,int pageNo) throws SQLException;
	public List<Map<String,Object>> querySQLWithPage(String sql ,int pageSize ,int pageNo, Object...obj) throws SQLException;
	public List<Map<String,Object>> querySQLWithPage(Connection con ,String sql ,int pageSize,int pageNo) throws SQLException;
	public List<Map<String,Object>> querySQLWithPage(Connection con ,String sql ,int pageSize ,int pageNo, Object...obj) throws SQLException;
	
	public Map<String,Object> queryObjectSQL(Connection con ,String sql) throws SQLException;
	public Map<String,Object> queryObjectSQL(Connection con ,String sql, Object...obj) throws SQLException;
	public Map<String,Object> queryObjectSQL(String sql) throws SQLException;
	public Map<String,Object> queryObjectSQL(String sql, Object...obj) throws SQLException;
	
	public long excuteSQL(String sql,Object ...obj);
}
