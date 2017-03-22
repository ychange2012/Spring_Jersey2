package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.demo.dao.CommonDao;
import com.demo.utils.LowerColumnMapRowMapper;

public class CommonDaoImpl implements CommonDao {
	
	private DataSource dataSource;
	
	@Override
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Override
	public void setDataSource( DataSource dataSource) {
		this.dataSource = dataSource;
		
	}
	@Override
	public List<Map<String, Object>> queryListSQL(String sql) {
		return getTemplate().queryForList(sql,new LowerColumnMapRowMapper());
	}

	@Override
	public List<Map<String, Object>> queryListSQL(String sql, Object... obj) {
		return getTemplate().queryForList(sql,obj,new LowerColumnMapRowMapper());
	}

	@Override
	public List<Map<String, Object>> queryListSQL(Connection con, String sql) throws SQLException {
		return queryListSQL(con, sql, new Object[]{});
	}

	@Override
	public List<Map<String, Object>> queryListSQL(Connection con, String sql, Object... obj) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		LowerColumnMapRowMapper mapper = new LowerColumnMapRowMapper();
		try {
			pst = con.prepareStatement(sql);
			if(obj!=null && obj.length>0)
				for (int i = 0; i < obj.length; i++) {
					pst.setObject(i+1, obj[i]);
				}
			rs = pst.executeQuery();
			int y = 0;
			while(rs.next()){
				result.add(mapper.mapRow(rs, y));
				y++;
			}
		}finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> querySQLWithPage(Connection con, String sql,int pageSize,int pageNo) throws SQLException {
		return querySQLWithPage(con, sql, pageSize, pageNo, new Object[]{});
	}

	@Override
	public List<Map<String, Object>> querySQLWithPage(Connection con, String sql ,int pageSize,int pageNo, Object... obj) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		LowerColumnMapRowMapper mapper = new LowerColumnMapRowMapper();
		oracle.jdbc.rowset.OracleCachedRowSet crs = new oracle.jdbc.rowset.OracleCachedRowSet();
		try {
			pst = con.prepareStatement(sql);
			if(obj!=null && obj.length>0)
				for (int i = 0; i < obj.length; i++) {
					pst.setObject(i+1, obj[i]);
				}
			rs = pst.executeQuery();
			crs.setPageSize(pageSize);
			crs.populate(rs);
			for(int x=0;x<pageNo;x++){
				crs.nextPage();
			}
			int y = 0;
			while(rs.next()){
				result.add(mapper.mapRow(crs, y));
				y++;
			}
		}finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
		return result;
	}

	@Override
	public Map<String, Object> queryObjectSQL(Connection con, String sql) throws SQLException {
		return queryObjectSQL(con, sql, new Object[]{});
	}

	@Override
	public Map<String, Object> queryObjectSQL(Connection con, String sql, Object... obj) throws SQLException {
		List<Map<String, Object>> tempList = queryListSQL(con, sql, obj);
		if(tempList!=null &&tempList.size()>0){
			return tempList.get(0);
		}
		return null;
	}
	
	
	public JdbcTemplate getTemplate(){
		return new JdbcTemplate(dataSource);
	}

	@Override
	public List<Map<String, Object>> querySQLWithPage(String sql, int pageSize, int pageNo) throws SQLException {
		return querySQLWithPage(dataSource.getConnection(), sql, pageSize, pageNo);
	}

	@Override
	public List<Map<String, Object>> querySQLWithPage(String sql, int pageSize, int pageNo, Object... obj)
			throws SQLException {
		return querySQLWithPage(dataSource.getConnection(), sql, pageSize, pageNo,obj);
	}

	@Override
	public Map<String, Object> queryObjectSQL(String sql) throws SQLException {
		List<Map<String, Object>> tempList = getTemplate().query(sql, new LowerColumnMapRowMapper());
		if(tempList!=null &&tempList.size()>0){
			return tempList.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Object> queryObjectSQL(String sql, Object... obj) throws SQLException {
		List<Map<String, Object>> tempList = getTemplate().query(sql, obj, new LowerColumnMapRowMapper());
		if(tempList!=null &&tempList.size()>0){
			return tempList.get(0);
		}
		return null;
	}

	

}
